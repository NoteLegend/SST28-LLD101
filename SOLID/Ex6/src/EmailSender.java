public class EmailSender extends NotificationSender {

    public EmailSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validateChannel(Notification n) {
        if (n.email == null) {
            throw new IllegalArgumentException("email required");
        }
    }

    @Override
    protected void doSend(Notification n) {
        String body = n.body;

        // Keep existing behavior for compatibility
        if (body.length() > 40) {
            body = body.substring(0, 40);
        }

        System.out.println(
                "EMAIL -> to=" + n.email +
                        " subject=" + n.subject +
                        " body=" + body
        );

        audit.add("email sent");
    }
}