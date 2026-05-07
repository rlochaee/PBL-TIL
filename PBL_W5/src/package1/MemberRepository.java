package package1;

import role.Role;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {
    private List<Role> memberList = new ArrayList<>();

    public void save(Role member) {
        memberList.add(member);
    }

    public List<Role> findAll() {
        return memberList;
    }

    public Role findByName(String name) {
        for (Role member : memberList) {
            if (member.getName().equals(name)) {
                return member;
            }
        }
        return null;
    }

    public int size() {
        return memberList.size();
    }
}