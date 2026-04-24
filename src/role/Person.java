package role;
import policy.PostPolicy;
public abstract class Person {
    private String name;
    private String major;
    private int generation;
    private String part;

    public Person(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public String getBaseInfo() {
        return String.format("이름: %s, 전공: %s, 기수: %d, 파트: %s", name, major, generation, part);
    }

    public abstract PostPolicy getPolicy();
    public abstract String getDetails();
}
