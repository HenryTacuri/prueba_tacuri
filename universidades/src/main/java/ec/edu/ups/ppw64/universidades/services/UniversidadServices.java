package ec.edu.ups.ppw64.universidades.services;

import java.util.List;

import ec.edu.ups.ppw64.universidades.business.GestionUniversidades;
import ec.edu.ups.ppw64.universidades.model.Universidad;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api")
public class UniversidadServices {
	
	@Inject
	private GestionUniversidades gUniversidades;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Universidad universidad) {
		try{
			gUniversidades.guardarUniversidad(universidad);
			ErrorMessage error = new ErrorMessage(1, "OK");
			return Response.status(Response.Status.CREATED)
					.entity(error)
					.build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Universidad universidad) {
		try{
			gUniversidades.actualizarUniversidad(universidad);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("codigo") int codigo) {
		try{
			gUniversidades.borrarUniversidad(codigo);
			return "OK";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	//@Produces("application/json")
	public Response leer(@QueryParam("codigo") int codigo) {
		try{
			Universidad universidad = gUniversidades.getUniversidadPorCodigo(codigo);
			return Response.ok(universidad).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Universidad no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}

}
