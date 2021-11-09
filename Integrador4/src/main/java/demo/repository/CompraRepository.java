package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;

//interface que define las Query asociada a compra

public interface CompraRepository extends JpaRepository<Compra, Long> {

	// Devuelve una lista de los productos de esta compra
	@Query("select c from Compra c order by c.fechaDeCompra")
	public List<Compra> comprasOrdenFecha();

	//devuelve la cantidad de compras realizadas por el cliente con id igual al pasado por parametro
	//en la fecha igual a fechaCompra pasada por parametro
	@Query("select c.compras from Cliente c where id =: id and fechaDeCompra =: fechaCompra ")
	public List<Compra> cantidadDeCompras(String fechaCompra, Long id);

	//Devuelve el total de ventas del producto con id igual a idProducto que hice el cliente con id igual a idCliente
	@Query("select count(p.id) FROM Cliente cl join cl.compras c join c.productos p where p.id =:idProducto and cl.id =:idCliente group by p")
	public int ventasProducto(Long idProducto, Long idCliente);

	//devuelve un cliente con id igual al pasado por parametro
	@Query("select c FROM Cliente c where c.id =:idCliente ")
	public Cliente recuperarCLiente(Long idCliente);

	//devuelve un producto con id igual al pasado por parametro
	@Query("select p FROM Producto p where p.id =:id")
	public Producto getProducto(Long id);
	
}
