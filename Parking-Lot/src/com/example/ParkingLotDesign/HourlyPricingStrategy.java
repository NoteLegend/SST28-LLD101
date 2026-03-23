public class HourlyPricingStrategy implements PricingStrategy {
    @Override
    public double calculateAmount(long hours, VehicleType type) {
        double rate = switch (type) {
            case SMALL -> 10.0;
            case MEDIUM -> 20.0;
            case LARGE -> 50.0;
        };
        return Math.max(1, hours) * rate; // Minimum 1 hour charge
    }
}