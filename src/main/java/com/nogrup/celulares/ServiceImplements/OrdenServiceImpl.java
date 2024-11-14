package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.OrdenDto;
import com.nogrup.celulares.Entity.Inventario;
import com.nogrup.celulares.Entity.Orden;
import com.nogrup.celulares.Entity.Producto;
import com.nogrup.celulares.Repository.ClienteRepository;
import com.nogrup.celulares.Repository.InventarioRepository;
import com.nogrup.celulares.Repository.OrdenRepository;
import com.nogrup.celulares.Repository.ProductosRepository;
import com.nogrup.celulares.Service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository repositorioOrden;

    @Autowired
    private ProductosRepository repositorioProducto;

    @Autowired
    private InventarioRepository repositorioInventario;

    @Autowired
    private ClienteRepository repositorioCliente;

    @Override
    public List<OrdenDto> findAll() {
        return repositorioOrden.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @Override
    public OrdenDto findById(Long id) {
        Optional<Orden> optionalOrden = repositorioOrden.findById(id);
        return optionalOrden.map(this::convertToDto).orElse(null);
    }

    @Override
    public OrdenDto save(OrdenDto ordenDto) {
        // buscar primero el producto por su nombre
        Producto producto = repositorioProducto.findByNombreProducto(ordenDto.getNombreProducto());
        if (producto == null) {
            throw new RuntimeException("este producto no existe" + ordenDto.getNombreProducto());
        }
        // buscar el inventario del producto
        Inventario inventario = repositorioInventario.findByProducto(producto)
                .orElseThrow(() -> new RuntimeException("inventario no encontrado para el producto: " + ordenDto.getNombreProducto()));

        // esto ayuda a verificar si las cantidades son suficiente al inventario
        if (inventario.getCantidades() < ordenDto.getCantidad()) {
            throw new RuntimeException(("inventario insuficiente por favor, avasteser mas"));
        }
        // calcula el precio final
        BigDecimal precioTotal = producto.getPrecio().multiply(new BigDecimal(ordenDto.getCantidad()));
        ordenDto.setPrecio_total(precioTotal);

        inventario.setCantidades(inventario.getCantidades() - ordenDto.getCantidad());
        repositorioInventario.save(inventario);

        Orden orden = convertToEntity(ordenDto);
        orden.setProducto(producto);
        orden.setCliente(repositorioCliente.findByIdentificacion(ordenDto.getIdentificacion()));

        Orden ordenGuardada = repositorioOrden.save(orden);

        return convertToDto(ordenGuardada);
    }

    @Override
    public void delete(Long id) {
        repositorioOrden.deleteById(id);
    }

    private OrdenDto convertToDto(Orden orden) {
        if (orden == null) {
            return null;
        }

        OrdenDto ordenDto = new OrdenDto();
        ordenDto.setId_orden(orden.getId_orden());
        ordenDto.setIdentificacion(orden.getCliente().getIdentificacion());
        ordenDto.setFecha_creacion(orden.getFecha_creacion());
        ordenDto.setNombreProducto(orden.getProducto().getNombreProducto());
        ordenDto.setCantidad(orden.getCantidad());

        BigDecimal precioTotal = orden.getProducto().getPrecio().multiply(new BigDecimal(orden.getCantidad()));
        ordenDto.setPrecio_total(precioTotal);

        return ordenDto;
    }

    private Orden convertToEntity(OrdenDto ordenDto) {
        if (ordenDto == null) {
            return null;
        }

        Orden orden = new Orden();
        orden.setId_orden(ordenDto.getId_orden());
        orden.setFecha_creacion(ordenDto.getFecha_creacion());
        orden.setCantidad(ordenDto.getCantidad());
        orden.setPrecio_total(ordenDto.getPrecio_total());

        return orden;
    }

}