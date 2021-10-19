package it.prova.gestioniordinijpamaven.dao.ordine;

import it.prova.gestioniordinijpamaven.dao.IBaseDAO;
import it.prova.gestioniordinijpamaven.model.Ordine;

public interface OrdineDao extends IBaseDAO<Ordine>{

	public Ordine findByIdFetchingGeneri(Long id) throws Exception;
	
}
