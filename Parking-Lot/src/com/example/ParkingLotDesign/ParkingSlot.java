public class ParkingSlot {
    private final String id;
    private final VehicleType type;
    private boolean isOccupied;

    public ParkingSlot(String id, VehicleType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    // Getters and Setters
    public String getId() { return id; }
    public VehicleType getType() { return type; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
}