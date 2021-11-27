package demo.model;
/**
 * clase que define la entidad del reporte de los gastos del cliente.
 * @see Cliente
 * @version 10.8
 * 
 */
public class ReporteGastosCliente {
    private Long id;
    private Cliente cliente;
    private int gastos;
    /**
    * <p>Constructor vacio de ReporteGastosCliente</p>
    * @see long Cliente
    */
    public ReporteGastosCliente() {
        this.id = (long) 0;
        this.cliente = null;
        this.gastos = 0;
    }
    /**
     * <p> Devuelve el id del reporte. </p>
     * @return id de tipo long .
     * @see Long 
     */
    public Long getId() {
        return id;
    }
    /**
     * <p> setea el id del reporte. </p>
     * @param id es el id a setear en la variable.
     * @see Long 
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * <p> devuelve el cliente del reporte. </p>
     * @return Cliente.
     * @see Cliente 
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * <p> setea el cliente del reporte. </p>
     * @param cliente es el cliente a setear en la variable.
     * @see Cliente 
     */
    //setea el cliente del reporte
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    /**
     * <p> devuelve los gastos del reporte. </p>
     * @return gastos de tipo primitivo integer.
     */
    //devuelve los gastos del reporte
    public int getGastos() {
        return gastos;
    }
    /**
     * <p> setea los gastos del reporte. </p>
     * @param gasto es la variable con la nueva informacion de tipo integer.
     */
    public void setGastos(int gasto) {
        this.gastos = gasto;
    }
}
