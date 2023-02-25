package com.project.youknow.login.controller;

import com.project.youknow.jwt.JwtAuthProvider;
import com.project.youknow.login.dto.LoginDto;
import com.project.youknow.login.service.LoginService;
import com.project.youknow.member.dto.AuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final JwtAuthProvider jwtAuthProvider;

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        AuthenticationDto authentication = loginService.login(loginDto);

        return ResponseEntity.ok().header("accesstoken",
                jwtAuthProvider.createToken(authentication.getId(), authentication.getEmail()))
                .body(authentication);
    }
}
