package com.project.youknow.member.repository;

import com.project.youknow.member.MembersRepository;
import com.project.youknow.member.entitiy.Member;
import com.project.youknow.member.enumType.MemberRole;
import com.project.youknow.member.enumType.MemberStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
class MemberRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;

    @Test
    @DisplayName("회원 생성")
    void createMember() {
        Member member = new Member(1L, "neverdie4757@gmail.com", "정민서", "a1s2d3A!S@", "010-6449-4757", MemberStatus.ACTIVATE, MemberRole.ADMIN);
        Member member1 = membersRepository.save(member);

        Assertions.assertEquals(1L, member1.getId());
    }
}