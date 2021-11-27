package demo.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import demo.model.Producto;
/**
 * interface que define las Query asociada a producto.
 * @see Producto Long
 * @version 10.8
 * 
 */
public interface ProductoRepository extends JpaRepository<Producto, Long> {
       /**
         * <p>devuelve una lista de producto</p>
         * @return lista de tipo producto.
         * @see List Producto
         */
    @Query("select c.productos FROM Compra c")
    public List<Producto> productos();
    /**
     * <p>devuelve una lista de productos ordenados cantidad</p>
     * @return lista de tipo producto.
     * @see List Producto
     */
    @Query("select p FROM Compra c join c.productos p group by p order by count(p.id)DESC ")
    public List<Producto> cantidadProducto();
}
