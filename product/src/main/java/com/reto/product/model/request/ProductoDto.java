package com.reto.product.model.request;


import lombok.Data;

@Data
public class ProductoDto {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String tipoProducto;
    private String nombre;
    private Double saldo;
}
