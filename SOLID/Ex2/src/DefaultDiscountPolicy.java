public class DefaultDiscountPolicy implements DiscountPolicy {
    public double discount(String customerType, double subtotal, int lines) {
        return DiscountRules.discountAmount(customerType, subtotal, lines);
    }
}
