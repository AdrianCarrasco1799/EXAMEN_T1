package cibertec.ecommerce.repository;

import cibertec.ecommerce.model.Cliente;

public interface ClienteRepository {
    Cliente obtenerPorId(Long id);
}
