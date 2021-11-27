package demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


/**
 * Clase que define la entidad Compra.
 * 
 * @version 10.8
 * 
 */
@Entity
@Table(name = "compra")
@Data
public class Compra{
    
    @Id
    private Long id;
    @Column
    private String fechaDeCompra;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private List<Producto> productos;
    
    
    //Constructor vacio
    public Compra() {}
    
    /**
      * <p>Constructor con id, fecha de compra y lista de productos</p>
      * @param id es el id de la Compra. 
      * @param fechaDeCompra es la fecha en la que  se hizo esa compra.
      * @see Long Producto  
      */
    public Compra(Long id, String fechaDeCompra) {
        super();
        this.id = id;
        this.fechaDeCompra = fechaDeCompra;
        this.productos = new ArrayList<Producto>();
    }
    
    /**
     * <p>Agrega un producto a la lista de la compra</p>
     * @param p es el producto que se va a agregar a la lista de compras.
     * @see Producto
     */
    public void add(Producto p) {
        productos.add(p);
    }
    
    /**
     * <p>Compara 2 productos para ver si son iguales</p>
     * @param obj es el objeto con el que se quiere comparar.
     * @return boolean true si son iguales o false si no lo son.
     * @see Producto
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Compra other = (Compra) obj;
        if (fechaDeCompra == null) {
            if (other.fechaDeCompra != null)
                return false;
        } else if (!fechaDeCompra.equals(other.fechaDeCompra))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (productos == null) {
            if (other.productos != null)
                return false;
        } else if (!productos.equals(other.productos))
            return false;
        return true;
    }

    /**
     * <p>Devuelve la informacion de un producto.</p>
     * @return int con la informacion del producto.
     * @see Long Producto
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fechaDeCompra == null) ? 0 : fechaDeCompra.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((productos == null) ? 0 : productos.hashCode());
        return result;
    }

}
