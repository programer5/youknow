package com.project.youknow.member.dto;

import com.project.youknow.member.entitiy.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDto {

    private Long id;
    private String email;
    private String name;
    private String mobileNumber;
    private String createDate;
    private String updatedDate;

    public AuthenticationDto toDto(Member member) {
        id = member.getId();
        email = member.getEmail();
        name = member.getName();
        mobileNumber = member.getMobileNumber();
        createDate = String.valueOf(member.getCreatedDate());
        updatedDate = String.valueOf(member.getUpdatedDate());
        return this;
    }
}
