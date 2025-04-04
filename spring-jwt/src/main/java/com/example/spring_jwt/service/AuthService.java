package com.example.spring_jwt.service;

import com.example.spring_jwt.dto.LoginRequestDTO;
import com.example.spring_jwt.dto.LoginResponseDTO;
import com.example.spring_jwt.dto.RegisterRequestDTO;
import com.example.spring_jwt.dto.RegisterResponseDTO;
import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    public User createUser(RegisterRequestDTO user) {
//        System.out.println(user.getPassword().toString());
        User newUser = new User(user.getId(), user.getName(), user.getEmail(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);

    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
//        Boolean userExists = isUserExist(loginRequestDTO.getUsername());
//        if(!userExists) return new LoginResponseDTO(null,null,"User not found","Error");

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        } catch (RuntimeException e) {
            return new LoginResponseDTO(null, null, "User not found","Error");
        }

        Map<String, Object> claims = new HashMap<String,Object>();
        claims.put("role","User");
        claims.put("email","company@gmail.com");

        String token = jwtService.generateToken(loginRequestDTO.getUsername(),claims);
        System.out.println(jwtService.getFieldFromToken(token,"role"));

        return new LoginResponseDTO(token, LocalDateTime.now(), null, "User successfully logged in");
    }

    public RegisterResponseDTO register (RegisterRequestDTO registerRequestDTO) {
        if(isUserExist(registerRequestDTO.getUsername())) {
            return new RegisterResponseDTO(null,"User Already Exist!");
        }
        var userData = this.createUser(registerRequestDTO);
        if(userData.getId() == null) return new RegisterResponseDTO(null,"System Error !");

        return  new RegisterResponseDTO(null,String.format("User Created %s", userData.getId()));

    }

    private Boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
