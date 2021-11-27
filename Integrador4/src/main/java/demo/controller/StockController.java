package demo.controller;
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
import demo.model.Stock;
import demo.repository.StockRepository;
/**
 * Clase que administra los servicios rest de Stock.
 * @see StockRepository
 * @version 10.8
 * 
 */
@RestController
@RequestMapping("stock")
public class StockController  {
    @Qualifier("stockRepository")
    @Autowired
    private final StockRepository repository;
     /**
     * <p>Constructor de stock</p>
     * @param repository es la clase repositorio de stock.
     * @see StockRepository
     */
    public StockController(@Qualifier("stockRepository") StockRepository repository) {
        this.repository = repository;
    }
    /**
     * <p> Hace un get de todos los stocks. </p>
     * @return un iterable de todos los stock.
     * @see StockRepository
     */ 
    @GetMapping("/getAll")
    public Iterable<Stock> getStocks() {
        return repository.findAll();
    }
    /**
     * <p> Agrega un stock del producto con el id igual al pasado por parametro. </p>
     * @param s es el stock.
     * @param es el id de la clase repositorio de stock.
     * @return el stock agregado.
     * @see StockRepository Long
     */ 
    @PostMapping("/add/{id}")
    public Stock newStock(@RequestBody Stock s, @PathVariable Long id) {
        Producto p = repository.getProducto(id);
        s.add(p);
        return repository.save(s);
    }
    /**
     * <p>Edita el cliente con el id que se pasa por parametro</p>
     * @param s es el stock con la informacion para editar. 
     * @param es el id del producto que se quiere editar. 
     * @return la entidad guardada, ya sea el del id o el que ya venia editado seteandole su id.
     * @see Long StockRepository
     */
     @PutMapping("/update/{id}") public Stock updateStock(@RequestBody Stock s, @PathVariable Long id) {
            return repository.findById(id)
                    .map(stock -> {
                        stock.setCantidad(s.getCantidad());
                        return repository.save(stock);
                    })
                    .orElseGet(() -> {
                        s.setId(id);
                        return repository.save(s);
                    });
     } 
    /**
    * <p>Borra el parametro con id igual al pasado por parametro</p>
    * @param es el id del stock que se quiere borrar. 
    * @see Long StockRepository
    */ 
    @DeleteMapping("/delete/{id}")
     void deleteStock(@PathVariable Long id) {
            repository.deleteById(id);
        }
    /**
     * <p>Setea el stock con el id igual al pasado por parametro en 0</p>
     * @param es el id del stock que se quiere reiniciar. 
     * @return stock modificado.
     * @see Long StockRepository
     */
     @PutMapping("/restarStock/{id}") public Stock restarStock(@PathVariable Long id) {
             Optional<Stock> stock = repository.findById(id);
             if(stock.get().getCantidad()>0) {
                 stock.get().setCantidad(stock.get().getCantidad()-1);
                 repository.save(stock.get());
             }
             return stock.get();
     }
}
