package com.reto.client.proxy.product.repository;


import com.reto.client.model.proxy.model.request.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductoRepository extends JpaRepository<Product, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.cliente.id = :clientId WHERE p.id = :productId")
    int actualizarClienteIdPorProductoId(@Param("productId") Integer productId, @Param("clientId") Integer clientId);


}



