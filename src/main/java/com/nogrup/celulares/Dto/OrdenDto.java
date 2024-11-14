package com.nogrup.celulares.Dto;
import java.math.BigDecimal;
import java.time.LocalDate;

public class OrdenDto {

    private Long id_orden;

    private LocalDate fecha_creacion;

    private Long identificacion;

    private String nombreProducto;

    private Integer cantidad;

    private BigDecimal precio_total;

    public OrdenDto(Long id_orden, LocalDate fecha_creacion, Long identificacion, String nombreProducto, Integer cantidad, BigDecimal precio_total) {
        this.id_orden = id_orden;
        this.fecha_creacion = fecha_creacion;
        this.identificacion = identificacion;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio_total = precio_total;
    }

    public OrdenDto() {
    }

    public Long getId_orden() {
        return id_orden;
    }

    public void setId_orden(Long id_orden) {
        this.id_orden = id_orden;
    }

    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Long getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(Long identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio_total() {
        return precio_total;
    }

    public void setPrecio_total(BigDecimal precio_total) {
        this.precio_total = precio_total;
    }
}
