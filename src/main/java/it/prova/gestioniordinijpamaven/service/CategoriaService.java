package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioniordinijpamaven.dao.categoria.CategoriaDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;

public interface CategoriaService {
	
	public List<Categoria> listAll() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public Ordine caricaSingoloElementoEagerArticoli(Long id) throws Exception;

	public void aggiorna(Categoria categoriaInstance) throws Exception;

	public void inserisciNuovo(Categoria categoriaInstance) throws Exception;

	public void rimuovi(Categoria categoriaInstance) throws Exception;

	public void aggiungiCategoria(Categoria categoriaInstance, Articolo articoloInstance) throws Exception;

	public void creaECollegaCategoriaEArticolo(Categoria categoriaTransientInstance, Articolo articoloTransientInstance)
			throws Exception;

	// per injection
	public void setCategoriaDAO(CategoriaDao categoriaDao);
}
