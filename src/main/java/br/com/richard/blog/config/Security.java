package br.com.richard.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // Importante para CORS preflight
                .requestMatchers(HttpMethod.POST, "/artigos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/artigos/**").authenticated()
                .requestMatchers(HttpMethod.DELETE, "/artigos/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(basic -> {});
        
        return http.build();
    }
}