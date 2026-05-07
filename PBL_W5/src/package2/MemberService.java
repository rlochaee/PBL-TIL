package package2;

import role.Role;
import java.util.List;

public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public boolean join(Role member) {
        if (repository.findByName(member.getName()) != null) return false;
        repository.save(member);
        return true;
    }

    public List<Role> findMembers() { return repository.findAll(); }
    public Role findOne(String name) { return repository.findByName(name); }
    public int getTotalCount() { return repository.size(); }
}