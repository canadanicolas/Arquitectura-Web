package entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class CarreraEstudiante {
	
	@EmbeddedId
	CarreraEstudiantePk id;
	
	@ManyToOne
    @MapsId("documento") 
    @JoinColumn(name = "estudianteId")
    private Estudiante estudiante;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "carreraId")
    private Carrera carrera;
	
	@Column(nullable = false)
	LocalDate fechaIngreso;
	
	@Column(nullable = true)
	LocalDate fechaEgreso;
	
	public CarreraEstudiante() {
		
	}

	public CarreraEstudiante(Estudiante estudiante, Carrera carrera, LocalDate fechaIngreso, LocalDate fechaEgreso) {
		super();
		this.id = new CarreraEstudiantePk(estudiante.getDocumento(), carrera.getId());
		this.estudiante = estudiante;
		this.carrera = carrera;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
	}
	
	/*----------------------------------------------Getters y Setters----------------------------------------------*/

	public CarreraEstudiantePk getId() {
		return id;
	}

	public void setId(CarreraEstudiantePk id) {
		this.id = id;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}
	
	public LocalDate getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	
	/*---------------------------------------------- Otros Metodos ----------------------------------------------*/

	@Override
	public String toString() {
		return "CarreraEstudiante [estudiante: " + estudiante + ", carrera: " + carrera + ", fechaEgreso: "
				+ fechaEgreso + "]";
	}
    	
	
}
