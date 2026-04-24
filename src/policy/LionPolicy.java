package policy;

public class LionPolicy implements PostPolicy {
    @Override
    public boolean canSubmit() {
        return true; // 지침: 아기사자는 true 반환
    }
}
