package entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	
	@Id @GeneratedValue
	int id;
	
	@Column(nullable = false)
	String nombre;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "estudiante") 
	List <CarreraEstudiante> estudiantes;
	
	public Carrera() {
		
	}
	
	public Carrera(String nombre) {
		super();
		this.nombre = nombre;
		this.estudiantes = new ArrayList<>();
	}
	
	/*----------------------------------------------Getters y Setters----------------------------------------------*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<CarreraEstudiante> getEstudiantes() {
		return new ArrayList<>(estudiantes);
	}

	public void setEstudiantes(List<CarreraEstudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	/*---------------------------------------------- Otros Metodos ----------------------------------------------*/

	public void addEstudiante(CarreraEstudiante estudiante) {
		this.estudiantes.add(estudiante);
	}
	
	@Override
	public String toString() {
		return "Carrera [nombre: " + nombre + "]";
	}
	
}
