package package2;

import role.*;
import java.util.*;

public class MockMemberRepository implements MemberRepository {
    @Override
    public void save(Role member) {
        System.out.println("[Mock] 실제 저장되지 않습니다."); }

    @Override
    public List<Role> findAll() { return null; }

    @Override
    public Role findByName(String name) { return null; }

    @Override
    public int size() { return 0; }
}