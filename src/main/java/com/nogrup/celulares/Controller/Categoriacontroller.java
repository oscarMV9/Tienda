package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.CategoriaDto;
import com.nogrup.celulares.Service.CategriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class Categoriacontroller {

    @Autowired
    private CategriaService servicioCategoria;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> categoriaGet() {
        List<CategoriaDto> categorias = servicioCategoria.findAll();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // buscar categorias por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> ategoriaPorId(@PathVariable Long id) {
        CategoriaDto categoriaDto = servicioCategoria.findById(id);
        return categoriaDto != null ? new ResponseEntity<>(categoriaDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> createCategoria(@RequestBody CategoriaDto categoriaDto) {
        CategoriaDto newCategoria = servicioCategoria.save(categoriaDto);
        return new ResponseEntity<>(newCategoria, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {
        CategoriaDto categoriaDto = servicioCategoria.findById(id);
        if (categoriaDto == null) {
            return new ResponseEntity<>("Categoria no encontrada", HttpStatus.NOT_FOUND);
        }
        servicioCategoria.Delete(id);
        return new ResponseEntity<>("categoria se elimino con exito", HttpStatus.OK);
    }

}
