import java.util.*;

public class CreditsRule implements EligibilityRule {

    public Optional<String> validate(StudentProfile s) {
        if (s.earnedCredits < 20)
            return Optional.of("credits below 20");
        return Optional.empty();
    }
}
