package demo.controller;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import demo.model.Producto;
import demo.repository.ProductoRepository;
/**
 * Clase que administra los sistemas rest de Producto.
 * @see ProductoRepository
 * @version 10.8
 * 
 */
@RestController
@RequestMapping("producto")
public class ProductoController {
    @Qualifier("productoRepository")
    @Autowired
    private final ProductoRepository repository;
   /**
   * <p>Constructor de producto</p>
   * @param repository es la clase repositorio de producto.
   * @see ProductoRepository
   */
    //Constructor
    public ProductoController(@Qualifier("productoRepository") ProductoRepository repository) {
        this.repository = repository;
    }
    /**
     * <p> Hace un get de todos los productos. </p>
     * @return un iterable de todos los productos.
     * @see ProductoRepository
     */ 
    @GetMapping("/getAll")
    public Iterable<Producto> getProductos() {
        return repository.findAll();
    }
    /**
     * <p> Hace un get de un producto con el id = al que se pasa por parametro. </p>
     * @param es el id de la clase repositorio de producto.
     * @return opcional de tipo producto.
     * @see ProductoRepository Long
     */ 
    @GetMapping("/get/{id}")
    public Optional<Producto> getProducto(@PathVariable Long id) {
        return repository.findById(id);
    }
    /**
     * <p> Hace un get del producto mas vendido. </p>
     * @return producto.
     * @see ProductoRepository
     */ 
    @GetMapping("/masVendido")
    public Producto getProductoMasVendido() {
        List<Producto> productos = repository.productos();
        Producto productoMasVendido = new Producto();
        productos = repository.cantidadProducto();
        productoMasVendido = productos.get(0);
        return productoMasVendido;
    }
    /**
     * <p> Agrega un producto. </p>
     * @param p es el producto
     * @return el producto agregado.
     * @see ProductoRepository Producto
     */ 
    @PostMapping("/add")
    public Producto newProducto(@RequestBody Producto p) {
        return repository.save(p);
    }
    /**
     * <p>Edita el producto con el id que se pasa por parametro</p>
     * @param p es el producto con la informacion para editar. 
     * @param es el id del producto que se quiere editar. 
     * @return la entidad guardada, ya sea el del id o el que ya venia editado seteandole su id.
     * @see Long ProductoRepository
     */
    @PutMapping("/update/{id}")
    public Producto updateProducto(@RequestBody Producto p, @PathVariable Long id) {
        return repository.findById(id).map(producto -> {
            producto.setNombre(p.getNombre());
            producto.setPrecio(p.getPrecio());
            return repository.save(producto);
        }).orElseGet(() -> {
            p.setId(id);
            return repository.save(p);
        });
    }
    /**
     * <p>Borra el producto con el id que se pasa por parametro</p>
     * @param es el id del producto que se quiere borrar. 
     * @see Long ProductoRepository
     */ 
    @DeleteMapping("/delete/{id}")
    void deleteProducto(@PathVariable Long id) {
        repository.deleteById(id);
           //repository.borrarStockProducto(id);
    }
}
