package demo.model;

// clase que define la entidad del reporte de los gastos del cliente
public class ReporteGastosCliente {

	private Long id;
	private Cliente cliente;
	private int gastos;

	//Constructor vacio
	public ReporteGastosCliente() {
		this.id = (long) 0;
		this.cliente = null;
		this.gastos = 0;
	}

	//Devuelve el id del reporte
	public Long getId() {
		return id;
	}

	//setea el id del reporte
	public void setId(Long id) {
		this.id = id;
	}

	//devuelve el cliente del reporte
	public Cliente getCliente() {
		return cliente;
	}

	//setea el cliente del reporte
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//devuelve los gastos del reporte
	public int getGastos() {
		return gastos;
	}

	//setea los gastos del reporte
	public void setGastos(int gasto) {
		this.gastos = gasto;
	}
	
	
	
	
	
	
	
}
