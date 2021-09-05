package edu.isistan.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.event.SwingPropertyChangeSupport;

public class Consulta {
	private String driver;
	private String uri;

	public Consulta(String driver, String uri) {
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
			recaudacionMayor(conn);
			clienteMasFacturado(conn);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void recaudacionMayor(Connection conn) throws SQLException {
		String selectRecaudacion = "SELECT p.nombre, sum(fp.cantidad * p.valor) as recaudacion "
				+ "FROM facturaProducto fp NATURAL JOIN producto p "
				+ "GROUP BY fp.idProducto, p.nombre "
				+ "ORDER BY recaudacion DESC "
				+ "LIMIT 1";
		PreparedStatement ps = conn.prepareStatement(selectRecaudacion);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("El producto que mas recaudo fue: " + rs.getString(1) + " con un total de $ " + rs.getInt(2));
		}
	}
	private static void clienteMasFacturado(Connection conn) throws SQLException {
		String selectClientesFacturados = "SELECT c.idCliente, c.nombre, COUNT(f.idCliente) AS valor "
				+ "FROM cliente c NATURAL JOIN factura f "
				+ "GROUP BY f.idCliente "
				+ "ORDER BY valor DESC ";
		PreparedStatement ps = conn.prepareStatement(selectClientesFacturados);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println("El usuario: " + rs.getString(2) + " con un total de " + rs.getInt(3)+" facturas");
		}
	}
}
