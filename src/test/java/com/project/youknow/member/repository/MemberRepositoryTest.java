package com.project.youknow.member.repository;

import com.project.youknow.api.member.MembersRepository;
import com.project.youknow.api.member.entitiy.Member;
import com.project.youknow.api.member.enumType.MemberRole;
import com.project.youknow.api.member.enumType.MemberStatus;
import com.project.youknow.api.sign.dto.JoinDto;
import com.project.youknow.api.sign.service.SignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
class MemberRepositoryTest {

    @Autowired
    private MembersRepository membersRepository;

    @Autowired
    private SignService signService;

    @Test
    @DisplayName("회원 생성")
    @Rollback(false)
    void createMember() {
        JoinDto joinInfo = new JoinDto("neverdie4757@gmail.com", "a1s2d3A!S@", "정민서", "01064495767");
        Boolean join = signService.join(joinInfo);

        Assertions.assertEquals(true, join);
    }
}