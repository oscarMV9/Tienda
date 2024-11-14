package com.nogrup.celulares.Repository;

import com.nogrup.celulares.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {
    Producto findByNombreProducto(String nombreProducto);
}