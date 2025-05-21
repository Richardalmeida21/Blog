package br.com.richard.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class Security {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permite requisições de qualquer origem (para desenvolvimento)
        configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        
        // Alternativa: permitir apenas origens específicas
        // configuration.setAllowedOrigins(Arrays.asList("https://techblog-dun.vercel.app", "http://localhost:3000"));
        
        // Métodos permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH", "HEAD"));
        
        // Headers permitidos
        configuration.setAllowedHeaders(Arrays.asList(
            "Authorization", 
            "Content-Type", 
            "X-Requested-With", 
            "Accept", 
            "Origin", 
            "Access-Control-Request-Method", 
            "Access-Control-Request-Headers"
        ));
        
        // Expor estes headers na resposta
        configuration.setExposedHeaders(Arrays.asList("Authorization"));
        
        // Permitir cookies (necessário para autenticação)
        configuration.setAllowCredentials(true);
        
        // Cache de preflight por 1 hora (3600 segundos)
        configuration.setMaxAge(3600L);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}