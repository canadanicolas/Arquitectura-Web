package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DAOCliente {

	public DAOCliente() {
		super();
	}

	/*
	 * Agrega los datos de los clientes a la tabla clientes en la bbdd, trae los datos desde un archivo .csv 
	 * cuya ruta DEBE SER SETEADA ANTES DE COMENZAR
	 */
	protected static void addCliente(Connection conn) throws SQLException, FileNotFoundException, IOException {
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		CSVParser parser;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("//clientes.csv"));
		for (CSVRecord row : parser) {
			ps.setInt(1, Integer.parseInt(row.get("idCliente")));
			ps.setString(2, row.get("nombre"));
			ps.setString(3, row.get("email"));
			ps.executeUpdate();
		}
		ps.close();
		conn.commit();
	}

	/*
	 * Trae de la bbdd una lista de los 15 clientes que mas veces han sido facturados 
	 */
	public ArrayList<Cliente> getBestCustomers(String driver, String uri) {
		ArrayList<Cliente> arrayBestCustomers = new ArrayList<>();
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);

			String selectClientesFacturados = 
					"SELECT c.idCliente, c.nombre, COUNT(f.idCliente) AS valor "
					+ "FROM cliente c NATURAL JOIN factura f " 
					+ "GROUP BY f.idCliente " 
					+ "ORDER BY valor DESC "
					+ "LIMIT 15";
			PreparedStatement ps = conn.prepareStatement(selectClientesFacturados);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente aux = new Cliente(rs.getInt(1), rs.getString(2), rs.getInt(3));
				arrayBestCustomers.add(aux);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arrayBestCustomers;
	}

}
