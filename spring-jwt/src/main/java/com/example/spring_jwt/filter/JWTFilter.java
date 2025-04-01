package com.example.spring_jwt.filter;

import com.example.spring_jwt.entity.User;
import com.example.spring_jwt.repository.UserRepository;
import com.example.spring_jwt.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final UserRepository userRepository;

    public JWTFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");
        if(! authorizationHeader.startsWith("Bearer ")) filterChain.doFilter(request, response);

        String token = authorizationHeader.split(" ")[1];
        String username = jwtService.getUsername(token);
        if(username == null) {filterChain.doFilter(request, response);}

        User userData = userRepository.findByUsername(username).orElse(null);
        if(userData == null) {filterChain.doFilter(request, response);}
        
        System.out.println(authorizationHeader);
        System.out.println(token);
        System.out.println(username);
        filterChain.doFilter(request, response);
    }
}
