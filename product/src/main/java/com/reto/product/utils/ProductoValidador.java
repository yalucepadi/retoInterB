package com.reto.product.utils;


import com.reto.product.model.request.ProductoRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class ProductoValidador {
    private final Predicate<ProductoRequest> tieneSaldoPositivo = producto ->
            producto.getSaldo() != null && producto.getSaldo() >= 0;

    private final Predicate<ProductoRequest> tieneNombreValido = producto ->
            producto.getNombre() != null && producto.getNombre().length() >= 3;

    private final Predicate<ProductoRequest> tieneTipoProductoValido = producto ->
            producto.getTipoProducto() != null && !producto.getTipoProducto().isEmpty();

    public boolean esProductoValido(ProductoRequest producto) {
        return tieneSaldoPositivo
                .and(tieneNombreValido)
                .and(tieneTipoProductoValido)
                .test(producto);
    }

    public String obtenerMensajeError(ProductoRequest producto) {
        if (!tieneSaldoPositivo.test(producto)) return "El saldo debe ser positivo";
        if (!tieneNombreValido.test(producto)) return "El nombre debe tener al menos 3 caracteres";
        if (!tieneTipoProductoValido.test(producto)) return "El tipo de producto es requerido";
        return "Producto válido";
    }
}
