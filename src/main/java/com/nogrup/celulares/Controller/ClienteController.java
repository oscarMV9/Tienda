package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.ClienteDto;
import com.nogrup.celulares.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService servicioCliente;

    //metodo para mostrar los datos (todos)
    @GetMapping
    public ResponseEntity<List<ClienteDto>> ClienteGet() {
        List<ClienteDto> clientes = servicioCliente.findAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //metodo para buscar por Id
    @GetMapping("/{identificacion}")
    //
    public ResponseEntity<ClienteDto> ClienteGetById(@PathVariable Long identificacion) {
        ClienteDto clienteDto = servicioCliente.findById(identificacion);
        return clienteDto != null ? new ResponseEntity<>(clienteDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // metodo para crear un registro
    @PostMapping
    public ResponseEntity<ClienteDto> CreateCliente(@RequestBody ClienteDto clienteDto) {
        // guarda cliente en el servicio (save)
        ClienteDto newCliente = servicioCliente.save(clienteDto);
        return new ResponseEntity<>(newCliente, HttpStatus.CREATED);
    }

    //actualizar un registro
    @PutMapping("/{identificacion}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable Long identificacion, @RequestBody ClienteDto clienteDto) {
        ClienteDto ClienteExistente = servicioCliente.findById(identificacion);
        if (ClienteExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        clienteDto.setIdentificacion(identificacion);
        ClienteDto actualizarCliente = servicioCliente.save(clienteDto);
        return new ResponseEntity<>(actualizarCliente, HttpStatus.OK);
    }

    //metodo para eliminar un registro
    @DeleteMapping("/{identificacion}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long identificacion) {
        ClienteDto clienteDto = servicioCliente.findById(identificacion);
        if (clienteDto == null) {
            return new ResponseEntity<>("cliente no encontrado", HttpStatus.NOT_FOUND);
        }
        servicioCliente.Delete(identificacion);
        return new ResponseEntity<>("Cliente eliminado con exito!", HttpStatus.OK);
    }
}
