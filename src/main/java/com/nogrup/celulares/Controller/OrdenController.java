package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.OrdenDto;
import com.nogrup.celulares.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService serviciOrden;

    // listar todos las ordenes
    @GetMapping
    public ResponseEntity<List<OrdenDto>> ordenGet() {
        List<OrdenDto> orden = serviciOrden.findAll();
        return new ResponseEntity<>(orden, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDto> buscarPorId(@PathVariable Long id) {
        OrdenDto ordenDto = serviciOrden.findById(id);
        return ordenDto != null ? new ResponseEntity<>(ordenDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrdenDto> createOrdenes(@RequestBody OrdenDto ordenDto) {
        OrdenDto newOrder = serviciOrden.save(ordenDto);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOrden(@PathVariable Long id) {
        OrdenDto ordenDto = serviciOrden.findById(id);
        if (ordenDto == null) {
            return new ResponseEntity<>("la orden no se encuentra", HttpStatus.NOT_FOUND);
        }
        serviciOrden.delete(id);
        return new ResponseEntity<>("la orden se elimino exitosamente", HttpStatus.OK);
    }

}
