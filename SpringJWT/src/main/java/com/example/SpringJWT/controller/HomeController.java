package com.example.SpringJWT.controller;

import com.example.SpringJWT.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final JWTService jwtService;

    public HomeController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @GetMapping("/")
    public String getHello(){
        return "Hello World";
    }

    @PostMapping("/login")
    public String login(){
//        return "Hello Login";
        return jwtService.generateToken();
    }
    @GetMapping("username")
    public String getUsername(@RequestParam String token){
        return jwtService.getUsername(token);
    }
}
