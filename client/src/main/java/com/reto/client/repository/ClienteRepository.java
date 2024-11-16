package com.reto.client.repository;

import com.reto.client.model.request.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClientRequest, Integer> {
    @Query("SELECT c FROM ClientRequest c LEFT JOIN FETCH c.productosFinancieros WHERE c.id = :id")
    Optional<ClientRequest> findClientWithProductsById(@Param("id") Integer id);


}
