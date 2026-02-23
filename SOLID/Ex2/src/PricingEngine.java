import java.util.*;

public class PricingEngine {

    private final Map<String, MenuItem> menu;

    public PricingEngine(Map<String, MenuItem> menu) {
        this.menu = menu;
    }

    public PricingSummary calculate(List<OrderLine> lines) {
        double subtotal = 0;
        List<String> out = new ArrayList<>();

        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;

            out.add(String.format("- %s x%d = %.2f",
                    item.name, l.qty, lineTotal));
        }

        return new PricingSummary(subtotal, out);
    }
}
