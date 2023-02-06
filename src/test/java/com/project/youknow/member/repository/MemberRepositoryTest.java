package com.project.youknow.member.repository;

import com.project.youknow.member.entitiy.Member;
import com.project.youknow.member.enumType.MemberStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 저장")
    void save() {
        Member member = new Member(1L, "minseo@youknow.com", "정민서", "a1s2d3A!S@", MemberStatus.ACTIVATE);
        Member saveMember = memberRepository.save(member);
        Assertions.assertNotNull(saveMember);
    }

}