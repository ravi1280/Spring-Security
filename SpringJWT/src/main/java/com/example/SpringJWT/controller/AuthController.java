package com.example.SpringJWT.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {


    @GetMapping()
    public String getUser() {
        return "Hello All Users!";
    }


}
