package com.nogrup.celulares.Dto;

import jakarta.persistence.Column;

public class CategoriaDto {

    private Long id_categoria;

    private String nombreCategoria;

    private String descripcion;

    public CategoriaDto(Long id_categoria, String descripcion, String nombreCategoria) {
        this.id_categoria = id_categoria;
        this.descripcion = descripcion;
        this.nombreCategoria = nombreCategoria;
    }

    public CategoriaDto() {

    }

    public Long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
