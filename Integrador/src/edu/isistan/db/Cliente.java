package edu.isistan.db;

public class Cliente {
	private int idCliente;
	private String nombre;
	private int cantFacturas;

	public Cliente(int id, String nombre, int cantFacturas) {
		this.idCliente = id;
		this.nombre = nombre;
		this.cantFacturas = cantFacturas;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantFacturas() {
		return cantFacturas;
	}

	@Override
	public String toString() {
		return "Cliente:	" + nombre + "		| cantidad de facturas : " + cantFacturas + " |";
	}

}
