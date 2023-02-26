package com.project.youknow.api.sign.controller;

import com.project.youknow.api.sign.dto.JoinDto;
import com.project.youknow.api.sign.dto.LoginDto;
import com.project.youknow.api.sign.service.SignService;
import com.project.youknow.jwt.JwtAuthProvider;
import com.project.youknow.api.member.dto.AuthenticationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

    private final SignService signService;
    private final JwtAuthProvider jwtAuthProvider;

    @PostMapping("/signup")
    public ResponseEntity<Boolean> join(@Valid @RequestBody JoinDto joinDto) {
        return ResponseEntity.ok().body(signService.join(joinDto));
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDto> authorize(@Valid @RequestBody LoginDto loginDto) {

        AuthenticationDto authentication = signService.login(loginDto);

        return ResponseEntity.ok().header("accesstoken",
                jwtAuthProvider.createToken(authentication.getId(), authentication.getEmail()))
                .body(authentication);
    }
}
