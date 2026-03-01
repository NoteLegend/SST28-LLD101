public abstract class NotificationSender {

    protected final AuditLog audit;

    protected NotificationSender(AuditLog audit) {
        if (audit == null) {
            throw new IllegalArgumentException("audit cannot be null");
        }
        this.audit = audit;
    }

    // Final → subclasses cannot change contract
    public final void send(Notification n) {
        validateBase(n);
        validateChannel(n);
        doSend(n);
    }

    // Base validation shared by all
    private void validateBase(Notification n) {
        if (n == null) {
            throw new IllegalArgumentException("notification cannot be null");
        }
        if (n.body == null) {
            throw new IllegalArgumentException("body cannot be null");
        }
    }

    // Optional channel-specific validation
    protected void validateChannel(Notification n) {
        // default: no extra validation
    }

    protected abstract void doSend(Notification n);
}