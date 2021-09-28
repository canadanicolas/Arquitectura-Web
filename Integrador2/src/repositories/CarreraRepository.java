package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Carrera;

public class CarreraRepository {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		recuperarCarrerasConEstudiantesPorCantidad(em);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}
	
	//2.f)
	private static void recuperarCarrerasConEstudiantesPorCantidad(EntityManager em) {
		@SuppressWarnings("unchecked")
		List <Carrera> carreras = em.createQuery("SELECT c "
				+ "FROM Carrera c JOIN CarreraEstudiante ce ON c.id = ce.carrera "
				+ "GROUP BY c.id "
				+ "ORDER BY COUNT(*) DESC").getResultList();
		System.out.println("Lista de carreras con estudiantes: ");
		carreras.forEach(e-> System.out.println(e));
	}
}
