package com.nogrup.celulares.Repository;

import com.nogrup.celulares.Entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Categoria findByNombreCategoria(String nombreCategoria);
}
