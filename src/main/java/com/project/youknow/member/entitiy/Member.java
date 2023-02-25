package com.project.youknow.member.entitiy;

import com.project.youknow.common.time.DateEntity;
import com.project.youknow.member.enumType.MemberRole;
import com.project.youknow.member.enumType.MemberStatus;
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

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Member(long id, String email, String password, String mobileNumber, MemberStatus status, MemberRole role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.memberStatus = status;
        this.memberRole = role;
    }
}
