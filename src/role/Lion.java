package role;

import policy.SubmissionPolicy;
import policy.LionSubmissionPolicy;

public class Lion implements Role {
    private String name;
    private String major;
    private int generation;
    private String part;
    private String studentId;

    public Lion(String name, String major, int generation, String part, String studentId) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
        this.studentId = studentId;
    }

    @Override
    public String getName() { return name; }

    @Override
    public SubmissionPolicy getPolicy() { return new LionSubmissionPolicy(); }

    @Override
    public int getGeneration() { return generation; }

    @Override
    public String getDetails() {
        return String.format("이름: %s, 전공: %s, 기수: %d, 파트: %s\n역할: 아기사자\n학번: %s",
                name, major, generation, part, studentId);
    }

    @Override
    public String getPart() {
        return part;
    }
}