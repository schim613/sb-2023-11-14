package com.ll.sb20231114.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 스프링 시큐리티에서 csrf가 포함되어 있는데 csrf 토큰이 있어야 POST 전송이 됨
//        http
//                .csrf(
//                    csrf -> csrf.disable()
//                );

        return http.build();
    }
}