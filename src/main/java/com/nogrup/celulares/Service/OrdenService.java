package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.OrdenDto;

import java.util.List;

public interface OrdenService {

    List<OrdenDto> findAll();

    OrdenDto findById(Long id);

    OrdenDto save(OrdenDto ordenDto);

    void delete(Long id);
}
