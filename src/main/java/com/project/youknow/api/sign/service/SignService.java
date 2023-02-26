package com.project.youknow.api.sign.service;

import com.project.youknow.api.member.MembersRepository;
import com.project.youknow.api.member.dto.AuthenticationDto;
import com.project.youknow.api.member.entitiy.Member;
import com.project.youknow.api.sign.dto.JoinDto;
import com.project.youknow.api.sign.dto.LoginDto;
import com.project.youknow.exception.custom.DuplicatedException;
import com.project.youknow.exception.custom.ForbiddenException;
import com.project.youknow.util.validation.Empty;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final MembersRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public Boolean join(JoinDto joinDto) {

        // 아이디 중복체크
        if (!Empty.validation(memberRepository.countByEmail(joinDto.getEmail()))) {
            throw new DuplicatedException("중복 아이디");
        }

        if (!Empty.validation(memberRepository.countByMobileNumber(joinDto.getMobileNumber()))) {
            throw new DuplicatedException("중복 된 핸드폰 번호");
        }

        joinDto.encryptPassword(passwordEncoder, joinDto.getPassword());

        memberRepository.save(joinDto.toEntity());

        return true;
    }

    public AuthenticationDto login(LoginDto loginDto) {
        Member loginEntity = loginDto.toEntity();

        Member member = memberRepository.findByEmail(loginEntity.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Member Not Found"));

        if (!passwordEncoder.matches(loginEntity.getPassword(), member.getPassword())) {
            throw new ForbiddenException("Password do not match");
        }

        return new AuthenticationDto().toDto(member);
    }
}
