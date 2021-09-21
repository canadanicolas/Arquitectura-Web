package integrador2;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class insert {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Estudiante e1 = new Estudiante (39435576, "Nicolas", "Cañada", 25, "masculino", "Necochea", 1);
		Estudiante e2 = new Estudiante (123456789, "Jose", "Perez", 75, "masculino", "Napoleofu", 2);
		
		Carrera c1 = new Carrera("Tudai");
		Carrera c2 = new Carrera("Tupar");
		
		em.persist(e1);
		em.persist(e2);
		
		em.persist(c1);
		em.persist(c2);
		
		insertCarreraEstudiante(e1, c1, em);
		insertCarreraEstudiante(e1, c2, em);
		insertCarreraEstudiante(e2, c2, em);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	public static void insertCarreraEstudiante(Estudiante e, Carrera c, EntityManager em) {
		CarreraEstudiante ce = new CarreraEstudiante(e, c, LocalDate.now(), null);
		em.persist(ce);
	}

}
