package com.example.spring_jwt.controller;

import com.example.spring_jwt.dto.LoginRequestDTO;
import com.example.spring_jwt.dto.LoginResponseDTO;
import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return authService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {

        LoginResponseDTO res = authService.login(loginRequestDTO);
        if(res.getError() != null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }

}
