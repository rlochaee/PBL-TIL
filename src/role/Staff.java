package role;
import policy.*;

public class Staff extends Person {
    private String roleDescription; // 직책

    public Staff(String name, String major, int generation, String part, String roleDescription) {
        super(name, major, generation, part);
        this.roleDescription = roleDescription;
    }

    @Override
    public PostPolicy getPolicy() {
        return new StaffPolicy(); // 자신에게 맞는 정책 객체 반환
    }

    @Override
    public String getDetails() {
        return String.format("역할: 운영진\n%s\n 직책: %s",
                getBaseInfo(), roleDescription);
    }
}