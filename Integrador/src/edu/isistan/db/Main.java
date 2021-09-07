package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		
		DAOFactory factory = new DAOFactory("mysql");
		
		factory.creation(); //inicia la creacion y carga de datos de las tablas de la bbdd

		System.out.println(factory.getBestProduct()); // imprime el producto que mas recauda

		//imprime la lista de los clientes que mas veces han sido facturados
		ArrayList<Cliente> arrayBestCustomers = factory.getBestCustomers();
		for (int i = 0; i < arrayBestCustomers.size(); i++) {
			System.out.println(arrayBestCustomers.get(i));
		}

	}

}
