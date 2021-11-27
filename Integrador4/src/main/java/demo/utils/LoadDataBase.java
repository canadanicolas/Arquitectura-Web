package demo.utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import demo.model.Cliente;
import demo.model.Compra;
import demo.model.Producto;
import demo.model.Stock;
import demo.repository.ClienteRepository;
import demo.repository.CompraRepository;
import demo.repository.ProductoRepository;
import demo.repository.StockRepository;
/**
 * clase que se encarga de precargar los datos en la base de datos cuando se corre el programa.
 * @see String 
 * @version 10.8
 * 
 */
@Configuration
@Slf4j
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabaseProducto(@Qualifier("productoRepository") ProductoRepository repositoryProducto,
            @Qualifier("compraRepository") CompraRepository repositoryCompra,
            @Qualifier("stockRepository") StockRepository repositoryStock,
            @Qualifier("clienteRepository") ClienteRepository repositoryCliente) {

        return args -> {
            Producto p1 = new Producto(Long.valueOf("1"), "Gaseosa", (double) 100);
            Producto p2 = new Producto(Long.valueOf("2"), "Doritos", (double) 80);
            Producto p3 = new Producto(Long.valueOf("3"), "Hamburguesa", (double) 200);
            
            Compra c1 = new Compra(Long.valueOf("1"), "2021-11-04");
            Compra c2 = new Compra(Long.valueOf("2"), "2021-11-04");
            Compra c3 = new Compra(Long.valueOf("3"), "2021-11-05");
            
            Stock s1 = new Stock(Long.valueOf("1"), p1, 10);
            Stock s2 = new Stock(Long.valueOf("2"), p2, 5);
            Stock s3 = new Stock(Long.valueOf("3"), p3, 8);
            
            Cliente cl1 = new Cliente(Long.valueOf("1"), "Nicolas");
            Cliente cl2 = new Cliente(Long.valueOf("2"), "Lucia");
            
            c1.add(p1);
            c1.add(p2);
            c2.add(p1);
            c2.add(p3);
            c3.add(p1);
            c3.add(p2);
            c3.add(p3);
            
            cl1.add(c1);
            cl1.add(c3);
            cl2.add(c2);
            
            log.info("Preloading " + repositoryProducto.save(p1));
            log.info("Preloading " + repositoryProducto.save(p2));
            log.info("Preloading " + repositoryProducto.save(p3));
            log.info("Preloading " + repositoryCompra.save(c1));
            log.info("Preloading " + repositoryCompra.save(c2));
            log.info("Preloading " + repositoryCompra.save(c3));
            log.info("Preloading " + repositoryStock.save(s1));
            log.info("Preloading " + repositoryStock.save(s2));
            log.info("Preloading " + repositoryStock.save(s3));
            log.info("Preloading " + repositoryCliente.save(cl1));
            log.info("Preloading " + repositoryCliente.save(cl2));

        };
    }
}