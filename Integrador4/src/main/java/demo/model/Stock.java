package demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

//clase que define la entidad Stock

@Entity
@Data
public class Stock {
	
	@Id
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Producto producto;
    @Column
    private int cantidad;
    
	//Constructor vacio
    public Stock() {} 
    
	//Constructor con id, producto y cantidad del stock
	public Stock(Long id, Producto producto, int cantidad) {
		super();
		this.id = id;
		this.producto = producto;
		this.cantidad = cantidad;
	}
    
	//Agrega un producto al stock
	public void add(Producto p) {
		producto = p;
	}
    
}
