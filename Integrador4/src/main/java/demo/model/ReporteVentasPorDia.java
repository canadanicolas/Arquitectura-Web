package demo.model;
import java.util.ArrayList;
import java.util.List;

/**
 * clase que define la entidad del reporte de ventas por dia.
 * @see ArrayList
 * @version 10.8
 * 
 */
public class ReporteVentasPorDia {
    private String fecha;
    private List<Compra> compras;
    /**
    * <p>Constructor vacio de ReporteVentasPorDia</p>
    * @see ArrayList
    */
    public ReporteVentasPorDia() {
        this.fecha = null;
        this.compras = new ArrayList<>();
    }
    /**
     * <p> devuelve la fecha del reporte. </p>
     * @return string de la fecha.
     */
    public String getFecha() {
        return fecha;
    }
    /**
     * <p> setea la fecha del reporte. </p>
     * @param fecha es la fecha a setear en la variable.
     */ 
    //setea la fecha del reporte
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    /**
     * <p> devuelve la lista de compras del reporte. </p>
     * @param fecha es la fecha a setear en la variable.
     * @return una lista de tipo compra.
     */ 
    public List<Compra> getCompra() {
        return compras;
    }
    /**
     * <p> agrega una compra a la lista del reporte. </p>
     * @param c es la compra agregar.
     */ 
    public void addCompra(Compra c) {
        this.compras.add(c);
    }
}