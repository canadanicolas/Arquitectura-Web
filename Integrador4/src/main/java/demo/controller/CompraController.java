package demo.controller;

import java.util.ArrayList;
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

import demo.model.Compra;
import demo.model.Producto;
import demo.model.ReporteVentasPorDia;
import demo.repository.CompraRepository;

@RestController
@RequestMapping("compra")

/**
 * Clase que administra los sistemas rest de Compra.
 */
public class CompraController {
	@Qualifier("compraRepository")
	@Autowired
	private final CompraRepository repository;


	// Constructor
	public CompraController(@Qualifier("compraRepository") CompraRepository repository) {
		this.repository = repository;
	}

	/**
	 * <p>Hace un get de todos las compras.</p>
	 * @return  Un iterable con todas las compras.
	 * @see CompraRepository
	 */
	@GetMapping("/getAll")
	public Iterable<Compra> getCompras() {
		return repository.findAll();
	}

	/**
	 * <p>Agrega una compra</p>
	 * @param c es la Compra hecha
	 * @param id es el id de la compra
	 * @return La compra recien hecha
	 * @see Long CompraRepository Compra
	 */
	@PostMapping("/")
	public Compra Comprar(@RequestBody Compra c, @PathVariable Long id) {
		List<Compra> compras = repository.cantidadDeCompras(c.getFechaDeCompra(),id);
		if(compras.size() > 0) {
			
		}
		return repository.save(c);
	}
	
	
	@PostMapping("/add")
	public Compra addCompra(@RequestBody Compra c) {
		return repository.save(c);
	}

	/**
	 * <p>Edita la compra con el id que se pasa por parametro</p>
	 * @param c es la compra con la informacion para editar. 
	 * @param id es el id de la compra que se quiere editar. 
	 * @return la compra editada, ya sea la del id o la que ya venia editada seteandole su id.
	 * @see Long CompraRepository Compra
	 */
	@PutMapping("/update/{id}")
	public Compra updateCompra(@RequestBody Compra c, @PathVariable Long id) {
		return repository.findById(id).map(compra -> {
			compra.setFechaDeCompra(c.getFechaDeCompra());
			return repository.save(compra);
		}).orElseGet(() -> {
			c.setId(id);
			return repository.save(c);
		});
	}
	
	/*
	 * <p>Edita la compra con el id que se pasa por parametro</p>
	 * @param idCompra es el id de la compra.
	 * @param idProducto es el id del producto que se quiere comprar. 
	 * @return la compra editada, ya sea la del id o la que ya venia editada seteandole su id.
	 * @see Long CompraRepository Compra Producto
	 */
	@PutMapping("/addProducto/{idCompra}/{idProducto}")
	public Compra addProducto(@PathVariable Long idCompra, @PathVariable Long idProducto) {
		Producto p = repository.getProducto(idProducto);
		Optional<Compra> c = repository.findById(idCompra);

		return repository.findById(idCompra).map(compra -> {
			compra.add(p);
			return repository.save(compra);
		}).orElseGet(() -> {
			c.get().setId(idCompra);
			return repository.save(c.get());
		});
		

	}

	/**
	 * <p>Borra la compra con el id que se pasa por parametro</p>
	 * @param id es el id de la compra que se quiere borrar. 
	* @see Long CompraRepository
	 */
	@DeleteMapping("/delete/{id}")
	void deleteCompra(@PathVariable Long id) {
		repository.deleteById(id);
	}

	/**
	 * <p>Hace un get de los reportes de compras que se hacen por dia</p>
	 * @return Una lista de las compras que se hacen por dia
	 * @see CompraRepository Compra ReporteVentasPorDia
	 */
	@GetMapping("/reporteComprasPorDia")
	public List<ReporteVentasPorDia> getComprasPorDia() {
		boolean flag = false;
		List<ReporteVentasPorDia> reportes = new ArrayList<>();
		List<Compra> compras = repository.comprasOrdenFecha();
		for (Compra c : compras) {
			for (ReporteVentasPorDia r : reportes) {
				if (r.getFecha().equals(c.getFechaDeCompra())) {
					flag = true;
					r.addCompra(c);
				}
			}
			if (flag == false) {
				ReporteVentasPorDia reporte = new ReporteVentasPorDia();
				reporte.addCompra(c);
				reporte.setFecha(c.getFechaDeCompra());
				reportes.add(reporte);
			} else {
				flag = false;
			}
		}
		return reportes;
	}

}

