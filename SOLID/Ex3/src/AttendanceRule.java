import java.util.*;

public class AttendanceRule implements EligibilityRule {

    public Optional<String> validate(StudentProfile s) {
        if (s.attendancePct < 75)
            return Optional.of("attendance below 75");
        return Optional.empty();
    }
}
