import java.util.*;

public class CgrRule implements EligibilityRule {

    public Optional<String> validate(StudentProfile s) {
        if (s.cgr < 8.0)
            return Optional.of("CGR below 8.0");
        return Optional.empty();
    }
}
