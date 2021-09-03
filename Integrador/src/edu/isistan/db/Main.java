package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DAOFactory factory = new DAOFactory("mysql");
		factory.creation();
		
		ConsultaRecaudacion recaudacionMayor = new ConsultaRecaudacion("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/exampleDb");
		recaudacionMayor.conexion();
		
	}

}
