package com.example.spring_jwt.controller;

import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/createuser")
    public User createUser(@RequestBody User user) {
        System.out.println(user.getPassword().toString());
        return authService.createUser(user);

    }
}
