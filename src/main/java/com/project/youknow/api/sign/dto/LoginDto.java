package com.project.youknow.api.sign.dto;

import com.project.youknow.api.member.entitiy.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    @NotBlank(message = "'email' is a required input vale")
    @Email(message = "It is not in email format")
    private String email;

    @NotBlank(message = "'password' is a required input value")
    private String password;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .build();
    }
}
