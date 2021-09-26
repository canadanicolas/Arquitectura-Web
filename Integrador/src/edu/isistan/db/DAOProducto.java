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

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DAOProducto {

	public DAOProducto() {
		super();
	}

	/*
	 * Agrega los datos de los producto a la tabla producto en la bbdd, trae los datos desde un archivo .csv 
	 * cuya ruta DEBE SER SETEADA ANTES DE COMENZAR
	 */
	protected static void addProducto(Connection conn) throws SQLException, FileNotFoundException, IOException {
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		CSVParser parser;
		parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("//productos.csv"));
		for (CSVRecord row : parser) {
			ps.setInt(1, Integer.parseInt(row.get("idProducto")));
			ps.setString(2, row.get("nombre"));
			ps.setFloat(3, Float.parseFloat(row.get("valor")));
			ps.executeUpdate();
		}

		ps.close();
		conn.commit();
	}

	public Producto getBestProduct(String driver, String uri) {
		Producto bestProducto = new Producto(0, "", 0);
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

			String selectRecaudacion = 
					"SELECT p.idProducto, p.nombre, sum(fp.cantidad * p.valor) as recaudacion "
					+ "FROM facturaProducto fp NATURAL JOIN producto p " 
					+ "GROUP BY fp.idProducto, p.nombre "
					+ "ORDER BY recaudacion DESC " 
					+ "LIMIT 1";
			PreparedStatement ps = conn.prepareStatement(selectRecaudacion);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				bestProducto = new Producto(rs.getInt(1), rs.getString(2), rs.getInt(3));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bestProducto;
	}
	
	
}
