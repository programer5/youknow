package com.project.youknow.api.member;

import com.project.youknow.api.member.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembersRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Integer countByEmail(String email);

    Integer countByMobileNumber(String mobileNumber);
}
