package package1;

import role.Role;
import java.util.List;

public class MemberService {
    private MemberRepository repository = new MemberRepository();
    public boolean join(Role member) {
        if (repository.findByName(member.getName()) != null) {
            return false;
        }
        repository.save(member);
        return true;
    }
    // 전체 조회
    public List<Role> findMembers() {
        return repository.findAll();
    }
    // 이름 검색
    public Role findOne(String name) {
        return repository.findByName(name);
    }
    // 총 인원수 확인
    public int getTotalCount() {
        return repository.size();
    }
}