package com.example.spring_jwt.controller;

import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping()
    public List<User> getUser() {
        return authService.getAllUser();

    }
}
