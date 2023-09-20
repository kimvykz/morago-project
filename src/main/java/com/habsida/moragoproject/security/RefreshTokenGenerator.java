package com.habsida.moragoproject.security;

import com.habsida.moragoproject.exception.TokenRefreshException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.repository.RefreshTokenRepository;
import com.habsida.moragoproject.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class RefreshTokenGenerator {
    @Value("${habsida.kr.refreshtokenexpirationdays}")
    private Long refreshTokenExpirationDays;
    @Value("${habsida.kr.refreshtokensecret}")
    private String refreshTokenSecret;
    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public RefreshTokenGenerator(RefreshTokenRepository refreshTokenRepository,
                                 UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public Optional<RefreshToken> findByToken (String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken (Long userId) {
        RefreshToken refreshToken = new RefreshToken();
        Optional<RefreshToken> oldRefToken =
                refreshTokenRepository.findByUser(userRepository.findById(userId).get());
        if (oldRefToken.isPresent()) {
            refreshToken.setUser(oldRefToken.get().getUser());
            refreshToken.setId(oldRefToken.get().getId());
        } else {
            refreshToken.setUser(userRepository.findById(userId).get());
        }

        refreshToken.setExpiryDate(ZonedDateTime.now().plusDays(refreshTokenExpirationDays).toInstant());
        refreshToken.setToken(generateJwtRefreshToken());

        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken;

    }

    public RefreshToken verifyExpiration (RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new login request");
        }
        return token;
    }


    public int deleteByUserId (Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }

    private String generateJwtRefreshToken () {
        Date expireDate = Date.from(ZonedDateTime.now().plusDays(refreshTokenExpirationDays).toInstant());
        String token = Jwts
                .builder()
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, refreshTokenSecret)
                .compact();
        return token;
    }

}
