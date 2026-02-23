public class AddOnComponent implements PriceComponent {

    private final AddOn addOn;

    public AddOnComponent(AddOn a) { this.addOn = a; }

    public Money monthlyCost(BookingRequest req) {
        return switch(addOn) {
            case MESS -> new Money(1000);
            case LAUNDRY -> new Money(500);
            case GYM -> new Money(300);
        };
    }
}
