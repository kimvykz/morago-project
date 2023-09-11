package com.habsida.moragoproject.config;

import com.habsida.moragoproject.security.JwtAuthEntryPoint;
import com.habsida.moragoproject.security.JwtAuthenticationFilter;
import com.habsida.moragoproject.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private JwtAuthEntryPoint authEntryPoint;

    private CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthEntryPoint authEntryPoint, CustomUserDetailsService userDetailsService) {
        this.authEntryPoint = authEntryPoint;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
         http
                .csrf(csrf -> csrf.disable())
                .exceptionHandling()
                .authenticationEntryPoint(authEntryPoint)
                .and()
                .sessionManagement(session ->
                         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests( auth -> {
                    auth.antMatchers("/graphql").permitAll()
                    .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults());

         HttpSecurity httpSecurity = http.getSharedObject(HttpSecurity.class);

         http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

}
