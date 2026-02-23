import java.util.*;

public class OnboardingService {

    private final StudentRepository repo;
    private final StudentInputParser parser;
    private final StudentValidator validator;
    private final OnboardingPrinter printer;

    public OnboardingService(StudentRepository repo,
                             StudentInputParser parser,
                             StudentValidator validator,
                             OnboardingPrinter printer) {
        this.repo = repo;
        this.parser = parser;
        this.validator = validator;
        this.printer = printer;
    }


    public void registerFromRawInput(String raw) {

        printer.printInput(raw);

        Map<String,String> kv = parser.parse(raw);

        ValidationResult result = validator.validate(kv);

        if (!result.isValid()) {
            printer.printErrors(result.getErrors());
            return;
        }

        String id = IdUtil.nextStudentId(repo.count());

        StudentRecord rec = new StudentRecord(
                id,
                kv.get("name"),
                kv.get("email"),
                kv.get("phone"),
                kv.get("program")
        );

        repo.save(rec);

        printer.printSuccess(id, repo.count());
        printer.printRecord(rec);
    }
}
