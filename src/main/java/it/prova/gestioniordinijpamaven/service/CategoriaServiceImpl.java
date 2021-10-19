package it.prova.gestioniordinijpamaven.service;

import java.util.List;

import it.prova.gestioniordinijpamaven.dao.articolo.ArticoloDao;
import it.prova.gestioniordinijpamaven.dao.categoria.CategoriaDao;
import it.prova.gestioniordinijpamaven.model.Articolo;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;

public class CategoriaServiceImpl implements CategoriaService{

	@Override
	public List<Categoria> listAll() throws Exception {
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
	public void aggiorna(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inserisciNuovo(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rimuovi(Categoria categoriaInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void aggiungiCategoria(Categoria categoriaInstance, Articolo articoloInstance) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void creaECollegaCategoriaEArticolo(Categoria categoriaTransientInstance, Articolo articoloTransientInstance)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCategoriaDAO(CategoriaDao categoriaDao) {
		// TODO Auto-generated method stub
		
	}

}
