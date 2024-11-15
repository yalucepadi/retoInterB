package com.reto.client.repository;

import com.reto.client.model.request.AuthRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository  extends JpaRepository<AuthRequest,Integer> {
}
