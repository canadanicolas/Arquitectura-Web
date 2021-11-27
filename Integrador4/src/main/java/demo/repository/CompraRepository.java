package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;


/**
 * Interface que define las Query asociada a compra
 * 
 */
public interface CompraRepository extends JpaRepository<Compra, Long> {

    /**
     * <p>Devuelve una lista de los productos de esta compra.</p>
     * @return una lista .
     */
    @Query("select c from Compra c order by c.fechaDeCompra")
    public List<Compra> comprasOrdenFecha();

    /**
     * <p>Devuelve la lista de compras realizadas por el cliente con id igual al pasado por parametro
     * en la fecha igual a fechaCompra pasada por parametro.</p>
     * @param id es el id del Cliente que quiero ver las compras.
     * @param fechaCompra es la fecha de la posible compra.
     * @return lista de la compra que realizo el cliente.
     * @see Long Compra
     */
    @Query("select c.compras from Cliente c where id =: id and fechaDeCompra =: fechaCompra ")
    public List<Compra> cantidadDeCompras(String fechaCompra, Long id);

    /**
     * <p>Devuelve el total de ventas del producto con id igual a idProducto que hace el cliente con id igual a idCliente.</p>
     * @param idProducto es el id del producto que quiero ver el total de ventas.
     * @param idCliente es el id del cliente que del que quiero ver sus compras.
     * @return total de ventas del producto.
     * @see Long
     */
    @Query("select count(p.id) FROM Cliente cl join cl.compras c join c.productos p where p.id =:idProducto and cl.id =:idCliente group by p")
    public int ventasProducto(Long idProducto, Long idCliente);

    /**
     * <p>Devuelve un cliente con id igual al pasado por parametro.</p>
     * @param idCliente del cliente que quiero traer.
     * @return el Cliente que estoy buscando.
     * @see Long Cliente
     */
    @Query("select c FROM Cliente c where c.id =:idCliente ")
    public Cliente recuperarCLiente(Long idCliente);

    /**
     * <p>Devuelve un producto con id igual al pasado por parametro.</p>
     * @param id del producto que quiero traer.
     * @return el producto  que estoy buscando.
     * @see Long Producto
     */
    @Query("select p FROM Producto p where p.id =:id")
    public Producto getProducto(Long id);
    
}
