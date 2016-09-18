package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import data.ConceptoPagoDB;
import data.PagoDB;
import data.PensionDB;

public class DataService {

	@PersistenceContext
	private EntityManager em;

	public DataService()  {
        super();
    }
    
    public List<PagoDB> PagoRFCfind(String cadena)  {
        TypedQuery<PagoDB> query = em.createNamedQuery(PagoDB.FIND_RFC, PagoDB.class).setParameter(1, cadena);
        return query.getResultList();
    }	
	
    public List<ConceptoPagoDB> ConceptoPagoRFCfind(String cadena)  {
		TypedQuery<ConceptoPagoDB> consulta = em.createNamedQuery(ConceptoPagoDB.FIND_RFC, ConceptoPagoDB.class).setParameter(1, cadena);
        return consulta.getResultList();
    }
    
    public List<PensionDB> PensionRFCfind(String cadena)  {
        TypedQuery<PensionDB> query = em.createNamedQuery(PensionDB.FIND_RFC, PensionDB.class).setParameter(1, cadena);
        return query.getResultList();
    }
    
}
