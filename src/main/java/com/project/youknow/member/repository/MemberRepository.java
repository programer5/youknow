package com.project.youknow.member.repository;

import com.project.youknow.member.entitiy.Members;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Members, Long> {
}
