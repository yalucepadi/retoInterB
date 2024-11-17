package com.reto.client.utils;

import com.reto.client.model.request.AuthDto;

import java.util.function.Predicate;

public class AuthValidator {

    public static Predicate<AuthDto> isValidClientId = authDto -> authDto != null && authDto.getClienteId() != null;

    public static Predicate<AuthDto> isValidPassword = authDto -> authDto != null && authDto.getPassword() != null
            && !authDto.getPassword().isEmpty()
            && authDto.getPassword().length() >= 8 // Mínimo 8 caracteres
            && authDto.getPassword().matches(".*[A-Z].*") // Al menos una mayúscula
            && authDto.getPassword().matches(".*[!@#$%^&*(),.?\":{}|<>].*");
}