package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {
	
	@Id
	int documento;
	
	@Column(nullable = false)
	String nombre;
	
	@Column(nullable = false)
	String apellido;
	
	@Column(nullable = false)
	int edad;
	
	@Column(nullable = false)
	String genero;

	@Column(nullable = false)
	String ciudadResidencia;
	
	@Column(nullable = false)
	int numeroLibreta;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera")
	List<CarreraEstudiante> carreras;
	
	public Estudiante() {
		
	}
	
	public Estudiante(int documento, String nombre, String apellido, int edad, String genero, String ciudadResidencia,
			int numeroLibreta) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudadResidencia = ciudadResidencia;
		this.numeroLibreta = numeroLibreta;
		this.carreras = new ArrayList<>();
	}
	
	/*----------------------------------------------Getters y Setters----------------------------------------------*/

	public int getDocumento() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCiudadResidencia() {
		return ciudadResidencia;
	}

	public void setCiudadResidencia(String ciudadResidencia) {
		this.ciudadResidencia = ciudadResidencia;
	}

	public int getNumeroLibreta() {
		return numeroLibreta;
	}

	public void setNumeroLibreta(int numeroLibreta) {
		this.numeroLibreta = numeroLibreta;
	}

	public List<CarreraEstudiante> getCarreras() {
		return new ArrayList<>(carreras);
	}

	public void setCarreras(List<CarreraEstudiante> carreras) {
		this.carreras = carreras;
	}
	
	/*---------------------------------------------- Otros Metodos ----------------------------------------------*/
	
	public void addCarrera(CarreraEstudiante carrera) {
		this.carreras.add(carrera);
	}

	@Override
	public String toString() {
		return "Estudiante [documento: " + documento + ", nombre: " + nombre + ", apellido: " + apellido + ", edad: " + edad
				+ ", genero: " + genero + ", ciudadResidencia: " + ciudadResidencia + ", numeroLibreta: " + numeroLibreta+"]";
	}

}
