package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.CategoriaDto;
import com.nogrup.celulares.Entity.Categoria;

import java.util.List;

public interface CategriaService {

    List<CategoriaDto> findAll();

    CategoriaDto findById(Long id);

    CategoriaDto save(CategoriaDto categoriaDto);

    void Delete(Long id);

}
