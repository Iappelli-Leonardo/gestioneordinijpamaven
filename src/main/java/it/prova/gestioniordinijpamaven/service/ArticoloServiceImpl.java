package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.gestioniordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioniordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;

public class ArticoloServiceImpl implements ArticoloService {

	private ArticoloDao articoloDao;

	@Override
	public List<Articolo> listAll() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Articolo caricaSingoloElementoEagerCategorie(Long id) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return articoloDao.findByIdFetchingGeneri(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Articolo cdInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.update(cdInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Articolo cdInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.insert(cdInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Articolo articoloInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			articoloDao.delete(articoloInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void aggiungiArticolo(Articolo articoloInstance, Categoria categoriaInstance) throws Exception {
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			articoloDao.setEntityManager(entityManager);

			// 'attacco' alla sessione di hibernate i due oggetti
			// così jpa capisce che se risulta presente quel cd non deve essere inserito
			articoloInstance = entityManager.merge(articoloInstance);
			// attenzione che genereInstance deve essere già presente (lo verifica dall'id)
			// se così non è viene lanciata un'eccezione
			categoriaInstance = entityManager.merge(categoriaInstance);

			articoloInstance.getCategorie().add(categoriaInstance);
			// l'update non viene richiamato a mano in quanto
			// risulta automatico, infatti il contesto di persistenza
			// rileva che cdInstance ora è dirty vale a dire che una sua
			// proprieta ha subito una modifica (vale anche per i Set ovviamente)
			// inoltre se risultano già collegati lo capisce automaticamente grazie agli id

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}


	}

	@Override
	public void creaECollegaOrdineEArticolo(Articolo ordineTransientInstance, Categoria categoriaTransientInstance) throws Exception  {
	EntityManager entityManager = EntityManagerUtil.getEntityManager();

	try {
		// questo è come il MyConnection.getConnection()
		entityManager.getTransaction().begin();

		// uso l'injection per il dao
		articoloDao.setEntityManager(entityManager);

		// collego le due entità: questa cosa funziona grazie al fatto che ho
		// CascadeType.PERSIST, CascadeType.MERGE dentro l'owner della relazione (Cd in
		// questo caso)
		ordineTransientInstance.getCategorie().add(categoriaTransientInstance);

		// ********************** IMPORTANTE ****************************
		// se io rimuovo i cascade, non funziona più e si deve prima salvare il genere
		// (tramite genereDAO iniettando anch'esso) e poi
		// sfruttare i metodi addTo o removeFrom dentro Cd:
		// GenereDAO genereDAO = MyDaoFactory.getGenereDAOInstance();
		// genereDAO.setEntityManager(entityManager);
		// genereDAO.insert(genereTransientInstance);
		// cdTransientInstance.addToGeneri(genereTransientInstance);
		// in questo caso però se il genere è già presente non ne tiene conto e
		// inserirebbe duplicati, ma è logico
		// ****************************************************************

		// inserisco il cd
		articoloDao.insert(ordineTransientInstance);

		entityManager.getTransaction().commit();
	} catch (Exception e) {
		entityManager.getTransaction().rollback();
		e.printStackTrace();
		throw e;
	} finally {
		EntityManagerUtil.closeEntityManager(entityManager);
	}
	}

	@Override
	public void setArticoloDAO(ArticoloDao articoloDao) {
		this.articoloDao = articoloDao;

	}

}
