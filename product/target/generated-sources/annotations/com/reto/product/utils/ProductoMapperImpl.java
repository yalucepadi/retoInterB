package com.reto.product.utils;

import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.request.ProductoRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-15T00:26:37-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.13 (Eclipse Adoptium)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoRequest toProducto(ProductoDto productoDto) {
        if ( productoDto == null ) {
            return null;
        }

        ProductoRequest productoRequest = new ProductoRequest();

        productoRequest.setId( productoDto.getId() );
        productoRequest.setTipoProducto( productoDto.getTipoProducto() );
        productoRequest.setNombre( productoDto.getNombre() );
        productoRequest.setSaldo( productoDto.getSaldo() );

        return productoRequest;
    }

    @Override
    public ProductoDto toDto(ProductoRequest productoRequest) {
        if ( productoRequest == null ) {
            return null;
        }

        ProductoDto productoDto = new ProductoDto();

        productoDto.setId( productoRequest.getId() );
        productoDto.setTipoProducto( productoRequest.getTipoProducto() );
        productoDto.setNombre( productoRequest.getNombre() );
        productoDto.setSaldo( productoRequest.getSaldo() );

        return productoDto;
    }

    @Override
    public void updateProductoFromDto(ProductoDto dto, ProductoRequest request) {
        if ( dto == null ) {
            return;
        }

        request.setId( dto.getId() );
        request.setTipoProducto( dto.getTipoProducto() );
        request.setNombre( dto.getNombre() );
        request.setSaldo( dto.getSaldo() );
    }
}
