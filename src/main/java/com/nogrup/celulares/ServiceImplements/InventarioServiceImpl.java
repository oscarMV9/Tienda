package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.InventarioDto;
import com.nogrup.celulares.Dto.ProductosDto;
import com.nogrup.celulares.Entity.Inventario;
import com.nogrup.celulares.Entity.Producto;
import com.nogrup.celulares.Repository.InventarioRepository;
import com.nogrup.celulares.Repository.ProductosRepository;
import com.nogrup.celulares.Service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioServiceImpl implements InventarioService {

    @Autowired
    private InventarioRepository repositorioInventario;

    @Autowired
    private ProductosRepository repositorioProducto;

    @Override
    public List<InventarioDto> findAll() {
        return repositorioInventario.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioDto findById(Long id) {
        Optional<Inventario> optionalInventario = repositorioInventario.findById(id);
        return optionalInventario.map(this::convertToDto).orElse(null);
    }

    @Override
    public InventarioDto save(InventarioDto inventarioDto) {
        Producto producto = repositorioProducto.findByNombreProducto(inventarioDto.getNombreProducto());
        if (producto == null) {
            throw new RuntimeException("Producto no encontrado");
        }
        Optional<Inventario> inventarioExistente = repositorioInventario.findByProducto(producto);
        if (inventarioExistente.isPresent()) {
            if (!inventarioExistente.get().getId_inventario().equals(inventarioDto.getId_inventario())) {
                throw new RuntimeException("Este inventario ya tiene este producto");
            }
        }
        Inventario inventario = convertToEntity(inventarioDto);
        Inventario saveInventario = repositorioInventario.save(inventario);
        return convertToDto(saveInventario);
    }

    @Override
    public void Delete(Long id) {
        repositorioInventario.deleteById(id);
    }


    private InventarioDto convertToDto(Inventario inventario) {
        if (inventario == null) {
            return null;
        }

        InventarioDto inventarioDto = new InventarioDto();
        inventarioDto.setId_inventario(inventario.getId_inventario());
        inventarioDto.setNombreProducto(inventario.getProducto().getNombreProducto());
        inventarioDto.setCantidades(inventario.getCantidades());

        return inventarioDto;
    }

    private Inventario convertToEntity(InventarioDto inventarioDto) {
        if (inventarioDto == null) {
            return null;
        }

        Inventario inventario = new Inventario();
        inventario.setId_inventario(inventarioDto.getId_inventario());
        Producto producto = repositorioProducto.findByNombreProducto(inventarioDto.getNombreProducto());
        if (producto == null) {
            throw new RuntimeException("producto No encontrado");
        }
        inventario.setProducto(producto);
        inventario.setCantidades(inventarioDto.getCantidades());
        return inventario;
    }

}
