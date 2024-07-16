package ec.edu.ups.ppw64.universidades.business;

import java.util.List;

import ec.edu.ups.ppw64.universidades.model.Universidad;
import ec.edu.ups.ppw64.universidades.dao.UniversidadDAO;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUniversidades {
	
	@Inject
	private UniversidadDAO daoUniversidad;

	
	public void guardarUniversidad(Universidad universidad) {
		Universidad car = daoUniversidad.read(universidad.getCodigo());
		if (car != null){
			daoUniversidad.update(universidad);
		}else {
			daoUniversidad.insert(universidad);
		}
	}
	
	
	public void actualizarUniversidad(Universidad universidad) throws Exception {
		Universidad car = daoUniversidad.read(universidad.getCodigo());
		if (car != null){
			daoUniversidad.update(universidad);
		}else {
			throw new Exception("Universidad no existe");
		}
	}
	

	public Universidad getUniversidadPorCodigo(int codigo) throws Exception{
		
		if(codigo<0)
			throw new Exception("Codigo incorrecto");
			
		return daoUniversidad.getUniversidadPorCodigo(codigo);
	}
	

	public void borrarUniversidad(int codigo){
		
		daoUniversidad.remove(codigo);
	}
	
	
}


