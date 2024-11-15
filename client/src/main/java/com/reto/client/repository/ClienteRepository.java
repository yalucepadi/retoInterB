package com.reto.client.repository;

import com.reto.client.model.request.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClientRequest, Integer> {
}
