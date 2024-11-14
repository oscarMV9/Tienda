package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.ProductosDto;

import java.util.List;

public interface ProductosService {

    List<ProductosDto> findAll();

    ProductosDto findById(Long id);

    ProductosDto save(ProductosDto productosDto);

    void Delete(Long id);

}
