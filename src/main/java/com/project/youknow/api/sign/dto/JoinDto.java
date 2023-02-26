package com.project.youknow.api.sign.dto;

import com.project.youknow.api.member.entitiy.Member;
import com.project.youknow.api.member.enumType.MemberRole;
import com.project.youknow.api.member.enumType.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JoinDto {

    private String email;
    private String password;
    private String name;
    private String nickname;
    private String mobileNumber;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .name(name)
                .nickname(nickname)
                .mobileNumber(mobileNumber)
                .memberStatus(MemberStatus.ACTIVATE)
                .memberRole(MemberRole.MEMBER)
                .build();
    }

    public String encryptPassword(BCryptPasswordEncoder passwordEncoder, String password) {
        return this.password = passwordEncoder.encode(password);
    }
}
