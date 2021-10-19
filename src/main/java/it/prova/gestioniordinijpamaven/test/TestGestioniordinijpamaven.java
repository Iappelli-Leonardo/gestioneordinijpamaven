package it.prova.gestioniordinijpamaven.test;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.EntityManagerUtil;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;
import it.prova.gestioniordinijpamaven.service.ArticoloService;
import it.prova.gestioniordinijpamaven.service.CategoriaService;
import it.prova.gestioniordinijpamaven.service.MyServiceFactory;
import it.prova.gestioniordinijpamaven.service.OrdineService;

public class TestGestioniordinijpamaven {

//	public static void main(String[] args) {
//		
//		OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
//		ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
//		CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();
//		
//		try {
//
//			System.out.println("In tabella Ordine ci sono " + ordineServiceInstance.listAll().size() + " elementi.");
//			System.out.println("In tabella Articolo ci sono " + articoloServiceInstance.listAll().size() + " elementi.");
//			System.out.println("In tabella Categoria ci sono " + categoriaServiceInstance.listAll().size() + " elementi.");
//			System.out.println(
//					"**************************** inizio batteria di test ********************************************");
//			System.out.println(
//					"*************************************************************************************************");
//
//			testInserisciNuovoOrdine(ordineServiceInstance);
//
//			testInserisciArticoliCollegoAOrdine(ordineServiceInstance ,articoloServiceInstance);
//
//			testInserisciCategoriaConOrdine(articoloServiceInstance, categoriaServiceInstance);
//
//			
//
//		}catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
//			// main
//			EntityManagerUtil.shutdown();
//		}
//
//	}
//	
//	private static void testInserisciNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
//		System.out.println(".......testInserisciNuovoOrdine inizio.............");
//
//		Ordine ordineInstance = new Ordine("peppe","via ligure 21");
//		ordineServiceInstance.inserisciNuovo(ordineInstance);
//		if (ordineInstance.getId() == null)
//			throw new RuntimeException("testInserisciNuovoOrdine fallito ");
//
//		System.out.println(".......testInserisciNuovoOrdine fine: PASSED.............");
//	}
//	
//	private static void testInserisciArticoliCollegoAOrdine(OrdineService ordineService, ArticoloService articoloService)
//			throws Exception {
//		System.out.println("#################### testInserisciArticoliCollegoAOrdine inizio #######################");
//
//		
//		List<Ordine> listaOrdiniPresenti = ordineService.listAll();
//		if (listaOrdiniPresenti.isEmpty())
//			throw new RuntimeException("testInserisciArticoliCollegoAOrdine fallito: non ci sono ordini a cui collegarci ");
//		//creo un nuovo articolo e ci collego l'ordine
//		Articolo nuovoArticolo = new Articolo("libro", 29);
//		nuovoArticolo.setOrdine(listaOrdiniPresenti.get(0));
//		articoloService.inserisciNuovo(nuovoArticolo);
//		if (nuovoArticolo.getId() == null)
//			throw new RuntimeException("testInserisciArticoliCollegoAOrdine fallito ");
//		if (nuovoArticolo.getOrdine() == null)
//			throw new RuntimeException("testInserisciArticoliCollegoAOrdine fallito: non ha collegato l'ordine ");
//		System.out.println(".......testInserisciArticoliCollegoAOrdine fine: PASSED.............");
//	}
//	
//	private static void testInserisciCategoriaConOrdine(ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance ) throws Exception {
//		System.out.println(".......testInserisciCategoriaConOrdine inizio.............");
//		
//		Articolo articoloInstance = new Articolo("console",236);
//		
//		Categoria categoriaInstance = new Categoria("svago");
//		
//		articoloServiceInstance.creaECollegaOrdineEArticolo(articoloInstance, categoriaInstance);
//
//		if (articoloInstance.getId() == null)
//			throw new RuntimeException("testCreazioneECollegamentoCdInUnSoloColpo fallito: cd non inserito ");
//
//		if (categoriaInstance.getId() == null)
//			throw new RuntimeException("testCreazioneECollegamentoCdInUnSoloColpo fallito: genere non inserito ");
//
//		// ricarico eager per forzare il test
//		Articolo articoloReload = articoloServiceInstance.caricaSingoloElemento(articoloInstance.getId());
//		if (articoloReload.getCategorie().isEmpty())
//			throw new RuntimeException("testCreazioneECollegamentoCdInUnSoloColpo fallito: genere e cd non collegati ");
//
//		System.out.println(".......testInserisciCategoriaConOrdine fine: PASSED.............");
//	}
	
		
	


		public static void main(String[] args) {

			ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
			OrdineService ordineServiceInstance = MyServiceFactory.getOrdineServiceInstance();
			CategoriaService categoriaServiceInstance = MyServiceFactory.getCategoriaServiceInstance();

			try {
				
				System.out.println(
						"*********************************");
 				testInserimentoNuovoOrdine(ordineServiceInstance);
				System.out.println();
//				testInserisciArticoloCollegoAllOrdine(ordineServiceInstance, articoloServiceInstance);
				System.out.println();
//				testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo(articoloServiceInstance, categoriaServiceInstance);
				System.out.println();
				testCreazioneCategoriaArticoloOrdineInUnColpo(ordineServiceInstance, articoloServiceInstance,
						categoriaServiceInstance);
				System.out.println();
	
				System.out.println(
						"*********************************");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// questa è necessaria per chiudere tutte le connessioni quindi rilasciare il
				// main
				EntityManagerUtil.shutdown();
			}

		}

		// crea ORDINE LO AGGIORNO E LO RIMUOVO

		private static void testInserimentoNuovoOrdine(OrdineService ordineServiceInstance) throws Exception {
			System.out.println("######################## testInserimentoNuovoOrdine inizio ########################");

//			creo un nuovo ordine
			Ordine ordineInstance = new Ordine("peppe", "via lombardi 11");

//		 	inserisco l'ordine e controllo che sia stato inserito

			ordineServiceInstance.inserisciNuovo(ordineInstance);
			if (ordineInstance.getId() == null)
				throw new RuntimeException("testInserimentoNuovoOrdine fallito ");
			System.out.println("##################### testInserimentoNuovoOrdine fine: PASSED ########################");
		}
		// crea ARTICOLO COLLEGATO ORDINE

		private static void testInserisciArticoloCollegoAllOrdine(OrdineService ordineService,
				ArticoloService articoloService) throws Exception {
			System.out.println("#################### testInserisciArticoloCollegoAllOrdine inizio #######################");

			List<Ordine> listaOrdiniPresenti = ordineService.listAll();
			if (listaOrdiniPresenti.isEmpty())
				throw new RuntimeException("testInserisciArticolo fallito: non ci sono ordini a cui collegarci ");
			// creo un nuovo articolo e ci collego l'ordine
			Articolo nuovoArticolo = new Articolo("COMPUTER", 500);
			nuovoArticolo.setOrdine(listaOrdiniPresenti.get(0));
			articoloService.inserisciNuovo(nuovoArticolo);
			if (nuovoArticolo.getId() == null)
				throw new RuntimeException("testInserisciArticolo fallito ");
			if (nuovoArticolo.getOrdine() == null)
				throw new RuntimeException("testInserisciArticolo fallito: non ha collegato l'ordine ");
			System.out.println(
					"#################### testInserisciArticoloCollegoAllOrdine fine: PASSED ####################");
		}

		// crea CATEGORIA COLLEGATO ARTICOLO

		private static void testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo(
				ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

			System.out.println(".......testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo inizio.............");

			Articolo articoloInstanceX = new Articolo("Articolo2", 550);

			Categoria categoriaX = new Categoria("Categoria 1");

			articoloServiceInstance.creaECollegaOrdineEArticolo(articoloInstanceX, categoriaX);

			if (articoloInstanceX.getId() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: articolo non inserito ");

			if (categoriaX.getId() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: genere non inserito ");

			// ricarico eager per forzare il test
			Articolo articoloReloaded = articoloServiceInstance
					.caricaSingoloElementoEagerCategorie(articoloInstanceX.getId());
			if (articoloReloaded.getCategorie().isEmpty())
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: genere e articolo non collegati ");

			System.out.println(
					"#################### testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fine: PASSED ####################");
		}

		// crea CATEGORIA COLLEGATO ARTICOLO COLLEGATO ORDINE

		private static void testCreazioneCategoriaArticoloOrdineInUnColpo(OrdineService ordineServiceInstance,
				ArticoloService articoloServiceInstance, CategoriaService categoriaServiceInstance) throws Exception {

			System.out.println(".......testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo inizio.............");

			Ordine ordineX = new Ordine("ORDINE 2", "ORDINE_2");
			ordineServiceInstance.inserisciNuovo(ordineX);

			ordineServiceInstance.caricaSingoloElemento(ordineX.getId());

			Articolo articoloInstanceX = new Articolo("Articolo2", 550);

			articoloInstanceX.setOrdine(ordineX);

			Categoria categoriaX = new Categoria("Categoria 1");
			
			categoriaServiceInstance.inserisciNuovo(categoriaX);
			

			articoloServiceInstance.creaECollegaOrdineEArticolo(articoloInstanceX, categoriaX);

			if (ordineX.getId() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: ordine non inserito ");

			if (articoloInstanceX.getId() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: articolo non inserito ");

			if (categoriaX.getId() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: categoria non inserito ");

			// ricarico eager per forzare il test
			Ordine ordineReloaded = ordineServiceInstance.caricaSingoloElemento(ordineX.getId());
			if (ordineReloaded.getArticoli() == null)
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: genere e articolo non collegati ");

			Articolo articoloReloaded = articoloServiceInstance
					.caricaSingoloElementoEagerCategorie(articoloInstanceX.getId());
			if (articoloReloaded.getCategorie().isEmpty())
				throw new RuntimeException(
						"testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fallito: genere e articolo non collegati ");

			System.out.println(
					"#################### testCreazioneCategoriaECollegamentoArticoloInUnSoloColpo fine: PASSED ####################");
		}
		
		
	}
	

