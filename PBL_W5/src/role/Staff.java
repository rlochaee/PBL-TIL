package role;

import policy.SubmissionPolicy;
import policy.StaffSubmissionPolicy;

public class Staff implements Role {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String roleDescription;

    public Staff(String name, String major, int generation, String part, String roleDescription) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.roleDescription = roleDescription;
    }

    @Override
    public String getName() { return name; }

    @Override
    public SubmissionPolicy getPolicy() { return new StaffSubmissionPolicy(); }

    @Override
    public int getGeneration() { return generation; }

    @Override
    public String getDetails() {
        return String.format("이름: %s, 전공: %s, 기수: %d, 파트: %s\n역할: 운영진\n직책: %s",
                name, major, generation, part, roleDescription);
    }

    @Override
    public String getPart() {
        return part;
    }
}