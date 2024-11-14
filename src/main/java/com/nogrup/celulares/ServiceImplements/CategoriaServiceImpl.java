package com.nogrup.celulares.ServiceImplements;

import com.nogrup.celulares.Dto.CategoriaDto;
import com.nogrup.celulares.Entity.Categoria;
import com.nogrup.celulares.Repository.CategoriaRepository;
import com.nogrup.celulares.Service.CategriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategriaService {

    @Autowired
    private CategoriaRepository repositorioCategoria;


    @Override
    public List<CategoriaDto> findAll() {
        return repositorioCategoria.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CategoriaDto findById(Long id) {
        Optional<Categoria> optionalCategoria = repositorioCategoria.findById(id);
        return optionalCategoria.map(this::convertToDto).orElse(null);
    }

    @Override
    public CategoriaDto save(CategoriaDto categoriaDto) {
        Categoria categoria = convertToEntity(categoriaDto);
        Categoria categoriaSave = repositorioCategoria.save(categoria);
        return convertToDto(categoriaSave);
    }

    @Override
    public void Delete(Long id) {
        repositorioCategoria.deleteById(id);
    }

    private CategoriaDto convertToDto(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDto categoriaDto = new CategoriaDto();
        categoriaDto.setId_categoria(categoria.getId_categoria());
        categoriaDto.setNombreCategoria(categoria.getNombreCategoria());
        categoriaDto.setDescripcion(categoria.getDescripcion());

        return categoriaDto;

    }

    private Categoria convertToEntity(CategoriaDto categoriaDto) {
        if (categoriaDto == null) {
            return null;
        }

        Categoria categoria = new Categoria();
        categoria.setId_categoria(categoriaDto.getId_categoria());
        categoria.setNombreCategoria(categoriaDto.getNombreCategoria());
        categoria.setDescripcion(categoriaDto.getDescripcion());

        return categoria;

    }
}
