package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;

public interface ArticoloService {

	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long id) throws Exception;

	public Articolo caricaSingoloElementoEagerCategorie(Long id) throws Exception;

	public void aggiorna(Articolo articoloInstance) throws Exception;

	public void inserisciNuovo(Articolo articoloInstance) throws Exception;

	public void rimuovi(Articolo articoloInstance) throws Exception;

	public void aggiungiCategoria(Articolo articoloInstance, Categoria categoriaInstance) throws Exception;

	public void creaECollegaOrdineEArticolo(Articolo articoloTransientInstance, Categoria categoriaTransientInstance)
			throws Exception;

	// per injection
	public void setArticoloDAO(ArticoloDao articoloDao);
}
