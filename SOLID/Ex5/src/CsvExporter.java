import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {

    @Override
    protected ExportResult doExport(ExportRequest req) {

        String csv = "title,body\n"
                + escape(req.title) + ","
                + escape(req.body) + "\n";

        return new ExportResult(
                "text/csv",
                csv.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String escape(String s) {
        if (s == null) return "";
        // Proper CSV escaping: wrap in quotes and escape quotes
        return "\"" + s.replace("\"", "\"\"") + "\"";
    }
}