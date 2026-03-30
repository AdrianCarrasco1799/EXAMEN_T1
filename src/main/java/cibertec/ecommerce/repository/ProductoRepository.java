package cibertec.ecommerce.repository;
import cibertec.ecommerce.model.Producto;

public interface ProductoRepository {
    Producto obtenerPorId(Long id);
}
