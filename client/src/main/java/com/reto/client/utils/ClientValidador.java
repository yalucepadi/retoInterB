package com.reto.client.utils;


import com.reto.client.model.request.ClientRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ClientValidador {
    private final Predicate<ClientRequest> tieneApellidoValido = client ->
            client.getApellidos() != null && client.getApellidos().length() >= 3;
    ;

    private final Predicate<ClientRequest> tieneNombreValido = client ->
            client.getNombres() != null && client.getNombres().length() >= 3;

    private final Predicate<ClientRequest> tieneTipoDocumentoValido = client ->
            client.getTipoDocumento() != null && !client.getTipoDocumento().isEmpty();

    private final Predicate<ClientRequest> tieneNumeroDocumentoValido = client ->
            client.getNumeroDocumento() != null && !client.getNumeroDocumento().isEmpty();


    public boolean esClienteValido(ClientRequest client) {
        return tieneTipoDocumentoValido
                .and(tieneNombreValido)
                .and(tieneApellidoValido)
                .and(tieneNumeroDocumentoValido)
                .test(client);
    }

    public String obtenerMensajeError(ClientRequest client) {
        if (!tieneTipoDocumentoValido.test(client)) return "El tipo documento es requerido";
        if (!tieneNombreValido.test(client)) return "El nombre debe tener al menos 3 caracteres";
        if (!tieneApellidoValido.test(client)) return "El apellido debe tener al menos 3 caracteres";
        if (!tieneNumeroDocumentoValido.test(client)) return "El numero documento es requerido";
        return "Cliente válido";
    }
}
