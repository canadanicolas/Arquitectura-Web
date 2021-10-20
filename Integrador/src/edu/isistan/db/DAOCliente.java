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

public class DAOCliente {
	public DAOCliente() {
		super();
	}

	
	protected static void addCliente(Connection conn) throws SQLException, FileNotFoundException, IOException {
		String insert = "INSERT INTO cliente (idCliente, nombre, email) VALUES(?,?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		CSVParser parser;
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\clientes.csv"));
			for (CSVRecord row :parser) {
				ps.setInt(1, Integer.parseInt(row.get("idCliente")));
				ps.setString(2, row.get("nombre"));
				ps.setString(3, row.get("email"));
				ps.executeUpdate();
			}
		ps.close();
		conn.commit();
	}
	
}
