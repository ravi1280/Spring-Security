package com.example.spring_jwt.repository;

import com.example.spring_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
   Optional<User>  findByUsername(String username);
}
