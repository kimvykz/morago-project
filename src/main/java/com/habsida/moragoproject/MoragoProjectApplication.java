package com.habsida.moragoproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.sql.Date;

@SpringBootApplication
public class MoragoProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoragoProjectApplication.class, args);
    }


//    @Bean
//    CommandLineRunner commandLineRunner() {
//        Date date = Date.valueOf( "2023-11-23");
//
//        return null;
//    }

}
