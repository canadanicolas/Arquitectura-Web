package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;

// interface que define las Query asociadas al cliente
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	//Devuelve los gastos del cliente con la id igual a la pasada por parametro
	@Query("select SUM(p.precio) from Cliente cl join cl.compras co join co.productos p where cl.id =:id")
	public int gastosSegunCliente(Long id);

	//Devuelve el total de las ventas con el producto con id igual a la idProducto pasada por parametro, 
	//cliente con id igual a idCliente y fecha igual a la fecha pasada por parametro.
	@Query("select count(p.id) FROM Cliente cl join cl.compras c join c.productos p where p.id =:idProducto and cl.id =:idCliente and c.fechaDeCompra =:fecha group by p")
	public int ventasProducto(Long idProducto, String fecha, Long idCliente);

	//Elimina la compra del cliente con id igual a idCompra pasada por parametro.
	@Query("delete FROM Compra c where c.id =:idCompra")
	public void eliminarCompra(long idCompra);

	//Devuelve la compra del cliente con id igual a la idCompra pasada por parametro.
	@Query("select c FROM Compra c where c.id =:idCompra")
	public Compra getCompra(long idCompra);

	//Devuelve la lista de productos de la compra del cliente, con id igual a la idCompra pasada por parametro.
	@Query("select c.productos FROM Compra c join c.productos p where c.id =:idCompra")
	public List<Producto> getProductosSegunCompra(Long idCompra);
}
