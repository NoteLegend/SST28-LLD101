import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Format limitation — still allowed, but now within the defined contract
        if (req.body != null && req.body.length() > 20) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }

        String fakePdf = "PDF(" + safe(req.title) + "):" + safe(req.body);

        return new ExportResult(
                "application/pdf",
                fakePdf.getBytes(StandardCharsets.UTF_8)
        );
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }
}