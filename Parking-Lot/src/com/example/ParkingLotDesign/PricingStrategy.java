public interface PricingStrategy {
    double calculateAmount(long hours, VehicleType type);
}