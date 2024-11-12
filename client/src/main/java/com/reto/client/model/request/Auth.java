package com.reto.client.model.request;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;
    private String password;


    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")  // Referencia a la columna `id` de Cliente
    private Cliente cliente;

    @PostLoad
    @PostPersist
    private void initUser() {
        if (cliente != null) {
            this.user = cliente.getNombres();
        }
    }
}
