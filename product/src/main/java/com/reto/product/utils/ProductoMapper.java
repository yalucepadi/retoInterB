package com.reto.product.utils;



import com.reto.product.model.request.ProductoDto;
import com.reto.product.model.request.ProductoRequest;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface ProductoMapper {
    //ProductoMapper INSTANCE = Mappers.getMapper(ProductoMapper.class);


    ProductoRequest toProducto(ProductoDto productoDto);


    ProductoDto toDto(ProductoRequest productoRequest);

    //@Mapping(target = "id", ignore = true)
    void updateProductoFromDto(ProductoDto dto, @MappingTarget ProductoRequest request);

}
