package data;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entidades.Carrera;
import entidades.CarreraEstudiante;
import entidades.Estudiante;

public class Insert {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Integrador2");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Estudiante e1 = new Estudiante (39435576, "Nicolas", "Cañada", 25, "masculino", "Necochea", 1);
		Estudiante e2 = new Estudiante (123456789, "Jose", "Perez", 75, "masculino", "Napoleofu", 2);
		
		Carrera c1 = new Carrera("Tudai");
		Carrera c2 = new Carrera("Tupar");
		
		altaEstudiante(e1, em);
		altaEstudiante(e2, em);
		
		altaCarrera(c1, em);
		altaCarrera(c2, em);
		
		matricularEstudiante(e1, c1, em);
		matricularEstudiante(e1, c2, em);
		matricularEstudiante(e2, c2, em);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}
	
	//2.a)
	public static void altaEstudiante(Estudiante e, EntityManager em) {
		em.persist(e);
	}
	
	public static void altaCarrera(Carrera c, EntityManager em) {
		em.persist(c);
	}
	//2.b)
	public static void matricularEstudiante(Estudiante e, Carrera c, EntityManager em) {
		CarreraEstudiante ce = new CarreraEstudiante(e, c, LocalDate.now(), null);
		em.persist(ce);
	}

}
