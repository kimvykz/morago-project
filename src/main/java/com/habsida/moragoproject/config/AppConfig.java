package com.habsida.moragoproject.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class AppConfig {
    public static Long JWT_EXPIRATION_MS = (2 * 60 * 60 ) * 1000L;
    public static Long JWT_REFRESH_EXPIRATION_MS = (365 * 24 * 60 * 60) * 1000L;
    public static final String JWT_SECRET = "secret";

    @Bean
    public ModelMapper configureModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);
        return modelMapper;
    }

    public static Long getJwtExpiration() {
        return JWT_EXPIRATION_MS;
    }

    public static void setJwtExpiration(Long jwtExpiration) {
        JWT_EXPIRATION_MS = jwtExpiration;
    }
}
