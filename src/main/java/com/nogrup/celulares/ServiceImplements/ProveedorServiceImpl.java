package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.ProveedorDto;
import com.nogrup.celulares.Entity.Proveedor;
import com.nogrup.celulares.Repository.ProveedorRespository;
import com.nogrup.celulares.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    @Autowired
    private ProveedorRespository repositorioProveedor;

    @Override
    public List<ProveedorDto> findAll() {
        return repositorioProveedor.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProveedorDto findById(Long id) {
        Optional<Proveedor> optionalProveedor = repositorioProveedor.findById(id);
        return optionalProveedor.map(this::convertToDto).orElse(null);
    }

    @Override
    public ProveedorDto save(ProveedorDto proveedorDto) {
        Proveedor proveedor = convertToEntity(proveedorDto);
        Proveedor saveProveedor = repositorioProveedor.save(proveedor);
        return  convertToDto(saveProveedor);
    }

    @Override
    public void Delete(Long id) {
        repositorioProveedor.deleteById(id);
    }

    private ProveedorDto convertToDto(Proveedor proveedor) {
        if (proveedor == null) {
            return null;
        }

        ProveedorDto proveedorDto = new ProveedorDto();
        proveedorDto.setIdentificacion(proveedor.getIdentificacion());
        proveedorDto.setNombre(proveedor.getNombre());
        proveedorDto.setEmail(proveedor.getEmail());
        proveedorDto.setTelefono(proveedor.getTelefono());
        proveedorDto.setDireccion(proveedor.getDireccion());

        System.out.println("proveedor generado con exito!" + proveedorDto);

        return proveedorDto;
    }

    private Proveedor convertToEntity(ProveedorDto proveedorDto) {
        if (proveedorDto == null) {
            return null;
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setIdentificacion(proveedorDto.getIdentificacion());
        proveedor.setNombre(proveedorDto.getNombre());
        proveedor.setTelefono(proveedorDto.getTelefono());
        proveedor.setEmail(proveedorDto.getEmail());
        proveedor.setDireccion(proveedorDto.getDireccion());

        return proveedor;
    }
}
