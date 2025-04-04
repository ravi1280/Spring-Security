package com.example.spring_jwt.service;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {
    private final SecretKey secretKey;

    public JWTService() {
        try {
            SecretKey key = KeyGenerator.getInstance("HmacSHA256").generateKey();
            secretKey = Keys.hmacShaKeyFor(key.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String username, Map<String,Object> claims) {
        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000*60*15))
                .signWith(secretKey)
                .compact();

    }

    public String getUsername(String token) {
        Claims data = getTokenData(token);
        if(token == null) return null;
        return data.getSubject();

    }
    public Object getFieldFromToken(String token, String key) {
        Claims data = getTokenData(token);
        if(token == null) return null;
        return data.get(key);
    }
    public Claims getTokenData(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            return null;
        }

    }
}
