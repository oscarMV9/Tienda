package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.ProveedorDto;

import java.util.List;

public interface ProveedorService {

    List<ProveedorDto> findAll();

    ProveedorDto findById(Long id);

    ProveedorDto save(ProveedorDto proveedorDto);

    void Delete(Long id);

}
