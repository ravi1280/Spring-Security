package com.example.spring_jwt.service;

import com.example.spring_jwt.entity.UserEntity;
import com.example.spring_jwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<UserEntity> getAllUser() {
        return userRepository.findAll();
    }
}
