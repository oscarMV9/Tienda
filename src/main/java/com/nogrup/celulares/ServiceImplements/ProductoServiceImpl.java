package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.ProductosDto;
import com.nogrup.celulares.Entity.Categoria;
import com.nogrup.celulares.Entity.Producto;
import com.nogrup.celulares.Entity.Proveedor;
import com.nogrup.celulares.Repository.CategoriaRepository;
import com.nogrup.celulares.Repository.InventarioRepository;
import com.nogrup.celulares.Repository.ProductosRepository;
import com.nogrup.celulares.Repository.ProveedorRespository;
import com.nogrup.celulares.Service.ProductosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductosService {

    @Autowired
    private ProductosRepository repositorioProducto;

    @Autowired
    private CategoriaRepository repositorioCategoria;

    @Autowired
    private ProveedorRespository repositorioProveedor;

    @Autowired
    private InventarioRepository repositorioInventario;

    @Override
    public List<ProductosDto> findAll() {
        return repositorioProducto.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductosDto findById(Long id) {
        Optional<Producto> optionalProducto = repositorioProducto.findById(id);
        return optionalProducto.map(this::convertToDto).orElse(null);
    }

    @Override
    public ProductosDto save(ProductosDto productosDto) {
        Producto producto = convertToEntity(productosDto);
        Producto saveProducto = repositorioProducto.save(producto);
        return convertToDto(saveProducto);
    }

    @Override
    public void Delete(Long id) {

        Producto producto = repositorioProducto.findById(id)
                        .orElseThrow(() -> new RuntimeException("producto no encontrado"));

        if (producto.getInventario() != null) {
            repositorioInventario.delete(producto.getInventario());
        }

        repositorioProducto.deleteById(id);
    }

    private ProductosDto convertToDto(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductosDto productosDto = new ProductosDto();
        productosDto.setId_producto(producto.getId_producto());
        productosDto.setNombreProducto(producto.getNombreProducto());
        productosDto.setPrecio(producto.getPrecio());
        productosDto.setNombreCategoria(producto.getCategoria().getNombreCategoria());
        productosDto.setNombreProveedor(producto.getProveedor().getNombre());

        return productosDto;
    }

    // metodo para convertir Dto a entity
    private Producto convertToEntity(ProductosDto productosDto) {
        if (productosDto == null) {
            return null;
        }

        // conversion (proceso)
        Producto producto = new Producto();
        producto.setId_producto(productosDto.getId_producto());
        producto.setNombreProducto(productosDto.getNombreProducto());
        producto.setPrecio(productosDto.getPrecio());
        // se les pasa las entidades para buscar en el repositorio el metodo personalizado...
        Categoria categoria = repositorioCategoria.findByNombreCategoria(productosDto.getNombreCategoria());
        if (categoria == null) {
            throw new RuntimeException("categoria no encotrada");
        }
        producto.setCategoria(categoria);
        // igual que con el de categoria pero con proveedor
        Proveedor proveedor = repositorioProveedor.findByNombre(productosDto.getNombreProveedor());
        if (proveedor == null) {
            throw new RuntimeException("Proveedor no encontrado");
        }
        producto.setProveedor(proveedor);
        return producto;
    }

}
