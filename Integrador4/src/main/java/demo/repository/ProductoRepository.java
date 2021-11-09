package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import demo.model.Producto;

//interface que define las Query asociada a producto

public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
	//devuelve una lista de productos
	@Query("select c.productos FROM Compra c")
	public List<Producto> productos();

	//devuelve una lista de productos ordenados cantidad
	@Query("select p FROM Compra c join c.productos p group by p order by count(p.id)DESC ")
	public List<Producto> cantidadProducto();

}
