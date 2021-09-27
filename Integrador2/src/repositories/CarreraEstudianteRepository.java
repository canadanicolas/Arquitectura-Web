package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.CarreraEstudiante;

public class CarreraEstudianteRepository {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		informeCarrera(em);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}
	
	//3
	private static void informeCarrera(EntityManager em) {
		@SuppressWarnings("unchecked")
		List <CarreraEstudiante> carreras = em.createQuery("SELECT ce "
				+ "FROM Carrera c JOIN CarreraEstudiante ce ON c.id = ce.carrera "
				+ "JOIN Estudiante e ON ce.estudiante = e.documento "
				+ "GROUP BY ce.carrera, ce.estudiante  "
				+ "ORDER BY c.nombre ASC, ce.fechaEgreso").getResultList();
		System.out.println("Informe completo de las carreras ordenadas por fecha de ingreso y egreso:");
		carreras.forEach(c-> System.out.println(c));
	}
/*SELECT c.nombre, count(ce.estudianteId), count(ce.fechaEgreso), ce.fechaEgreso
FROM Carrera c JOIN CarreraEstudiante ce ON c.id = ce.carreraId 
JOIN Estudiante e ON ce.estudianteId = e.documento 
GROUP BY ce.carreraId, ce.estudianteId
ORDER BY c.nombre ASC, ce.fechaEgreso;*/
}
