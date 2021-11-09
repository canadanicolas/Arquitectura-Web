package demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//clase que define la entidad de Producto

@Entity
@Data
public class Producto{
	
	@Id
	private Long id;
	@Column
	private String nombre;
	@Column
	private double precio;
	
	//Constructor vacio
	public Producto() {}
	
	//Constructor con id, nombre y precio del producto
	public Producto(Long id, String nombre, double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	//setea el nombre del producto
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//devuelve el nombre del producto
	public String getNombre() {
		return this.nombre;
	}
	
	//setea el precio del producto
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	//devuelve el precio del producto
	public double getPrecio() {
		return this.precio;
	}

	//compara dos productos para ver si son iguales
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(precio) != Double.doubleToLongBits(other.precio))
			return false;
		return true;
	}

}

