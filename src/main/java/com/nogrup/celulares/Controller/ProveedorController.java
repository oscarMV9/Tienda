package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.ProveedorDto;
import com.nogrup.celulares.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedor")
public class ProveedorController {

    @Autowired
    private ProveedorService servicioProveedor;

    // metodo para listar todos los registros
    @GetMapping
    public ResponseEntity<List<ProveedorDto>> ProveedorGet() {
        List<ProveedorDto> proveedor = servicioProveedor.findAll();
        return new ResponseEntity<>(proveedor, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDto> ProveedorByid(@PathVariable Long id) {
        ProveedorDto proveedorDto = servicioProveedor.findById(id);
        return proveedorDto != null ? new ResponseEntity<>(proveedorDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProveedorDto> CreateProveedor(@RequestBody ProveedorDto proveedorDto) {
        ProveedorDto newProveedor = servicioProveedor.save(proveedorDto);
        return new ResponseEntity<>(newProveedor, HttpStatus.CREATED);
    }

    //actualizar un proveedor (Corregir)
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDto> updateProveedor(@PathVariable Long id, @RequestBody ProveedorDto proveedorDto) {
        ProveedorDto ProveedorExistente = servicioProveedor.findById(id);
        if (ProveedorExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        proveedorDto.setIdentificacion(id);
        ProveedorDto actualizarProveedor = servicioProveedor.save(proveedorDto);
        return new ResponseEntity<>(actualizarProveedor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable Long id) {
        ProveedorDto proveedorDto = servicioProveedor.findById(id);
        if (proveedorDto == null) {
            return new ResponseEntity<>("el proveedor no se encuentra", HttpStatus.NOT_FOUND);
        }
        servicioProveedor.Delete(id);
        return new ResponseEntity<>("Proveedor eliminado exitosamente!", HttpStatus.OK);
    }

}
