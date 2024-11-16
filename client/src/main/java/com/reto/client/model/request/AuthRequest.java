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
public class AuthRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String user;
    private String password;
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")  // Referencia a la columna `id` de Cliente

    private ClientRequest cliente;
    @PostLoad
    @PostPersist
    private void initUser() {
        if (cliente != null) {
            this.user = cliente.getNombres();
        }
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientRequest getCliente() {
        return cliente;
    }

    public void setCliente(ClientRequest cliente) {
        this.cliente = cliente;
    }




}
