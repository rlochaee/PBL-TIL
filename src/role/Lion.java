package role;
import policy.*;

public class Lion extends Person {
    private String studentId; // 아기사자 전용 속성

    public Lion(String name, String major, int generation, String part, String studentId) {
        super(name, major, generation, part);
        this.studentId = studentId;
    }

    @Override
    public PostPolicy getPolicy() {
        return new LionPolicy(); // 자신에게 맞는 정책 객체 반환
    }

    @Override
    public String getDetails() {
        return String.format("역할: 아기사자\n%s\n 학번: %s",
                getBaseInfo(), studentId);
    }
}