package com.nogrup.celulares.Repository;

import com.nogrup.celulares.Entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRespository extends JpaRepository<Proveedor, Long> {
    Proveedor findByNombre(String nombre);
}
