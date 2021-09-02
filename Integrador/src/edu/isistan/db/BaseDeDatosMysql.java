package edu.isistan.db;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BaseDeDatosMysql {

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
	        String table1 = "CREATE TABLE cliente(" +
	                "idCliente INT,"+
	                "nombre VARCHAR(500)," +
	                "email VARCHAR(150)," + 
	                "PRIMARY KEY(idCliente))" ;
	        String table2 = "CREATE TABLE Factura(" +
	                "idFactura INT,"+
	                "idCliente INT," +
	                "PRIMARY KEY(idFactura),"+
	                "CONSTRAINT FK_ID_FACTURACLIENTE FOREIGN KEY (idCliente) REFERENCES Cliente(IdCliente))";
	        String table3 = "CREATE TABLE Producto(" +
	                "idProducto INT,"+
	                "nombre VARCHAR(45)," +
	                "valor FLOAT,"+
	                "PRIMARY KEY(idProducto))" ;
	        String table4 = "CREATE TABLE FacturaProducto(" +
	                "idFactura INT,"+
	                "idProducto INT,"+
	                "cantidad INT,"+
	                "CONSTRAINT FK_ID_FACTURAPRODUCTO FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),"+
	                "CONSTRAINT FK_ID_FACTURAPRODUCTOP FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
	                
	        createTable(conn,table1);
	        createTable(conn,table2);
	        createTable(conn,table3);
	        createTable(conn,table4);
	        addCliente(conn, 1 , "Cami", "email1");
	        addCliente(conn, 2 , "Martin", "email2");
	        conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    private static void addCliente(Connection conn, int idCliente, String name, String email) throws SQLException {
        
        String insert = "INSERT INTO cliente(idCliente,nombre,email) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(insert);
        ps.setInt(1, idCliente);
        ps.setString(2, name);
        ps.setString(3, email);
        ps.executeUpdate();
        ps.close();
        conn.commit();
    }

    private static void createTable(Connection conn, String table) throws SQLException {
        conn.prepareStatement(table).execute();
    }

}
