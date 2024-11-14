package com.nogrup.celulares.Controller;

import com.nogrup.celulares.Dto.ProductosDto;
import com.nogrup.celulares.Service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {

    @Autowired
    private ProductosService servicioProductos;

    // controlador para listar todos los productos
    @GetMapping
    public ResponseEntity<List<ProductosDto>> ProductosGet() {
        List<ProductosDto> productos = servicioProductos.findAll();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductosDto> ProductosById(@PathVariable Long id) {
        ProductosDto productosDto = servicioProductos.findById(id);
        return productosDto != null ? new ResponseEntity<>(productosDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProductosDto> createProducto(@RequestBody ProductosDto productosDto) {
        ProductosDto newProducto = servicioProductos.save(productosDto);
        return new ResponseEntity<>(newProducto, HttpStatus.CREATED);
    }

    // controlador para actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductosDto> updateProducto(@PathVariable Long id, @RequestBody ProductosDto productosDto) {
        ProductosDto productoExistente = servicioProductos.findById(id);
        if (productoExistente == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productosDto.setId_producto(id);
        ProductosDto actualizarProducto = servicioProductos.save(productosDto);
        return new ResponseEntity<>(actualizarProducto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        ProductosDto productosDto = servicioProductos.findById(id);
        if (productosDto == null) {
            return new ResponseEntity<>("el producto no se encontro", HttpStatus.NOT_FOUND);
        }
        servicioProductos.Delete(id);
        return new ResponseEntity<>("Producto eliminado Con exito", HttpStatus.OK);
    }

}
