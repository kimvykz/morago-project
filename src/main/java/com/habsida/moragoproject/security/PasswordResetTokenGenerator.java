package com.habsida.moragoproject.security;

import com.habsida.moragoproject.exception.TokenRefreshException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class PasswordResetTokenGenerator {
    @Value("${habsida.kr.passwordresetexparationminutes}")
    private Long passwordResetExpirationMinutes;
    @Value("${habsida.kr.passwordresetsecret}")
    private String passwordResetSecret;

    public void verifyExpiration (String token) {
        if ( getValidationDate(token).toInstant().compareTo(Instant.now()) < 0) {
            throw new IllegalArgumentException( "Password reset token was expired. Please make a new reset request");
        }
        //return token;
    }

    public String generateJwtPasswordResetToken () {
        Date expireDate = Date.from(ZonedDateTime.now().plusMinutes(passwordResetExpirationMinutes).toInstant());
        String token = Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, passwordResetSecret)
                .compact();
        return token;
    }

    public Date getValidationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(passwordResetSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();

    }
}
