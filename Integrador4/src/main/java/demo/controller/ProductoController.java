package demo.controller;

import java.util.List;
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
import demo.repository.ProductoRepository;

//Clase que administra los sistemas rest de Producto
@RestController
@RequestMapping("producto")
public class ProductoController {
	@Qualifier("productoRepository")
	@Autowired
	private final ProductoRepository repository;

	//Constructor
	public ProductoController(@Qualifier("productoRepository") ProductoRepository repository) {
		this.repository = repository;
	}

	// Hace un get de todos los productos.
	@GetMapping("/getAll")
	public Iterable<Producto> getProductos() {
		return repository.findAll();
	}

	// Hace un get de un producto con el id = al que se pasa por parametro
	@GetMapping("/get/{id}")
	public Optional<Producto> getProducto(@PathVariable Long id) {
		return repository.findById(id);
	}

	// Hace un get del producto mas vendido
	@GetMapping("/masVendido")
	public Producto getProductoMasVendido() {
		List<Producto> productos = repository.productos();
		Producto productoMasVendido = new Producto();
		productos = repository.cantidadProducto();
		productoMasVendido = productos.get(0);
		return productoMasVendido;
	}

	// Agrega un producto
	@PostMapping("/add")
	public Producto newProducto(@RequestBody Producto p) {
		return repository.save(p);
	}

	// Edita el producto con el id que se pasa por parametro
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

	//Borra el producto con el id que se pasa por parametro
	@DeleteMapping("/delete/{id}")
	void deleteProducto(@PathVariable Long id) {
		repository.deleteById(id);
	       //repository.borrarStockProducto(id);
	}
	
}
