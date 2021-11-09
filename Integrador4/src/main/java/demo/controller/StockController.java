package demo.controller;

import java.util.Optional;

import javax.transaction.Transactional;

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

//Clase que administra los servicios rest de Stock
@RestController
@RequestMapping("stock")
public class StockController  {
	
	@Qualifier("stockRepository")
    @Autowired
    private final StockRepository repository;
	
	//Constructor
	public StockController(@Qualifier("stockRepository") StockRepository repository) {
		this.repository = repository;
	}
	
	// Hace un get de todos los stocks
    @GetMapping("/getAll")
    public Iterable<Stock> getStocks() {
        return repository.findAll();
    }
    
	//Agrega un stock del producto con el id igual al pasado por parametro
    @PostMapping("/add/{id}")
    public Stock newStock(@RequestBody Stock s, @PathVariable Long id) {
    	Producto p = repository.getProducto(id);
    	s.add(p);
        return repository.save(s);
    }
	
    //Edita el stock con id igual al pasado por parametro
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
	 
	 //Borra el parametro con id igual al pasado por parametro
	@DeleteMapping("/delete/{id}")
	 void deleteStock(@PathVariable Long id) {
	        repository.deleteById(id);
	    }
	
	//Setea el stock con el id igual al pasado por parametro en 0
	 @PutMapping("/restarStock/{id}") public Stock restarStock(@PathVariable Long id) {
		 	Optional<Stock> stock = repository.findById(id);
		 	if(stock.get().getCantidad()>0) {
		 		stock.get().setCantidad(stock.get().getCantidad()-1);
		 		repository.save(stock.get());
		 	}
		 	return stock.get();
	 }
 
}
