package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.ordine.OrdineDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Ordine;


public interface OrdineService {

	public List<Ordine> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;
	
	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Ordine ordineInstance) throws Exception;

	public void aggiungiArticolo(Ordine cdInstance, Articolo articoloInstance) throws Exception;
	
	public void creaECollegaOrdineEArticolo(Ordine ordineTransientInstance, Articolo articoloTransientInstance) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDao ordineDao);
}
