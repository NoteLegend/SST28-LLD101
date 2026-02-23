import java.util.*;

public class FileStore implements InvoiceRepository {

    private final Map<String,String> storage = new HashMap<>();

    public void save(String id, String text) {
        storage.put(id, text);
    }

    public int countLines(String id) {
        String s = storage.get(id);
        if (s == null || s.isEmpty()) return 0;
        return s.split("\n").length;
    }
}
