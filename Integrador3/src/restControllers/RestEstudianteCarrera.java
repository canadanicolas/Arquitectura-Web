package restControllers;

import java.time.LocalDate;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidades.CarreraEstudiantePk;

@Path("/matricular")
public class RestEstudianteCarrera {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String matricular(CarreraEstudiantePk ce, LocalDate ingreso, LocalDate egreso) {
		Lector.carreraEstudiante.matricularEstudiante(ce.getEstudianteId(), ce.getCarreraId(), ingreso, egreso);
		return "El usuario fue guardado con exito";
		
	}
}
