package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;

/**
 * Interface que define las Query asociadas al cliente
 * 
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * <p>Devuelve los gastos del cliente con la id igual a la pasada por parametro</p>
     * @param id es el id del cliente.
     * @return los gastos segun el id que pasaron del cliente.
     * @see Long
     */
    @Query("select SUM(p.precio) from Cliente cl join cl.compras co join co.productos p where cl.id =:id")
    public int gastosSegunCliente(Long id);

    /**
     * <p>Devuelve el total de las ventas con el producto con id igual a la idProducto pasada 
     * por parametro, cliente con id igual a idCliente y fecha igual a la fecha pasada por parametro.</p>
     * @param idProducto es el id del producto que quiero ver el total de ventas.
     * @param fecha es la fecha de la compra.
     * @param idCliente es el id del cliente.
     * @return total de la cantidad de ventas.
     * @see Long
     */
    @Query("select count(p.id) FROM Cliente cl join cl.compras c join c.productos p where p.id =:idProducto and cl.id =:idCliente and c.fechaDeCompra =:fecha group by p")
    public int ventasProducto(Long idProducto, String fecha, Long idCliente);

    /**
     * <p>Elimina la compra del cliente con id igual a idCompra pasada por parametro.</p>
     * @param idCompra es el id de la compra que quiero borrar.
     * @see Long
     */
    @Query("delete FROM Compra c where c.id =:idCompra")
    public void eliminarCompra(long idCompra);

    /**
     * <p>Devuelve la compra del cliente con id igual a la idCompra pasada por parametro.</p>
     * @param idCompra es el id de la compra que quiero traer.
     * @return la compra que estoy buscando segun id.
     * @see Compra
     */
    @Query("select c FROM Compra c where c.id =:idCompra")
    public Compra getCompra(long idCompra);

    /**
     * <p>Devuelve la lista de productos de la compra del cliente, con id igual a la idCompra pasada por parametro.</p>
     * @param idCompra es el id de la compra del cliente.
     * @return lista de productos con la compra del cliente.
     * @see Producto
     */
    @Query("select c.productos FROM Compra c join c.productos p where c.id =:idCompra")
    public List<Producto> getProductosSegunCompra(Long idCompra);
}
