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
		List <Carrera> carreras = em.createQuery("Aca va la consulta").getResultList();
		System.out.println("Lista de carreras con estudiantes: ");
		carreras.forEach(e-> System.out.println(e));
	}

}
