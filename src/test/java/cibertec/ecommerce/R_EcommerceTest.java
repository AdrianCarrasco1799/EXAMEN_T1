package cibertec.ecommerce;

import cibertec.ecommerce.model.Cliente;
import cibertec.ecommerce.model.Producto;
import cibertec.ecommerce.model.Orden;
import cibertec.ecommerce.repository.ClienteRepository;
import cibertec.ecommerce.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class R_EcommerceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private ProductoRepository productoRepository;
    @InjectMocks
    private R_Ecommerce ecommerceService;

    private Cliente clienteActivo;
    private Producto productoConStock;
    private Producto productoSinStock;

    @BeforeEach
    void Inicializar() {
        // -----
        clienteActivo = new Cliente();
        clienteActivo.setId(1L);
        clienteActivo.setNombre("Juan Pérez");
        clienteActivo.setEmail("juan@email.com");
        clienteActivo.setActivo(true);

        //------
        productoConStock = new Producto();
        productoConStock.setId(1L);
        productoConStock.setNombre("Laptop Dell");
        productoConStock.setPrecio(600.00);
        productoConStock.setStock(10);
        //------
        productoSinStock = new Producto();
        productoSinStock.setId(2L);
        productoSinStock.setNombre("Teclado Mecánico");
        productoSinStock.setPrecio(150.00);
        productoSinStock.setStock(0);
    }


    @Test
    @DisplayName("Crear Orden Exitosa Con Descuento")
    void CrearOrdenExitosaConDescuento() {
        List<Long> productoIds = Arrays.asList(1L);
        Map<Long, Integer> cantidades = new HashMap<>();
        cantidades.put(1L, 1);
        when(clienteRepository.obtenerPorId(1L)).thenReturn(clienteActivo);
        when(productoRepository.obtenerPorId(1L)).thenReturn(productoConStock);
        Orden orden = ecommerceService.crearOrden(1L, productoIds, cantidades);
        assertNotNull(orden);
        assertTrue(orden.getCodigo().matches("OR-\\d{4}"));
        assertEquals("ACTIVA", orden.getEstado());
        assertEquals(540.00, orden.getTotal(), 0.01); // 600 - 10% = 540
        assertEquals(1, orden.getProductos().size());
        assertNotNull(orden.getFecha());
        verify(clienteRepository, times(1)).obtenerPorId(1L);
        verify(productoRepository, times(1)).obtenerPorId(1L);
    }

}