package com.reto.client.repository;

import com.reto.client.model.request.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AuthRepository  extends JpaRepository<AuthRequest,Integer> {
    Optional<AuthRequest> findByUser(String user);


}
