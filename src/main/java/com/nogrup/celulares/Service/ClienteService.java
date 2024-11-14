package com.nogrup.celulares.Service;

import com.nogrup.celulares.Dto.ClienteDto;
import com.nogrup.celulares.Entity.Cliente;

import java.util.List;

public interface ClienteService {

    List<ClienteDto> findAll();

    // metodo para los Html (No para postman)
    List<Cliente> listarClientes();

    ClienteDto findById(Long id);

    ClienteDto save(ClienteDto clienteDto);

    void Delete(Long id);

}
