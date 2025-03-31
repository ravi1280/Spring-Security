package com.example.spring_jwt.service;

import com.example.spring_jwt.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailsService( UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.spring_jwt.entity.User userData =userRepository.findByUsername(username).orElse(null);

        if(userData == null)  throw new UsernameNotFoundException("User not found");

        UserDetails user = User.builder()
                .username(userData.getUsername())
                .password(userData.getPassword())
                .build();
        return user;
    }
}
