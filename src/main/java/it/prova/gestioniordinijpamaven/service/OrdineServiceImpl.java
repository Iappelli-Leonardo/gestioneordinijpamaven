package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.ordine.OrdineDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDao ordineDAO;

	@Override
	public List<Ordine> listAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine caricaSingoloElemento(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiorna(Ordine cdInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void inserisciNuovo(Ordine cdInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void rimuovi(Ordine ordineInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void aggiungiArticolo(Ordine cdInstance, Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void creaECollegaOrdineEArticolo(Ordine ordineTransientInstance, Articolo articoloTransientInstance)
			throws Exception {
		// TODO Auto-generated method stub

	}

	public OrdineDao getOrdineDAO() {
		return ordineDAO;
	}

	@Override
	public void setOrdineDAO(OrdineDao ordineDao) {
		this.ordineDAO = ordineDao;
	}

}
