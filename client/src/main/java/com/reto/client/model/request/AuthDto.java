package com.reto.client.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDto {
    private Integer id;
    private String user;
    private String password;
    private ClientDto cliente;
}