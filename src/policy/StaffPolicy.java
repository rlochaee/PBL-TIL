package policy;

public class StaffPolicy implements PostPolicy {
        @Override
        public boolean canSubmit() {
            return false; // 지침: 운영진은 false 반환
        }
}
