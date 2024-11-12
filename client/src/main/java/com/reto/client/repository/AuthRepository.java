package com.reto.client.repository;

import com.reto.client.model.request.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository  extends JpaRepository<Auth,Integer> {
}
