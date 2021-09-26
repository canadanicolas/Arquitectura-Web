package integrador2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Select {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		recuperarTodosEstudiantes(em);
		recuperarEstudiantePorLibreta(1, em);
		recuperarEstudiantePorGenero("masculino", em);
		recuperarCarrerasConEstudiantesPorCantidad(em);
		recuperarEstudiantesPorCarreraPorCiudad("TUDAI", "tandil", em);
		informeCarrera(em);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}

	//2.c)
	@SuppressWarnings({"unchecked" })
	private static void recuperarTodosEstudiantes(EntityManager em) {
		List <Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e ORDER BY documento ASC").getResultList();
		System.out.println("Lista de todos los estudiantes:");
		estudiantes.forEach(e-> System.out.println(e));
	}
	
	//2.d)
	private static void recuperarEstudiantePorLibreta(int numLibreta, EntityManager em) {
		Estudiante estudiante = (Estudiante) em.createQuery("SELECT e FROM Estudiante e WHERE numeroLibreta ='"+numLibreta+"'").getResultList().get(0);
		System.out.println("Estudiante con el numero de libreta: " + numLibreta);
		System.out.println(estudiante);
	}
	
	//2.e)
	private static void recuperarEstudiantePorGenero(String genero, EntityManager em) {
		@SuppressWarnings("unchecked")
		List <Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e WHERE genero ='"+genero +"'").getResultList();
		System.out.println("Lista de estudiantes con el genero: " + genero);
		estudiantes.forEach(e-> System.out.println(e));
	}
	
	//2.f)
	private static void recuperarCarrerasConEstudiantesPorCantidad(EntityManager em) {
		@SuppressWarnings("unchecked")
		List <Carrera> carreras = em.createQuery("SELECT c, COUNT(ce.estudianteId) FROM Carrera c NATURAL JOIN CarreraEstudiante ce+"
				+ "WHERE cd.estudianteID is NOT NULL ORDER BY COUNT(ce.estudianteId) DESC ").getResultList();
		System.out.println("Lista de carreras con estudiantes: ");
		carreras.forEach(e-> System.out.println(e));
	}
	//2.g)
	private static void recuperarEstudiantesPorCarreraPorCiudad(String carrera, String ciudad, EntityManager em) {
		@SuppressWarnings("unchecked")
		List <Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e NATURAL JOIN CarreraEstudiante ce NATURAL JOIN carrera c+"
				+ "WHERE e.ciudadResidencia= '"+ ciudad+"' AND c.nombre='"+carrera+"'  ORDER BY e.apellido ASC").getResultList();
		System.out.println("Estudiantes de la carrera"+carrera+" que reciden en la cidad "+ciudad+":");
		estudiantes.forEach(e-> System.out.println(e));
	}
	//3
	private static void informeCarrera(EntityManager em) {
		@SuppressWarnings("unchecked")
		List <Carrera> carreras = em.createQuery("SELECT c, e, ce.fechaIngreso, isnull(cd.fechaEgreso,'')  FROM Carrera c NATURAL JOIN CarreraEstudiante ce NATURAL JOIN Estudiante e+"
				+ "*/GROUP BY c.nombre*/  ORDER BY c.nombre, cd.fechaEgreso, ce.fechaIngreso ASC").getResultList();
		System.out.println("Informe completo de las carreras ordenadas por fecha de ingreso y egreso:");
		carreras.forEach(c-> System.out.println(c));
	}
}
