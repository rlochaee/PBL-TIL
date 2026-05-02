package role;
import policy.SubmissionPolicy;
public interface Role {
    String getName();
    String getDetails();
    int getGeneration();
    String getPart();
    SubmissionPolicy getPolicy();
}
