package package2;

import role.Role;
import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private List<Role> memberList = new ArrayList<>();

    @Override
    public void save(Role member) { memberList.add(member); }

    @Override
    public Role findByName(String name) {
        for (Role m : memberList) {
            if (m.getName().equals(name)) return m;
        }
        return null;
    }

    @Override
    public List<Role> findAll() { return memberList; }

    @Override
    public int size() { return memberList.size(); }
}