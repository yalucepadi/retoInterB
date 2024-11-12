package com.reto.client.repository;

import com.reto.client.model.request.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
