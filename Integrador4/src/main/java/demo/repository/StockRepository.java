package demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import demo.model.Producto;
import demo.model.Stock;
/**
 * interface que define las Query asociada a stock.
 * @see Stock Long
 * @version 10.8
 * 
 */
public interface StockRepository extends JpaRepository<Stock, Long> {
       /**
         * <p>devuelve el producto con id igual a la pasada por parametro</p>
         * @param id del producto a seleccionar para devolver.
         * @return producto.
         * @see Long 
         */
    @Query("select p FROM Producto p where p.id =:id")
    public Producto getProducto(Long id);
}