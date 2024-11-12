package com.reto.product.utils;

import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.request.ProductoRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-12T15:34:37-0500",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoRequest toProducto(ProductoDto productoDto) {
        if ( productoDto == null ) {
            return null;
        }

        ProductoRequest productoRequest = new ProductoRequest();

        return productoRequest;
    }

    @Override
    public ProductoDto toDto(ProductoRequest productoRequest) {
        if ( productoRequest == null ) {
            return null;
        }

        ProductoDto productoDto = new ProductoDto();

        return productoDto;
    }

    @Override
    public void updateProductoFromDto(ProductoDto dto, ProductoRequest request) {
        if ( dto == null ) {
            return;
        }
    }
}
