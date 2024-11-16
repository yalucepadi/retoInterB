package com.reto.client.model.proxy.model.request;

import com.reto.client.model.request.ClientRequest;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "product")
public class Product {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "tipo_producto")
   private String tipoProducto;

   private String nombre;

   @ManyToOne(fetch = FetchType.LAZY)  // Relación Muchos a Uno (muchos productos para un cliente)
   @JoinColumn(name = "cliente_id")  // Especifica la columna que será la clave foránea en la tabla de productos
   private ClientRequest cliente;  // Relación con Cliente

   public ClientRequest getCliente() {
      return cliente;
   }

   public void setCliente(ClientRequest cliente) {
      this.cliente = cliente;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
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

   public LocalDateTime getFechaCreacion() {
      return fechaCreacion;
   }

   public void setFechaCreacion(LocalDateTime fechaCreacion) {
      this.fechaCreacion = fechaCreacion;
   }

   private Double saldo;

   @Column(name = "fecha_creacion")
   private LocalDateTime fechaCreacion = LocalDateTime.now();
}
