package edu.isistan.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaRecaudacion {
	private String driver;
	private String uri;
	
	public ConsultaRecaudacion(String driver, String uri) {
		this.driver = driver;
		this.uri = uri;
	}
	
	public void conexion() {
		
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
			consulta(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void consulta(Connection conn) throws SQLException {
		String select = "SELECT p.nombre, sum(fp.cantidad * p.valor) as recaudacion "
				+ "FROM facturaProducto fp NATURAL JOIN producto p "
				+ "GROUP BY fp.idProducto, p.nombre "
				+ "ORDER BY recaudacion DESC "
				+ "LIMIT 1";
		
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("El producto que mas recaudo fue: " + rs.getString(1) + " con un total de $ " + rs.getInt(2));	
		}
	}

}
