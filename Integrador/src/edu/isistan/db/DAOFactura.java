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

public class DAOFactura {
	public DAOFactura() {
		super();
	}

	protected static void addFactura(Connection conn) throws SQLException, FileNotFoundException, IOException {
		String insert = "INSERT INTO factura (idFactura, idCliente) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		CSVParser parser;
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("C:\\facturas.csv"));
			for (CSVRecord row :parser) {
				ps.setInt(1, Integer.parseInt(row.get("idFactura")));
				ps.setInt(2, Integer.parseInt(row.get("idCliente")));
				ps.executeUpdate();
			}
		ps.close();
		conn.commit();
	}
}
