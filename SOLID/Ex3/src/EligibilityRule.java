import java.util.Optional;

public interface EligibilityRule {
    Optional<String> validate(StudentProfile s);
}
