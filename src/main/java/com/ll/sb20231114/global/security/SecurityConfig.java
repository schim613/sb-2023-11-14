package com.ll.sb20231114.global.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        return http
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/member/login")
//                                .loginProcessingUrl("/member/login") 위와 같으면 생략 가능
//                                .usernameParameter("username") 기본 이름이 username, password 이면 생략 가능
//                                .passwordParameter("password")
                                .defaultSuccessUrl("/article/list")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}