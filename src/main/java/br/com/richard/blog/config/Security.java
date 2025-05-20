package br.com.richard.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.http.HttpMethod;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/artigos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/artigos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/artigos/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic();
        return http.build();
    }
}