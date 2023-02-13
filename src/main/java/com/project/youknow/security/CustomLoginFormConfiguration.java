package com.project.youknow.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class CustomLoginFormConfiguration {

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.formLogin(form -> form.loginPage("/login").permitAll()).build();
    }
}
