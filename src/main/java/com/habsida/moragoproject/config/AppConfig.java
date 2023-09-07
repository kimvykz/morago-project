package com.habsida.moragoproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public static final Long JWT_EXPIRATION = 70000L;
    public static final String JWT_SECRET = "secret";

    @Bean
    public ModelMapper configureModelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
