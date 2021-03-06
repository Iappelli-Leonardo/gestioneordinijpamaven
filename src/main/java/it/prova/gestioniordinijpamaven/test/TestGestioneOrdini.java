package it.prova.gestioniordinijpamaven.test;

import java.util.Date;

import it.prova.gestioniordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;
import it.prova.gestioniordinijpamaven.service.ArticoloService;
import it.prova.gestioniordinijpamaven.service.CategoriaService;
import it.prova.gestioniordinijpamaven.service.MyServiceFactory;
import it.prova.gestioniordinijpamaven.service.OrdineService;

public class TestGestioneOrdini {
	public static void main(String[] args) {
		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();

		try {

			System.out.println(
					"In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			System.out
					.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
		
			System.out.println(
					"*************************************************************************************************");

			testInserimentoNuovoArticolo(articoloServiceInstance);

			testInserisciAggiornaRimuoviOrdine(ordineServiceInstance);

			testAggiornaArticolo(articoloServiceInstance);

			testAggiornaCategoria(categoriaServiceInstance);

			testCollegaCategoriaAdArticolo(articoloServiceInstance, categoriaServiceInstance);

			testCreazioneECollegamentoArticoloInUnSoloColpo(articoloServiceInstance, categoriaServiceInstance);

			testRimozioneArticoloECheckCategoria(articoloServiceInstance, categoriaServiceInstance);

//			testCalcolaSommaByArticoloDiCategoria(articoloServiceInstance, categoriaServiceInstance,
//					ordineServiceInstance);
//
//			testCercaTuttiByOrdineArticolo(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);
//
//			testStampaOrdiniPerUnaCategoria(articoloServiceInstance, categoriaServiceInstance, ordineServiceInstance);

		
			System.out.println("In tabella Genere ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
			System.out.println("In tabella Cd ci sono " + articoloServiceInstance.listAll().size() + " elementi.");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// questa ?? necessaria per chiudere tutte le connessioni quindi rilasciare il
			// main
			EntityManagerUtil.shutdown();
		}

	}

	private static void testInserimentoNuovoArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(".......testInserimentoNuovoArticolo inizio.............");

		Articolo articoloInstance = new Articolo("descrizione", 120);
		articoloServiceInstance.inserisciNuovo(articoloInstance);
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testInserimentoNuovoArticolo fallito ");

		System.out.println(".......testInserimentoNuovoArticolo fine: PASSED.............");
	}

	private static void testAggiornaArticolo(ArticoloService articoloServiceInstance) throws Exception {
		System.out.println(".......testAggiornaArticolo inizio.............");

		Articolo articoloInstance = new Articolo("costoso", 120);
		articoloServiceInstance.inserisciNuovo(articoloInstance);
		String descrizioneDaAggiornare = "Qualit??";
		articoloInstance.setDescrizione(descrizioneDaAggiornare);
		articoloServiceInstance.aggiorna(articoloInstance);

		if (!articoloServiceInstance.caricaSingoloElemento(articoloInstance.getId()).getDescrizione()
				.equals(descrizioneDaAggiornare))
			throw new RuntimeException("testAggiornaArticolo fallito: articolo non aggiornato ");

		articoloServiceInstance.rimuovi(articoloInstance);
		if (articoloServiceInstance.caricaSingoloElemento(articoloInstance.getId()) != null)
			throw new RuntimeException("testAggiornaArticolo fallito: eliminazione articolo fallita ");
		System.out.println(".......testAggiornaArticolo fine: PASSED.............");
	}

	private static void testInserisciAggiornaRimuoviOrdine(OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testInserisciAggiornaRimuoviOrdine inizio.............");

		Ordine ordinePerIlTest = new Ordine("peppe", "via ligure");
		ordineServiceInstance.inserisciNuovo(ordinePerIlTest);
		if (ordineServiceInstance.caricaSingoloElemento(ordinePerIlTest.getId()) == null)
			throw new RuntimeException("testInserisciAggiornaRimuoviOrdine fallito: ordine non inserito ");

		String nomeDestinatarioDaModificare = "leonardo";
		ordinePerIlTest.setNomeDestinatario(nomeDestinatarioDaModificare);
		ordineServiceInstance.aggiorna(ordinePerIlTest);

		if (!ordineServiceInstance.caricaSingoloElemento(ordinePerIlTest.getId()).getNomeDestinatario()
				.equals(nomeDestinatarioDaModificare))
			throw new RuntimeException("testInserisciAggiornaRimuoviOrdine fallito: ordine non aggiornato ");

		ordineServiceInstance.rimuovi(ordinePerIlTest);
		if (ordineServiceInstance.caricaSingoloElemento(ordinePerIlTest.getId()) != null)
			throw new RuntimeException("testInserisciAggiornaRimuoviOrdine fallito: eliminazione ordine fallita ");
		System.out.println(".......testInserisciAggiornaRimuoviOrdine fine: PASSED.............");
	}

	private static void testAggiornaCategoria(CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testAggiornaCategoria inizio.............");

		Categoria categoriaInstance = new Categoria("console");
		categoriaServiceInstance.inserisciNuovo(categoriaInstance);
		;
		String descrizioneDaAggiornare = "ScarpeSportive";
		categoriaInstance.setDescrizione(descrizioneDaAggiornare);
		categoriaServiceInstance.aggiorna(categoriaInstance);

		if (!categoriaServiceInstance.caricaSingoloElemento(categoriaInstance.getId()).getDescrizione()
				.equals(descrizioneDaAggiornare))
			throw new RuntimeException("testAggiornaCategoria fallito: genere non aggiornato ");

		categoriaServiceInstance.rimuovi(categoriaInstance);
		if (categoriaServiceInstance.caricaSingoloElemento(categoriaInstance.getId()) != null)
			throw new RuntimeException("testAggiornaCategoria fallito: eliminazione genere fallita ");
		System.out.println(".......testAggiornaCategoria fine: PASSED.............");
	}

	private static void testCollegaCategoriaAdArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testCollegaCategoriaAdArticolo inizio.............");

		long nowInMillisecondi = new Date().getTime();

		Articolo articoloInstance = new Articolo("descrizione" + nowInMillisecondi, 600);
		articoloServiceInstance.inserisciNuovo(articoloInstance);
		if (articoloInstance.getId() == null)
			throw new RuntimeException("testCollegaCategoriaAdArticolo fallito: inserimento articolo non riuscito ");

		Categoria nuovoCategoria = new Categoria("Vestiario" + nowInMillisecondi);
		categoriaServiceInstance.inserisciNuovo(nuovoCategoria);
		if (nuovoCategoria.getId() == null)
			throw new RuntimeException("testCollegaCategoriaAdArticolo fallito: categoria non inserito ");

		// collego
		articoloServiceInstance.aggiungiCategoria(articoloInstance, nuovoCategoria);

		// ricarico eager per forzare il test
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstance.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException("testCollegaCategoriaAdArticolo fallito: categoria non collegato ");

		System.out.println(".......testCollegaCategoriaAdArticolo fine: PASSED.............");
	}

	private static void testCreazioneECollegamentoArticoloInUnSoloColpo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testCreazioneECollegamentoArticoloInUnSoloColpo inizio.............");

		long nowInMillisecondi = new Date().getTime();
		Articolo articoloInstanceX = new Articolo("descrizione" + nowInMillisecondi, 300);
		Categoria categoriaX = new Categoria("categoria" + nowInMillisecondi);
		articoloServiceInstance.creaECollegaOrdineEArticolo(articoloInstanceX, categoriaX);

		if (articoloInstanceX.getId() == null)
			throw new RuntimeException("testCreazioneECollegamentoArticoloInUnSoloColpo fallito: cd non inserito ");

		if (categoriaX.getId() == null)
			throw new RuntimeException("testCreazioneECollegamentoArticoloInUnSoloColpo fallito: genere non inserito ");

		// ricarico eager per forzare il test
		Articolo articoloReloaded = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstanceX.getId());
		if (articoloReloaded.getCategorie().isEmpty())
			throw new RuntimeException(
					"testCreazioneECollegamentoArticoloInUnSoloColpo fallito: genere e cd non collegati ");

		System.out.println(".......testCreazioneECollegamentoArticoloInUnSoloColpo fine: PASSED.............");

	}

	private static void testRimozioneArticoloECheckCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance) throws Exception {
		System.out.println(".......testRimozioneArticoloECheckCategoria inizio.............");

		long nowInMillisecondi = new Date().getTime();
		Articolo articoloInstanceX = new Articolo("descrizione" + nowInMillisecondi, 500);
		articoloServiceInstance.inserisciNuovo(articoloInstanceX);
		Categoria categoria1 = new Categoria("categoria" + nowInMillisecondi);
		categoriaServiceInstance.inserisciNuovo(categoria1);
		Categoria categoria2 = new Categoria("categoria" + nowInMillisecondi + 1);
		categoriaServiceInstance.inserisciNuovo(categoria2);
		articoloServiceInstance.aggiungiCategoria(articoloInstanceX, categoria1);
		articoloServiceInstance.aggiungiCategoria(articoloInstanceX, categoria2);

		// ricarico eager per forzare il test
		Articolo cdReloaded = articoloServiceInstance.caricaSingoloElementoEagerCategorie(articoloInstanceX.getId());
		if (cdReloaded.getCategorie().size() != 2)
			throw new RuntimeException("testRimozioneArticoloECheckCategoria fallito: 2 generi e cd non collegati ");

		// rimuovo
		articoloServiceInstance.rimuovi(cdReloaded);

		// ricarico
		Articolo cdSupposedToBeRemoved = articoloServiceInstance
				.caricaSingoloElementoEagerCategorie(articoloInstanceX.getId());
		if (cdSupposedToBeRemoved != null)
			throw new RuntimeException("testRimozioneArticoloECheckCategoria fallito: rimozione non avvenuta ");

		System.out.println(".......testRimozioneArticoloECheckCategoria fine: PASSED.............");
	}

	private static void testStampaOrdiniPerUnaCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testStampaOrdiniPerUnaCategoria inizio.............");

		long nowInMillisecondi = new Date().getTime();
		Articolo articoloInstanceX = new Articolo("Samsumg" + nowInMillisecondi, 500);
		articoloServiceInstance.inserisciNuovo(articoloInstanceX);
		Categoria categoria1 = new Categoria("PC" + nowInMillisecondi);
		categoriaServiceInstance.inserisciNuovo(categoria1);
		articoloServiceInstance.aggiungiCategoria(articoloInstanceX, categoria1);
		Ordine ordineInstance = new Ordine("Filippo", "Roma Sud");
		ordineServiceInstance.inserisciNuovo(ordineInstance);
		ordineServiceInstance.aggiungiArticolo(ordineInstance, articoloInstanceX);

		System.out.println(".......testStampaOrdiniPerUnaCategoria fine: PASSED.............");
	}

	private static void testCercaTuttiByOrdineArticolo(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testCercaTuttiByOrdineArticolo inizio.............");

		long nowInMillisecondi = new Date().getTime();
		Articolo articoloInstanceX = new Articolo("Samsumg" + nowInMillisecondi, 500);
		articoloServiceInstance.inserisciNuovo(articoloInstanceX);
		Categoria categoria1 = new Categoria("PC" + nowInMillisecondi);
		categoriaServiceInstance.inserisciNuovo(categoria1);
		articoloServiceInstance.aggiungiCategoria(articoloInstanceX, categoria1);
		Ordine ordineInstance = new Ordine("Filippo", "Roma Sud");
		ordineServiceInstance.inserisciNuovo(ordineInstance);
		ordineServiceInstance.aggiungiArticolo(ordineInstance, articoloInstanceX);

		System.out.println(".......testCercaTuttiByOrdineArticolo fine: PASSED.............");
	}

	private static void testCalcolaSommaByArticoloDiCategoria(ArticoloService articoloServiceInstance,
			CategoriaService categoriaServiceInstance, OrdineService ordineServiceInstance) throws Exception {
		System.out.println(".......testCalcolaSommaByArticoloDiCategoria inizio.............");

		long nowInMillisecondi = new Date().getTime();
		Articolo articoloInstanceX = new Articolo("Samsumg" + nowInMillisecondi, 500);
		articoloServiceInstance.inserisciNuovo(articoloInstanceX);
		Categoria categoria1 = new Categoria("PC" + nowInMillisecondi);
		categoriaServiceInstance.inserisciNuovo(categoria1);
		articoloServiceInstance.aggiungiCategoria(articoloInstanceX, categoria1);

		System.out.println(".......testCalcolaSommaByArticoloDiCategoria fine: PASSED.............");
	}
}
