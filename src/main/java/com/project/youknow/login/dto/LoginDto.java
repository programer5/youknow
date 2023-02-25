package com.project.youknow.login.dto;

import com.project.youknow.member.entitiy.Members;
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

    public Members toEntity() {
        return new Members(email, password);
    }
}
