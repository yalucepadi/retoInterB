package com.reto.product.repository;


import com.reto.product.model.request.ProductoRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<ProductoRequest, Integer> {


}



