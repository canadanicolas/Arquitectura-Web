package demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

/**
 * Clase que define la entidad de Producto.
 * 
 * @version 10.8
 * 
 */
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
    
    /**
      * <p>Constructor con id, nombre y precio del producto</p>
      * @param id es el id del Producto. 
      * @param nombre es el nombre del Producto.
      * @param precio es el precio del Producto.
      * @see Long  
      */
    public Producto(Long id, String nombre, double precio) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }
    
    /**
      * <p>Setea el nombre del producto</p>
      * @param nombre es el nuevo nombre del Producto.
      */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
      * <p>Devuelve el nombre del producto</p>
      * @return el nombre del producto
      */
    public String getNombre() {
        return this.nombre;
    }
    
    /**
      * <p>Setea el precio del producto</p>
      * @param precio es el nuevo precio del Producto.
      */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
      * <p>Devuelve el precio del producto</p>
      * @return el precio del Producto.
      */
    public double getPrecio() {
        return this.precio;
    }

    /**
     * <p>Compara 2 productos para ver si son iguales</p>
     * @param obj es el objeto con el que se quiere comparar.
     * @return boolean true si son iguales o false si no lo son.
     */
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

