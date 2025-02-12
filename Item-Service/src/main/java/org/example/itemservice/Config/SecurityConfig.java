package org.example.itemservice.Config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {

        serverHttpSecurity.csrf()
                .disable()
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/api/**")
                        .permitAll() // Allow access to `/api/**` without authentication
                        .anyExchange()
                        .authenticated()); // Require authentication for all other paths
        return serverHttpSecurity.build();
    }
}
