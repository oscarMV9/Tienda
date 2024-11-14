package com.nogrup.celulares.Dto;

public class InventarioDto {

    private Long id_inventario;

    private String nombreProducto;

    private int cantidades;

    public InventarioDto(Long id_inventario, String nombreProducto, int cantidades) {
        this.id_inventario = id_inventario;
        this.nombreProducto = nombreProducto;
        this.cantidades = cantidades;
    }

    public InventarioDto() {
    }

    public Long getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(Long id_inventario) {
        this.id_inventario = id_inventario;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidades() {
        return cantidades;
    }

    public void setCantidades(int cantidades) {
        this.cantidades = cantidades;
    }
}
