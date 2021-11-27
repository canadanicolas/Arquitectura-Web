package demo.model;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Data;
/**
 * Clase que define la entidad stock.
 * 
 * @version 10.8
 *
 */
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
    /**
     * <p>Constructor vacio</p>
     */
    public Stock() {} 
   /**
   * <p>Constructor con id, producto y cantidad del stock</p>
   * @param id es el id del stock. 
   * @param producto es el producto del cual tratamos el stock. 
   * @param cantidad es la cantidad de stock de ese producto.
   * @see Long Producto
   */
    public Stock(Long id, Producto producto, int cantidad) {
        super();
        this.id = id;
        this.producto = producto;
        this.cantidad = cantidad;
    }
    /**
       * <p>Agrega un producto al stock</p>
       * @param producto es el producto agregar. 
       * @see Long Producto
       */
    public void add(Producto p) {
        producto = p;
    }
    public void addCantidad(int i) {
    	this.cantidad = (this.cantidad + i);
    }
}
