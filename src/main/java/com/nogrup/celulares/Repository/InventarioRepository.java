package com.nogrup.celulares.Repository;

import com.nogrup.celulares.Entity.Inventario;
import com.nogrup.celulares.Entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario ,Long> {
    Optional<Inventario> findByProducto(Producto producto);
}
