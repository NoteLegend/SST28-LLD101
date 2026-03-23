import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final LocalDateTime entryTime;
    private final ParkingSlot slot;
    private final String vehicleNumber;

    public Ticket(String ticketId, ParkingSlot slot, String vehicleNumber) {
        this.ticketId = ticketId;
        this.slot = slot;
        this.vehicleNumber = vehicleNumber;
        this.entryTime = LocalDateTime.now();
    }

    public LocalDateTime getEntryTime() { return entryTime; }
    public ParkingSlot getSlot() { return slot; }
}