package restControllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.ReporteCarreras;

@Path("/carrera")
public class RestCarrera {

	// dar de alta un estudiante
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String altaDeEstudiante(Carrera c) {
		Lector.carrera.insertCarrera(c);
		;
		return "La carrera fue guardada con exito";
	}

	/*
	 * f) recuperar las carreras con estudiantes inscriptos, y ordenar por cantidad
	 * de inscriptos.
	 */
	@GET
	@Path("/carrerasSegunInscriptos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carrera> carrerasSegunInscriptos() {
		return Lector.carrera.carrerasSegunInscriptos();
	}

	/*
	 * h) generar un reporte de las carreras, que para cada carrera incluya
	 * información de los inscriptos y egresados por año. Se deben ordenar las
	 * carreras alfabéticamente, y presentar los años de manera cronológica.
	 */
	@GET
	@Path("/reporteCarreras")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ReporteCarreras> reporteCarreras() {
		return Lector.carrera.reporteCarreras();
	}

}
