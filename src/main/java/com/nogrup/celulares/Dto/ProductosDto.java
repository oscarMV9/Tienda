package com.nogrup.celulares.Dto;


import java.math.BigDecimal;

public class ProductosDto {

    private Long id_producto;

    private String nombreProducto;

    private BigDecimal precio;

    private String nombreCategoria;

    private String nombreProveedor;

    public ProductosDto(Long id_producto, String nombreProveedor, String nombreCategoria, BigDecimal precio, String nombreProducto) {
        this.id_producto = id_producto;
        this.nombreProveedor = nombreProveedor;
        this.nombreCategoria = nombreCategoria;
        this.precio = precio;
        this.nombreProducto = nombreProducto;
    }

    public ProductosDto() {
    }

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
}
