package com.habsida.moragoproject.security;

import com.habsida.moragoproject.config.AppConfig;
import com.habsida.moragoproject.exception.TokenException;
import com.habsida.moragoproject.service.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    public String generateJwtToken(UserDetails userDetails) {
        return generateTokenFromUsername(userDetails.getUsername());
    }

    public String generateTokenFromUsername (String username) {

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + AppConfig.JWT_EXPIRATION_MS);
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, AppConfig.JWT_SECRET)
                .compact();
        return token;
    }

    public String getUsernameFromJwt (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(AppConfig.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(AppConfig.JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            throw new TokenException(token, "Jwt was expired or incorrect");
        }
    }
}
