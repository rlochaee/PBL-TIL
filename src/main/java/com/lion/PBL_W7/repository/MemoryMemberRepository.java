package com.lion.PBL_W7.repository;

import com.lion.PBL_W7.domain.role.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    // [기존 코드]
    private List<Role> members = new ArrayList<>();

    // [기존 코드]
    @Override
    public void save(Role member) {
        members.add(member);
    }

    // [기존 코드]
    @Override
    public Role findByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    // [기존 코드] 빠뜨리지 않고 확실하게 유지합니다!
    @Override
    public List<Role> findAll() {
        return members;
    }

    // [기존 코드]
    @Override
    public boolean existsByName(String name) {
        for (Role member : members) {
            if (member.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // [지침 조건 추가] updatedByName: 이름이 일치하면 해당 인덱스를 교체한다.
    @Override
    public void updateByName(String name, Role member) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getName().equals(name)) {
                members.set(i, member);
                return;
            }
        }
    }

    // [지침 조건 추가] deleteByName: removeIf를 사용해 일치하는 멤버를 제거한다.
    @Override
    public boolean deleteByName(String name) {
        return members.removeIf(member -> member.getName().equals(name));
    }
}