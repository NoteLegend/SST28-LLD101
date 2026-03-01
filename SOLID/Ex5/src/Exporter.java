public abstract class Exporter {

    /**
     * Base contract:
     *
     * Preconditions:
     * - req must not be null
     *
     * Postconditions:
     * - returns non-null ExportResult
     * - ExportResult.contentType is non-null
     * - ExportResult.bytes is non-null
     * - no silent data corruption
     *
     * Only IllegalArgumentException is allowed if req is null.
     */
    public final ExportResult export(ExportRequest req) {
        validate(req);

        ExportResult result = doExport(req);

        if (result == null) {
            throw new IllegalStateException("Exporter returned null result");
        }
        if (result.contentType == null) {
            throw new IllegalStateException("contentType cannot be null");
        }
        if (result.bytes == null) {
            throw new IllegalStateException("bytes cannot be null");
        }

        return result;
    }

    protected void validate(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
    }

    protected abstract ExportResult doExport(ExportRequest req);
}