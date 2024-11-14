package com.nogrup.celulares.Dto;

import jakarta.persistence.Column;

public class ProveedorDto {

    private Long identificacion;

    private String nombre;

    private String telefono;

    private String email;

    private String direccion;

    public ProveedorDto(String direccion, String email, String telefono, String nombre, Long identificacion) {
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public ProveedorDto() {
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
