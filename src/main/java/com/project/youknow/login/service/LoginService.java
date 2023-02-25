package com.project.youknow.login.service;

import com.project.youknow.login.dto.LoginDto;
import com.project.youknow.member.MemberRepository;
import com.project.youknow.member.dto.AuthenticationDto;
import com.project.youknow.member.entitiy.Members;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationDto login(LoginDto loginDto) {
        Members loginEntity = loginDto.toEntity();

        Members member = memberRepository.findByEmail(loginEntity.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found"));

        if (!passwordEncoder.matches(loginEntity.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("Password do not match");
        }

        return new AuthenticationDto().toDto(member);
    }
}
