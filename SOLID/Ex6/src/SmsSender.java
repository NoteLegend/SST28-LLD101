public class SmsSender extends NotificationSender {

    public SmsSender(AuditLog audit) {
        super(audit);
    }

    @Override
    protected void validateChannel(Notification n) {
        if (n.phone == null) {
            throw new IllegalArgumentException("phone required");
        }
    }

    @Override
    protected void doSend(Notification n) {
        System.out.println(
                "SMS -> to=" + n.phone +
                        " body=" + n.body
        );

        audit.add("sms sent");
    }
}