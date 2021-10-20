package repositories;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Carrera;
import entidades.Estudiante;
import entidades.CarreraEstudiante;
import entidades.CarreraEstudiantePk;

public class CarreraEstudianteRepository {
	private EntityManagerFactory emf = null;
	private EstudianteRepository estudianteRepository = null;
	private CarreraRepository carreraRepository = null;

	public CarreraEstudianteRepository() {
		this.emf = Persistence.createEntityManagerFactory("Integrador3");
		this.estudianteRepository = new EstudianteRepository();
		this.carreraRepository = new CarreraRepository();
	}

	// b) matricular un estudiante en una carrera

	public void matricularEstudiante(int idEstudiante, int idCarrera, LocalDate ingreso, LocalDate egreso) {
		EntityManager em = emf.createEntityManager();
		Estudiante e = estudianteRepository.getEstudiante(idEstudiante);
		Carrera c = carreraRepository.getCarrera(idCarrera);
		CarreraEstudiante ce = new CarreraEstudiante(e, c, ingreso, egreso);
		em.getTransaction().begin();
		if (egreso.isBefore(ingreso)) { //por si pone las fechas al revez
			ce = new CarreraEstudiante(e, c, egreso, ingreso);
		}
		em.persist(ce);
		em.getTransaction().commit();
		em.close();
	}

}
