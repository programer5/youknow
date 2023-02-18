package com.project.youknow.member.entitiy;

import com.project.youknow.common.time.DateEntity;
import com.project.youknow.member.enumType.MemberRole;
import com.project.youknow.member.enumType.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private MemberStatus memberStatus;

    private MemberRole memberRole;

}
