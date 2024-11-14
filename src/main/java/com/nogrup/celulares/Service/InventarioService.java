package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.InventarioDto;

import java.util.List;

public interface InventarioService {

    List<InventarioDto> findAll();

    InventarioDto findById(Long id);

    InventarioDto save(InventarioDto inventarioDto);

    void Delete(Long id);

}
