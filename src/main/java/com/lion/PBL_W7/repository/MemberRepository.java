package com.lion.PBL_W7.repository;

import com.lion.PBL_W7.domain.role.Role;
import java.util.List;

public interface MemberRepository {
    void save(Role member);
    Role findByName(String name);
    List<Role> findAll();
    void updateByName(String name, Role member); // 이름으로 기존 멤버를 교체한다.
    boolean deleteByName(String name);          // 이름으로 멤버를 삭제한다.
    boolean existsByName(String name);          // 이름 존재 여부를 확인한다.
}