import java.util.List;

public class PricingSummary {
    public double subtotal;
    public List<String> lineDescriptions;

    public PricingSummary(double subtotal, List<String> lines) {
        this.subtotal = subtotal;
        this.lineDescriptions = lines;
    }
}
