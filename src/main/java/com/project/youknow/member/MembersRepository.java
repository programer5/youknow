package com.project.youknow.member;

import com.project.youknow.member.entitiy.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}
