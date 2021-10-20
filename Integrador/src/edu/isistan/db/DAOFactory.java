package edu.isistan.db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
	private String driver;
	private String uri;
	private DAOFactura factura;
	private DAOCliente cliente;
	private DAOProducto producto;
	private DAOFacturaProducto facturaProducto;

	public DAOFactory(String driver) {
		if (driver.equals("mysql")) {
			this.driver = "com.mysql.cj.jdbc.Driver";
			uri = "jdbc:mysql://localhost:3306/exampleDb";
		}else {
			this.driver = "org.apache.derby.jdbc.EmbeddedDriver";
			uri = "jdbc:derby:MyDerbyDb;create=true";
		}
		this.factura = new DAOFactura();
		this.cliente = new DAOCliente();
		this.producto = new DAOProducto();
		this.facturaProducto = new DAOFacturaProducto();
	}
	
	protected void creation() throws FileNotFoundException, IOException {
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
			createTables(conn);
			insertData(conn);
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createTables(Connection conn) throws SQLException { 
		String table1 = "CREATE TABLE cliente(" + "idCliente  INT," + "nombre VARCHAR(500)," + "email VARCHAR(150),"
				+ "PRIMARY KEY(idCliente))";
			conn.prepareStatement(table1).execute();
			conn.commit();
		String table2 = "CREATE TABLE factura(" + "idFactura  INT," + "idCliente INT NOT NULL," + "PRIMARY KEY(idFactura),"
				+ "CONSTRAINT FK_ID_FACTURACLIENTE FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";

			conn.prepareStatement(table2).execute();
			conn.commit();
		String table3 = "CREATE TABLE producto(" + "idProducto INT NOT NULL," + "nombre VARCHAR(45) NOT NULL," + "valor double NOT NULL,"
				+ "PRIMARY KEY(idProducto))";
			conn.prepareStatement(table3).execute();
			conn.commit();
		String table4 = "CREATE TABLE facturaProducto(" + "idFactura INT NOT NULL," + "idProducto INT NOT NULL," + "cantidad INT NOT NULL,"
				+ "CONSTRAINT FK_ID_FACTURAPRODUCTO FOREIGN KEY (idFactura) REFERENCES Factura(idFactura),"
				+ "CONSTRAINT FK_ID_FACTURAPRODUCTOP FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
			conn.prepareStatement(table4).execute();
			conn.commit();

	}
	
	private void insertData(Connection conn) throws FileNotFoundException, SQLException, IOException {
		cliente.addCliente(conn);
		factura.addFactura(conn);
		producto.addProducto(conn);
		facturaProducto.addFacturaProducto(conn);
	}
}
