package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DAOFactory factory = new DAOFactory("mysql");
		// DAOFactory factory = new DAOFactory("derby");
		// factory.creation();

		System.out.println(factory.getBestProduct());

		ArrayList<Cliente> arrayBestCustomers = factory.getBestCustomers();
		for (int i = 0; i < arrayBestCustomers.size(); i++) {
			System.out.println(arrayBestCustomers.get(i));
		}

	}

}

/*
 * que instalar y que hacer para que funcione el codigo en un readme
 * 
 */
