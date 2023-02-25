package com.project.youknow.member;

import com.project.youknow.member.entitiy.Members;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByEmail(String email);
}
