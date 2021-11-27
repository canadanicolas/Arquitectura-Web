package demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * Clase que define la entidad Cliente.
 * 
 * @version 10.8
 * 
 */
@Entity
@Data
public class Cliente {

    @Id
    private Long id;
    @Column
    private String nombre;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idCompra", referencedColumnName = "id")
    private List<Compra> compras;

    //Constructor vacio
        public Cliente() {} 

      /**
      * <p>Constructor de un cliente con id y nombre</p>
      * @param id es el id del Cliente. 
      * @param nombre es el nombre del Cliente.
      * @see Long Compra
      */
    public Cliente(Long id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.compras = new ArrayList<Compra>();
    } 

    /**
     * <p>Agrega una compra al cliente</p>
     * @param c es la compra del Cliente que se va a agregar a su lista.
     * @see Compra
     */
    public void add(Compra c) {
        compras.add(c);
    }

    /**
     * <p>Remueve una compra al cliente. </p>
     * @param c es la compra del Cliente que se va a eliminar a su lista.
     * @see Compra
     */
    public void remove(Compra c) {
        compras.remove(c);
    }

}
