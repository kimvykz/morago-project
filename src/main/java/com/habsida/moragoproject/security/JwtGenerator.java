package com.habsida.moragoproject.security;

import com.habsida.moragoproject.exception.TokenException;
import com.habsida.moragoproject.model.entity.User;
import com.habsida.moragoproject.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${habsida.kr.jwtsecret}")
    private String jwtSecret;
    @Value("${habsida.kr.jwtexpirationdays}")
    private int jwtExpirationDays;
    private UserRepository userRepository;//UserService userService;

    public JwtGenerator(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public String generateJwtToken(UserDetails userDetails) {
        return generateTokenFromUsername(userDetails.getUsername());
    }

    public String generateTokenFromUsername (String username) {
        Date expireDate = new Date();
        Duration duration = Duration.ofDays(jwtExpirationDays);
        expireDate.setTime(expireDate.getTime() + duration.toMillis());

        User user = userRepository.findByPhone(username)
                .orElseThrow(() -> new IllegalArgumentException("User is not found"));

        String token = Jwts
                .builder()
                .setSubject(username)
                .setId(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
        return token;
    }

    public String getUsernameFromJwt (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new TokenException(token, "Jwt was expired or incorrect");
        }
    }
}
