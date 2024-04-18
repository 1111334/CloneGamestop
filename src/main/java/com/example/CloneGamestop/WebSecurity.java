package com.example.CloneGamestop;
// Importazioni librerie Spring Security e HikariDataSource

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class WebSecurity {

    //@Autowired
    //private JwtTokenFilter jwtTokenFilter;

    @Bean // Bean per la codifica delle password con l'algoritmo BCrypt
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean // Bean SecurityFilterChain per le richieste API
    @Order(1)
    protected SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        // Applica il filtro solo alle richieste che iniziano con "/auth/"
        http
                .securityMatcher("/auth/**")
                // Consente l'accesso a qualsiasi richiesta sotto "/auth/" senza autenticazione
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll()
                )
                // Ignora la protezione CSRF per le richieste sotto "/auth/"
                .csrf(csrf -> csrf                                  // Enable CSRF protection
                        .ignoringRequestMatchers(new AntPathRequestMatcher("/auth/**")) // Ignore CSRF for /auth/**
                )
                // Abilita l'autenticazione HTTP Basic per questo filtro
                .httpBasic(withDefaults());
        // Costruisce e restituisce il SecurityFilterChain
        return http.build();

        //http.addFilterBefore(
        //        jwtTokenFilter,
        //        UsernamePasswordAuthenticationFilter.class
        //);;
    }
}
