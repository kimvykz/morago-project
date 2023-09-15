package com.habsida.moragoproject.service;

import com.habsida.moragoproject.config.AppConfig;
import com.habsida.moragoproject.exception.TokenRefreshException;
import com.habsida.moragoproject.model.entity.RefreshToken;
import com.habsida.moragoproject.repository.RefreshTokenRepository;
import com.habsida.moragoproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{
    private Long refreshTokenDurationMs = AppConfig.JWT_REFRESH_EXPIRATION_MS;
    private RefreshTokenRepository refreshTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository,
                                   UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public RefreshToken createRefreshToken(Long userId) {

        Optional<RefreshToken> oldRefToken =
                refreshTokenRepository.findByUser(userRepository.findById(userId).get());
        if (oldRefToken.isPresent()) {
            refreshTokenRepository.delete(oldRefToken.get());
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUser(userRepository.findById(userId).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken;

    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new login request");
        }
        return token;
    }

    @Override
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}
