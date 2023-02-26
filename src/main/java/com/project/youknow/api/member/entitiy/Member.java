package com.project.youknow.api.member.entitiy;

import com.project.youknow.api.member.enumType.MemberRole;
import com.project.youknow.api.member.enumType.MemberStatus;
import com.project.youknow.common.time.DateEntity;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends DateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 이메일을 회원 아이디로 사용한다.
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String mobileNumber;

    private MemberStatus memberStatus;

    private MemberRole memberRole;

    @Builder
    public Member(String email, String name, String password, String mobileNumber) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}
