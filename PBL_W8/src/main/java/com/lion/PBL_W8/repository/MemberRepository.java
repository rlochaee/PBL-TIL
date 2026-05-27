package com.lion.PBL_W8.repository;

import com.lion.PBL_W8.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// 1. JpaRepository를 상속받고 대상 엔티티(Member)와 ID 타입(Long)을 지정합니다.
public interface MemberRepository extends JpaRepository<Member, Long> {

    // 2. 7주차 로직 검증에 필요한 '이름으로 찾기'만 규칙에 맞춰 남겨둡니다.
    // 안전한 데이터 처리를 위해 null을 방지하는 Optional로 감싸줍니다.
    Optional<Member> findByName(String name);
}