package com.alten.sahim.back.service;

import com.alten.sahim.back.dao.UserDao;
import com.alten.sahim.back.dto.UserDto;
import com.alten.sahim.back.entity.User;
import com.alten.sahim.back.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    public final String jwtSecret = "c29tZXJhbmRvbWtleXRoYXR5b3VzaG91bGRnZW5lcmF0ZQ==";

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public void registerUser(UserDto userDto) {
        if (userDao.findByEmail(userDto.getEmail()).isPresent()) {
                throw new IllegalArgumentException("L'email est déjà utilisé !");
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userDao.save(UserDto.toEntity(userDto));
    }

    public String authenticate(String email, String password) {
        Optional<User> userOptional = userDao.findByEmail(email);

        if (userOptional.isEmpty() || !passwordEncoder.matches(password, userOptional.get().getPassword())) {
            throw new IllegalArgumentException("Email ou mot de passe incorrect !");
        }
        try {
            return generateToken(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String generateToken(String email) throws Exception {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Expire après 24h
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public String extractEmail(String token) throws Exception {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
