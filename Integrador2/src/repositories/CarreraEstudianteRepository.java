package repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entidades.CarreraEstudiante;

public class CarreraEstudianteRepository{

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		informeCarreras(em);
		em.getTransaction().commit();
		em.close();
		emf.close();

	}
	
	//3
	private static void informeCarreras(EntityManager em) {
		@SuppressWarnings("unchecked")
		List <CarreraEstudiante> informe = em.createQuery("SELECT ce "
				+ "FROM Carrera c JOIN CarreraEstudiante ce ON c.id = ce.carrera "
				+ "JOIN Estudiante e ON ce.estudiante = e.documento "
				+ "GROUP BY ce.carrera, ce.estudiante  "
				+ "ORDER BY c.nombre ASC, ce.fechaEgreso").getResultList();
		imprimirInformeCarrera(informe);
	}

	private static void imprimirInformeCarrera(List<CarreraEstudiante> informe) {
		for (int i = 0; i < informe.size(); i++) {
			System.out.println("Reporte de las carreras en orden alfabetico y sus alumnos por orden cronologico de egreso");
			if (informe.get(i).getFechaEgreso() != null) {
			System.out.println("El alumno " + informe.get(i).getEstudiante().getApellido() + " " + informe.get(i).getEstudiante().getNombre()
					+ " a egresado el dia " + informe.get(i).getFechaEgreso() + " de la carrera " + informe.get(i).getCarrera().getNombre());
			}
			else {
				System.out.println("El alumno " + informe.get(i).getEstudiante().getApellido() + " " + informe.get(i).getEstudiante().getNombre()
						+ " esta cursando la carrera " + informe.get(i).getCarrera().getNombre());
			}
		}
		
	}
}
