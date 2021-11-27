package demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;
import demo.model.ReporteGastosCliente;
import demo.repository.ClienteRepository;

@RestController
@RequestMapping("cliente")

/**
 * Clase que administra los servicios rest de Cliente.
 * 
 * @version 10.8
 * 
 */
public class ClienteController {
	@Qualifier("clienteRepository")
	@Autowired
	private final ClienteRepository repository;

	// Constructor
	public ClienteController(@Qualifier("clienteRepository") ClienteRepository repository) {
		this.repository = repository;
	}
	
	/**
	 * <p> Realiza un get de todos los clientes. </p>
	 * @return Un iterable con todos los clientes.
	 * @see ClienteRepository
	 */ 
	@GetMapping("/getAll")
	public Iterable<Cliente> getClientes() {
		return repository.findAll();
	}
	
	/**
	 * <p>Agrega un  nuevo cliente </p>
	 * @param c es el Cliente que se agregara nuevo.
	 * @return el cliente agregado.
	 * @see ClienteRepository Cliente
	 */
	@PostMapping("/add")
	public Cliente newCliente(@RequestBody Cliente c) {
		return repository.save(c);
	}
	
	
	/**
	 * <p>Edita el cliente con el id que se pasa por parametro</p>
	 * @param c es el Cliente con la informacion para editar. 
	 * @param id es el id del Cliente que se quiere editar. 
	 * @return el cliente editado, ya sea el del id o el que ya venia editado seteandole su id.
	 * @see Long ClienteRepository Cliente 
	 */
	@PutMapping("/update/{id}")
	public Cliente updateCliente(@RequestBody Cliente c, @PathVariable Long id) {
		return repository.findById(id).map(cliente -> {
			cliente.setNombre(c.getNombre());
			return repository.save(cliente);
		}).orElseGet(() -> {
			c.setId(id);
			return repository.save(c);
		});
	}

	/**
	 * <p> Borra el cliente con el id que se pasa por parametro</p>
	 * @param id es el id del Cliente que se quiere borrar.
	 * @see Long ClienteRepository 
	 */
	@DeleteMapping("/delete/{id}")
	void deleteCliente(@PathVariable Long id) {
		repository.deleteById(id);
	}

	/**
	 * <p> Hace un get de las compras del cliente</p>
	 * @return Una lista con las compras del cliente.
	 * @see ClienteRepository ReporteGastosCliente Cliente
	 */
	@GetMapping("/reporteCompras")
	public List<ReporteGastosCliente> getReporteCompras() {
		List<ReporteGastosCliente> reportes = new ArrayList<>();
		List<Cliente> clientes = repository.findAll();
		for (Cliente c : clientes) {
			ReporteGastosCliente reporte = new ReporteGastosCliente();
			reporte.setCliente(c);
			int gastos = repository.gastosSegunCliente(c.getId());
			reporte.setGastos(gastos);
			reportes.add(reporte);
		}
		return reportes;
	}

	/**
	 * <p>Agrega una compra con el id = idCompra al cliente con la id = idCliente</p>
	 * @param c es el Cliente con la informacion para editar. 
	 * @param id es el id del Cliente que se quiere editar. 
	 * @return el cliente editado, ya sea el del id o el que ya venia editado seteandole su id.
	 * @see Long ClienteRepository Cliente
	 */
	@SuppressWarnings("rawtypes")
	@PostMapping("/comprar/{idCompra}/{idCliente}")
	public ResponseEntity compraCliente(@PathVariable Long idCompra, @PathVariable Long idCliente) {

		Optional<Cliente> cliente = repository.findById(idCliente);
		Compra c = repository.getCompra(idCompra);
		System.out.println(c.getId());
		cliente.get().add(c);
		repository.save(cliente.get());
		List<Producto> productos = repository.getProductosSegunCompra(c.getId());
		System.out.println(productos);
		for (Producto p : productos) {
			System.out.println("cantidad para producto" + p.getNombre()
					+ repository.ventasProducto(p.getId(), c.getFechaDeCompra(), idCliente));
			if (repository.ventasProducto(p.getId(), c.getFechaDeCompra(), idCliente) > 3) {
				cliente.get().remove(c);
				repository.save(cliente.get());
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Limite de productos al dia superado");
			}
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Compra realizada");
	}

}
