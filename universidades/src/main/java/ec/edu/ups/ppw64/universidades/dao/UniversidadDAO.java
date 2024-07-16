package ec.edu.ups.ppw64.universidades.dao;

import java.util.List;

import ec.edu.ups.ppw64.universidades.model.Universidad;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UniversidadDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Universidad universidad) {
		em.persist(universidad);
	}
	
	public void update(Universidad universidad) {
		em.merge(universidad);
	}
	
	public void remove(int codigo) {
		Universidad universidad = em.find(Universidad.class, codigo);
		em.remove(universidad);
	}
	
	public Universidad read(int codigo) {
		Universidad universidad = em.find(Universidad.class, codigo);
		return universidad;
	}
	
	
	public Universidad getUniversidadPorCodigo(int codigo){
		String jpql = "SELECT c FROM Universidad c WHERE c.codigo = :codigo";
		Query q = em.createQuery(jpql, Universidad.class);
		q.setParameter("codigo", codigo);
		List<Universidad> universidads = q.getResultList();
		if(universidads.size()>0)
			return universidads.get(0);
		return null;
	}
}
