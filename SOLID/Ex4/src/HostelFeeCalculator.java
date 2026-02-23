import java.util.*;

public class HostelFeeCalculator {

    private final FakeBookingRepo repo;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
    }

    public void process(BookingRequest req) {

        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {

        Money total = new Money(0);

        RoomPricing room = RoomRegistry.get(String.valueOf(req.roomType));
        total = total.add(room.baseMonthly());

        for (AddOn a : req.addOns) {
            PriceComponent comp = new AddOnComponent(a);
            total = total.add(comp.monthlyCost(req));
        }

        return total;
    }
}
