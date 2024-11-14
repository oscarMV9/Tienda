package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.ClienteDto;
import com.nogrup.celulares.Entity.Cliente;
import com.nogrup.celulares.Repository.ClienteRepository;
import com.nogrup.celulares.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository repositorioCliente;

    @Override
    public List<ClienteDto> findAll() {
        return repositorioCliente.findAll().stream()
                .map(this::convertToDTo)
                .collect(Collectors.toList());
    }

    // este solo se usa para las platillas (traer todos los datos) :)
    @Override
    public List<Cliente> listarClientes() {
        return repositorioCliente.findAll();
    }

    @Override
    public ClienteDto findById(Long id) {
        Optional<Cliente> optionalCliente = repositorioCliente.findById(id);
        return optionalCliente.map(this::convertToDTo).orElse(null);
    }

    @Override
    public ClienteDto save(ClienteDto clienteDto) {
        Cliente cliente = convertToEntity(clienteDto);
        Cliente saveCliente = repositorioCliente.save(cliente);
        return convertToDTo(saveCliente);
    }

    @Override
    public void Delete(Long id) {
        repositorioCliente.deleteById(id);
    }

    // metodo para convertir Entidad (Cliente) a Dto (ClienteDto)
    private ClienteDto convertToDTo(Cliente cliente) {
        // si no existe me devuelve nulo
        if (cliente == null) {
            return null;
        }

        // proceso de converticion
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setIdentificacion(cliente.getIdentificacion());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setDireccion(cliente.getDireccion());
        clienteDto.setTelefono(cliente.getTelefono());
        clienteDto.setEmail(cliente.getEmail());
        System.out.println("ClienteDto Generado con exito! " + clienteDto);

        return clienteDto;
    }


    private Cliente convertToEntity(ClienteDto clienteDto) {
        if (clienteDto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setEmail(clienteDto.getEmail());

        return cliente;
    }

}
