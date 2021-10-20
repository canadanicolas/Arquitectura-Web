package restControllers;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import repositories.CarreraRepository;
import repositories.EstudianteRepository;
import repositories.CarreraEstudianteRepository;

@WebListener
public class Lector implements ServletContextListener {

	public static EstudianteRepository estudiante = null;
	public static CarreraRepository carrera = null;
	public static CarreraEstudianteRepository carreraEstudiante = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		estudiante = new EstudianteRepository();
		carrera = new CarreraRepository();
		carreraEstudiante = new CarreraEstudianteRepository();

	}
}
