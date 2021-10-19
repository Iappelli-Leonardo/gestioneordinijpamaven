package it.prova.gestioniordinijpamaven.dao.articolo;

import it.prova.gestioniordinijpamaven.dao.IBaseDAO;
import it.prova.gestioniordinijpamaven.model.Articolo;

public interface ArticoloDao extends IBaseDAO<Articolo>{

	public Articolo findByIdFetchingGeneri(Long id) throws Exception;
}
