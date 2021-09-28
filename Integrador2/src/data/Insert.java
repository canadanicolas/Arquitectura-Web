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
		Estudiante e3 = new Estudiante (35698745, "Lucia", "Ferreyra", 27, "femenino", "Necochea", 3);
		Estudiante e4 = new Estudiante (36786456, "Manuel", "Acosta", 25, "masculino", "Tandil", 4);
		Estudiante e5 = new Estudiante (39876555, "Josefa", "Lopez", 28, "femenino", "Tandil", 5);
		Estudiante e6 = new Estudiante (37555666, "Victoria", "Fernandez", 30, "femenino", "Tandil", 6);
		Estudiante e7 = new Estudiante (40888999, "Pablo", "Pablito", 32, "masculino", "Necochea", 7);
		Estudiante e8 = new Estudiante (78666425, "Fernando", "Asd", 42, "masculino", "Necochea", 8);
		
		
		Carrera c1 = new Carrera("Tudai");
		Carrera c2 = new Carrera("Tupar");
		Carrera c3 = new Carrera("Ingenieria");
		
		altaEstudiante(e1, em);
		altaEstudiante(e2, em);
		altaEstudiante(e3, em);
		altaEstudiante(e4, em);
		altaEstudiante(e5, em);
		altaEstudiante(e6, em);
		altaEstudiante(e7, em);
		altaEstudiante(e8, em);
		
		altaCarrera(c1, em);
		altaCarrera(c2, em);
		altaCarrera(c3, em);
		
		matricularEstudianteViejo(e1, c1, LocalDate.of(2021, 01, 05), LocalDate.of(2021, 03, 25), em);
		matricularEstudianteViejo(e1, c2, LocalDate.of(2021, 03, 15), LocalDate.of(2021, 05, 12), em);
		matricularEstudianteViejo(e2, c2, LocalDate.of(2021, 01, 24), LocalDate.of(2021, 04, 05), em);
		matricularEstudianteViejo(e3, c3, LocalDate.of(2021, 03, 25), LocalDate.of(2020, 07, 22), em);
		matricularEstudianteViejo(e4, c2, LocalDate.of(2020, 02, 17), LocalDate.of(2021, 06, 15), em);
		matricularEstudianteNuevo(e5, c1, em);
		matricularEstudianteNuevo(e5, c2, em);
		matricularEstudianteNuevo(e5, c3, em);
		matricularEstudianteNuevo(e6, c2, em);
		matricularEstudianteNuevo(e7, c1, em);
		matricularEstudianteNuevo(e8, c3, em);
		
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
	public static void matricularEstudianteNuevo(Estudiante e, Carrera c, EntityManager em) {
		CarreraEstudiante ce = new CarreraEstudiante(e, c, LocalDate.now(), null);
		em.persist(ce);
	}
	
	//Si la fecha de ingreso es posterior a la de egreso las da vuelta asumiendo que es un error del que las ingresa ya que no es posible
	public static void matricularEstudianteViejo(Estudiante e, Carrera c, LocalDate ingreso, LocalDate egreso, EntityManager em) {
		if (ingreso.isAfter(egreso)) {
			CarreraEstudiante ce = new CarreraEstudiante(e, c, egreso, ingreso);
			em.persist(ce);
		}else {
			CarreraEstudiante ce = new CarreraEstudiante(e, c, ingreso, egreso);
			em.persist(ce);
		}
		
	}
}
