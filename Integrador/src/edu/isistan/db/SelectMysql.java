package edu.isistan.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectMysql {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}

		String uri = "jdbc:mysql://localhost:3306/exampleDb";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			selectAll(conn); //ejemplos de select
			selectNames(conn); // asi se podria hacer cualquier consulta
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private static void selectNames(Connection conn) throws SQLException {
		String select = "SELECT nombre FROM cliente";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getString(1));	
		}
	}

	private static void selectAll(Connection conn) throws SQLException {
		String select = "SELECT * FROM cliente";
		PreparedStatement ps = conn.prepareStatement(select);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			System.out.println(rs.getInt(1)+ ", " + rs.getString(2) + ", " + rs.getString(3));	
		}
	}

}
