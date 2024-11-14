package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.OrdenDto;
import com.nogrup.celulares.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
