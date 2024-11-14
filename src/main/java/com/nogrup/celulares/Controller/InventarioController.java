package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.InventarioDto;
import com.nogrup.celulares.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService servicioInventario;

    // listar todo el inventario
    @GetMapping
    public ResponseEntity<List<InventarioDto>> InventarioGet() {
        List<InventarioDto> inventario = servicioInventario.findAll();
        return new ResponseEntity<>(inventario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDto> BuscarPorId(@PathVariable Long id) {
        InventarioDto inventarioDto = servicioInventario.findById(id);
        return inventarioDto != null ? new ResponseEntity<>(inventarioDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<InventarioDto> createInventario(@RequestBody InventarioDto inventarioDto) {
        InventarioDto newInventario = servicioInventario.save(inventarioDto);
        return new ResponseEntity<>(newInventario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDto> updateInventario(@PathVariable Long id, @RequestBody InventarioDto inventarioDto) {
        InventarioDto inventarioExistente = servicioInventario.findById(id);
        if (inventarioDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        inventarioDto.setId_inventario(id);
        InventarioDto actualizarInventario = servicioInventario.save(inventarioDto);
        return new ResponseEntity<>(actualizarInventario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        InventarioDto inventarioDto = servicioInventario.findById(id);
        if (inventarioDto == null) {
            return new ResponseEntity<>("El inventario no se encontro", HttpStatus.NOT_FOUND);
        }
        servicioInventario.Delete(id);
        return new ResponseEntity<>("Inventario Eliminado exitosamente", HttpStatus.OK);
    }

}
