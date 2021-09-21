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
		
		Carrera c1 = new Carrera(4, "Tudai");
		Carrera c2 = new Carrera(5, "Tupar");
		
		CarreraEstudiantePk cepk1 = new CarreraEstudiantePk(e1.getDocumento(), c1.getId());
		CarreraEstudiantePk cepk2 = new CarreraEstudiantePk(e2.getDocumento(), c2.getId());
		
		CarreraEstudiante ce1 = new CarreraEstudiante(cepk1, e1, c1, LocalDate.now(), LocalDate.of(2023, 5, 7));
		CarreraEstudiante ce2 = new CarreraEstudiante(cepk2, e2, c2, LocalDate.now(), LocalDate.of(2023, 5, 7));
		
		em.persist(e1);
		em.persist(e2);
		
		em.persist(c1);
		em.persist(c2);
		
		em.persist(ce1);
		em.persist(ce2);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
	}

}
