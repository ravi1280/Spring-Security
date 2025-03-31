package com.example.spring_jwt.service;

import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public List<User> getAllUser() {

        return userRepository.findAll();
    }

    public User createUser(User user) {
//        System.out.println(user.getPassword().toString());
        User newUser = new User(user.getId(), user.getName(), user.getEmail(), user.getUsername(), passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);

    }
}
