package com.project.youknow.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final JwtAuthProvider jwtAuthProvier;

}

