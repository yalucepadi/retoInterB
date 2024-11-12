package com.reto.product.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDto {
    private Integer id;
    private String tipoProducto;
    private String nombre;
    private Double saldo;
}
