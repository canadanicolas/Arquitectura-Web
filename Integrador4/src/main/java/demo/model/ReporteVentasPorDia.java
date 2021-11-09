package demo.model;

import java.util.ArrayList;
import java.util.List;

// clase que define la entidad del reporte de ventas por dia
public class ReporteVentasPorDia {

	private String fecha;
	private List<Compra> compras;

	//Constructor vacio
	public ReporteVentasPorDia() {
		this.fecha = null;
		this.compras = new ArrayList<>();
	}

	//devuelve la fecha del reporte
	public String getFecha() {
		return fecha;
	}

	//setea la fecha del reporte
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	//devuelve la lista de compras del reporte
	public List<Compra> getCompra() {
		return compras;
	}

	//agrega una compra a la lista del reporte
	public void addCompra(Compra c) {
		this.compras.add(c);
	}

}
