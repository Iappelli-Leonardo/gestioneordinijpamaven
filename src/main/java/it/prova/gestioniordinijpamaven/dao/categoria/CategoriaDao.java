package it.prova.gestioniordinijpamaven.dao.categoria;

import it.prova.gestioniordinijpamaven.dao.IBaseDAO;
import it.prova.gestioniordinijpamaven.model.Categoria;
import it.prova.gestioniordinijpamaven.model.Ordine;

public interface CategoriaDao extends IBaseDAO<Categoria>{

	public Categoria findByIdFetchingGeneri(Long id) throws Exception;
	
}
