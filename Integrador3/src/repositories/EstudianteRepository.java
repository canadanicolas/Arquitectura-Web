package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entidades.Estudiante;

public class EstudianteRepository {
	private EntityManagerFactory emf = null;

	public EstudianteRepository() {
		this.emf = Persistence.createEntityManagerFactory("Integrador3");
	}

	public Estudiante getEstudiante(int dni) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Query query = em.createQuery("SELECT e from Estudiante e where dni = :dni");
			query.setParameter("dni", dni);
			Estudiante estudiante = (Estudiante) query.getSingleResult();
			System.out.println(estudiante.toString());
			em.close();
			return estudiante;
		} catch (Exception e) {
			em.close();
			return null;
		}
	}

	// a) dar de alta un estudiante
	public void altaEstudiante(Estudiante e) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(e);
		em.getTransaction().commit();
	}

	// c) recuperar todos los estudiantes, y especificar algún criterio de
	// ordenamiento simple
	public List<Estudiante> recuperarTodosEstudiantes() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			@SuppressWarnings("unchecked")
			List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e ORDER BY documento ASC")
					.getResultList();
			return estudiantes;
		} catch (Exception e) {
			return null;
		}

	}

	// d) recuperar un estudiante, en base a su número de libreta universitaria
	public Estudiante recuperarEstudiantePorLibreta(int numLibreta) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			Estudiante estudiante = (Estudiante) em.createQuery("SELECT e FROM Estudiante e WHERE numeroLibreta = :num")
					.setParameter("num", numLibreta).getResultList().get(0);
			return estudiante;
		} catch (Exception e) {
			return null;
		}
	}

	// e) recuperar todos los estudiantes, en base a su género
	public List<Estudiante> recuperarEstudiantePorGenero(String genero) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			@SuppressWarnings("unchecked")
			List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE genero =:gen")
					.setParameter("gen", genero).getResultList();
			return estudiantes;
		} catch (Exception e) {
			return null;
		}
	}

	// g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad
	// de residencia
	public List<Estudiante> recuperarEstudiantesPorCarreraPorCiudad(String carrera, String ciudad) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		try {
			@SuppressWarnings("unchecked")
			List<Estudiante> estudiantes = em
					.createQuery(
							"SELECT e " + "FROM Estudiante e JOIN CarreraEstudiante ce ON e.documento = ce.estudiante "
									+ "JOIN Carrera c ON ce.carrera = c.id "
									+ "WHERE c.nombre = :carr AND e.ciudadResidencia = :ciud")
					.setParameter("carr", carrera).setParameter("ciud", ciudad).getResultList();
			return estudiantes;
		} catch (Exception e) {
			return null;
		}
	}

	public static List<Estudiante> reporteCarreras(EntityManager em) {
		try {
			@SuppressWarnings("unchecked")

			List<Estudiante> reporte = em
					.createQuery(
							"Select e from carrera c  join c.estudiantes ec  where ec.fechaEgreso is null group by c")
					.getResultList();
			return reporte;
		} catch (Exception e) {
			return null;
		}
	}

}
