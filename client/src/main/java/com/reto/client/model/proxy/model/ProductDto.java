package com.reto.client.model.proxy.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "product")
public class ProductDto {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "tipo_producto")
   private String tipoProducto;

   private String nombre;

   private Double saldo;

   @Column(name = "fecha_creacion")
   private LocalDateTime fechaCreacion = LocalDateTime.now();
}
