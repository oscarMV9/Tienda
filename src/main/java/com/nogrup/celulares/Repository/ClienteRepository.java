package com.nogrup.celulares.Repository;

import com.nogrup.celulares.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByIdentificacion(Long identificacion);
}
