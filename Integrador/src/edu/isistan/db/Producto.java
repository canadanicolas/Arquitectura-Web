package edu.isistan.db;

public class Producto {

	private int idProducto;
	private String nombre;
	private int recaudacionTotal;

	public Producto(int idProducto, String nombre, int recaudacionTotal) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.recaudacionTotal = recaudacionTotal;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public int getRecaudacionTotal() {
		return recaudacionTotal;
	}

	@Override
	public String toString() {
		return "Producto:	" + nombre + "		| su recaudacion total es : " + recaudacionTotal + " |";
	}
}
