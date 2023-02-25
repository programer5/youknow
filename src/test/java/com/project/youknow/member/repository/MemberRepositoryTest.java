package com.project.youknow.member.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"spring.config.location=classpath:application-test.yml"})
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


}