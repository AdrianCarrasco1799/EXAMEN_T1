package cibertec.ecommerce;

import cibertec.ecommerce.model.Cliente;
import cibertec.ecommerce.model.Producto;
import cibertec.ecommerce.model.Orden;
import cibertec.ecommerce.repository.ClienteRepository;
import cibertec.ecommerce.repository.ProductoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class R_Ecommerce {

    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;
    private static final AtomicInteger contadorOrdenes = new AtomicInteger(1);

    public R_Ecommerce(ClienteRepository clienteRepository, ProductoRepository productoRepository) {
        this.clienteRepository = clienteRepository;
        this.productoRepository = productoRepository;
    }

    public Orden crearOrden(Long clienteId, List<Long> productoIds, Map<Long, Integer> cantidades) {

        Cliente cliente = clienteRepository.obtenerPorId(clienteId);
        if (cliente == null) {
            throw new RuntimeException("El cliente no existe en el sistema");
        }

        if (!cliente.isActivo()) {
            throw new RuntimeException("El cliente no está activo");
        }

        List<Producto> productosOrden = new ArrayList<>();
        Map<Long, Producto> productosVistos = new HashMap<>();
        double total = 0.0;

        for (Long productoId : productoIds) {
            if (productosVistos.containsKey(productoId)) {
                throw new RuntimeException("Producto duplicado en la orden: " + productoId);
            }

            Producto producto = productoRepository.obtenerPorId(productoId);
            if (producto == null) {
                throw new RuntimeException("El producto no existe: " + productoId);
            }

            Integer cantidad = cantidades.get(productoId);
            if (cantidad == null || cantidad <= 0) {
                throw new RuntimeException("Cantidad inválida para el producto: " + productoId);
            }

            // Validar stock
            if (producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            productosVistos.put(productoId, producto);
            productosOrden.add(producto);
            total += producto.getPrecio() * cantidad;
        }
        if (total > 500) {
            total = total * 0.90;
        }

        String codigoOrden = generarCodigoOrden();
        Orden orden = new Orden();
        orden.setCodigo(codigoOrden);
        orden.setCliente(cliente);
        orden.setProductos(productosOrden);
        orden.setCantidades(cantidades);
        orden.setTotal(total);
        orden.setFecha(LocalDate.now());
        orden.setEstado("ACTIVA");

        return orden;
    }

    private String generarCodigoOrden() {
        int numero = contadorOrdenes.getAndIncrement();
        return String.format("OR-%04d", numero);
    }
}