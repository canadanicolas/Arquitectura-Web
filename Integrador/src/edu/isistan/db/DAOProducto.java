package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class DAOProducto {

	public DAOProducto() {
		super();
	}

	protected static void addProducto(Connection conn) throws SQLException, FileNotFoundException, IOException {
		String insert = "INSERT INTO producto (idProducto, nombre, valor) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		CSVParser parser;
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\productos.csv"));
			for (CSVRecord row : parser) {
				ps.setInt(1, Integer.parseInt(row.get("idProducto")));
				ps.setString(2, row.get("nombre"));
				ps.setFloat(3, Float.parseFloat(row.get("valor")));
				ps.executeUpdate();
			}

		ps.close();
		conn.commit();
	}
	
}
