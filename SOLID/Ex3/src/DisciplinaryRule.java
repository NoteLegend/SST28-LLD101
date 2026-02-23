import java.util.*;

public class DisciplinaryRule implements EligibilityRule {

    public Optional<String> validate(StudentProfile s) {
        if (s.disciplinaryFlag != LegacyFlags.NONE)
            return Optional.of("disciplinary flag present");
        return Optional.empty();
    }
}
