package demo.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;
import demo.model.Stock;
/**
 * Clase que administra los test de Stock,Producto,Cliente,Compra.
 * @see Stock Compra Producto Cliente
 * @version 10.8
 * 
 */
public class Tests {
    
    private Cliente _defaultCliente;
    private Producto _defaultProducto;
    private Compra _defaultCompra;
    private Stock _defaultStock;

    /**
     * <p>Constructor del test</p>
     * @see Stock Compra Producto Cliente Long
     */
    @BeforeEach
    public void setUp() throws Exception {
        _defaultCliente = new Cliente(Long.valueOf("3") , "Roberto");
        _defaultProducto = new Producto(Long.valueOf("4"), "Helado", (double) 20);
        _defaultCompra = new Compra(Long.valueOf("4"), "2021-11-21");
        _defaultStock = new Stock(Long.valueOf("5"), _defaultProducto, 10);
    }
    
    /**
      * <p>test encargado de verificar que el precio del producto sea mayor a 0</p>
      * @see assertEquals 
      */
    @Test
    public void testProductoPrecioPositivo() {
        System.out.println("testProductoPrecioPositivo");
        assertEquals((_defaultProducto.getPrecio()>0), true);
    }
    
    /**
       * <p>test encargado de verificar que el nombre no sea nulo</p>
       * @see assertEquals 
       */
    @Test
    public void testClienteNombre() {
        System.out.println("testClienteNombre");
        assertEquals((_defaultCliente.getNombre()!=null), true);
    }
    
    /**
       * <p>test encargado de verificar que id sea igual</p>
       * @see assertEquals 
       */
    @Test
    public void testCompraId() {
        System.out.println("testCompraFecha");
        Compra compraAux = new Compra(Long.valueOf("5"), "2021-11-23");
        assertEquals((_defaultCompra.getId()), compraAux.getId());
    }
    /**
       * <p>test encargado de verificar que el nombre sea igual a otro</p>
       * @see assertEquals 
       */
    @Test
    public void testProductoNombreDiferente() {
        System.out.println("testPrecioPositivo");
        Producto productoAux = new Producto(Long.valueOf("5"), "Helado", (double) 80);
        assertEquals(_defaultProducto.getNombre(), productoAux.getNombre());
    }
    /**
        * <p>test encargado de verificar que stock sea mayor a 0</p>
        * @see assertEquals 
        */
    @Test
    public void testStockCantidadMayor0() {
        System.out.println("testStockCantidadMayor0");
        assertEquals((_defaultStock.getCantidad()>0), true);
    }
    /**
        * <p>test encargado de verificar que haya sumado el stock correctamente</p>
        * @see assertEquals 
        */
    @Test
    public void testStockSumarCantidad() {
        System.out.println("testStockSumarCantidad");
        int auxCantidad = _defaultStock.getCantidad();
        _defaultStock.addCantidad(5);
        assertEquals((_defaultStock.getCantidad()), auxCantidad + 5);
    }
}
