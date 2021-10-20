package entidades;

import java.util.ArrayList;
import java.util.List;

public class ReporteCarreras {

	private int idCarrera;

	private List<Estudiante> graduados;

	private List<Estudiante> inscriptos;


	public ReporteCarreras(int idCarrera) {
		this.idCarrera = idCarrera;
		this.graduados = new ArrayList<Estudiante>();
		this.inscriptos = new ArrayList<Estudiante>();
	}


	public int getIdCarrera() {
		return idCarrera;
	}

	public List<Estudiante> getInscriptos() {
		return inscriptos;
	}

	public List<Estudiante> getGraduados() {
		return graduados;
	}

	public void setGraduados(List<Estudiante> e) {
		graduados = e;

	}
	public void setInscriptos(List<Estudiante> e) {
		inscriptos = e;

	}

}
