package com.habsida.moragoproject.service;

import com.habsida.moragoproject.model.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenService {
    Optional<RefreshToken> findByToken (String token);
    RefreshToken createRefreshToken (Long userId);
    RefreshToken verifyExpiration (RefreshToken token);
    int deleteByUserId(Long userId);
}
