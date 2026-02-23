import java.util.*;

public class CafeteriaSystem {

    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repo;
    private final TaxPolicy taxPolicy;
    private final DiscountPolicy discountPolicy;
    private final InvoicePrinter printer;
    private int invoiceSeq = 1000;

    public CafeteriaSystem(
            InvoiceRepository repo,
            TaxPolicy taxPolicy,
            DiscountPolicy discountPolicy,
            InvoicePrinter printer
    ) {
        this.repo = repo;
        this.taxPolicy = taxPolicy;
        this.discountPolicy = discountPolicy;
        this.printer = printer;
    }

    public void addToMenu(MenuItem i) {
        menu.put(i.id, i);
    }

    public void checkout(String customerType, List<OrderLine> lines) {

        String invId = "INV-" + (++invoiceSeq);

        PricingEngine pricing = new PricingEngine(menu);
        PricingSummary sum = pricing.calculate(lines);

        double taxPct = taxPolicy.taxPercent(customerType);
        double tax = sum.subtotal * taxPct / 100.0;

        double discount =
                discountPolicy.discount(customerType, sum.subtotal, lines.size());

        double total = sum.subtotal + tax - discount;

        Invoice inv = new Invoice();
        inv.id = invId;
        inv.lines = sum.lineDescriptions;
        inv.subtotal = sum.subtotal;
        inv.taxPct = taxPct;
        inv.tax = tax;
        inv.discount = discount;
        inv.total = total;

        String printable = printer.format(inv);

        System.out.print(printable);

        repo.save(invId, printable);

        System.out.println("Saved invoice: " + invId +
                " (lines=" + repo.countLines(invId) + ")");
    }
}
