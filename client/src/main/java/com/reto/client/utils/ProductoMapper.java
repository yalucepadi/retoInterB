package com.reto.client.utils;

import com.reto.client.model.proxy.model.request.Product;
import com.reto.client.model.proxy.model.request.ProductDto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface ProductoMapper {
   // ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoProducto", source = "tipoProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "saldo", source = "saldo")
    ProductDto toProducto(ProductDto productoDto);



    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tipoProducto", source = "tipoProducto")
    @Mapping(target = "nombre", source = "nombre")
    @Mapping(target = "saldo", source = "saldo")


    ProductDto toDto(Product productoRequest);

    //@Mapping(target = "id", ignore = true)
    void updateProductoFromDto(ProductDto dto, @MappingTarget ProductDto request);

}
