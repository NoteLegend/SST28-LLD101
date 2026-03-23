// ParkingLot.java
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class ParkingLot {
    private List<Level> levels = new ArrayList<>();
    private List<EntryGate> entryGates = new ArrayList<>();
    private AllocationStrategy allocationStrategy;
    private PricingStrategy pricingStrategy;

    // Package-private constructor for Factory use
    ParkingLot(AllocationStrategy allocation, PricingStrategy pricing) {
        this.allocationStrategy = allocation;
        this.pricingStrategy = pricing;
    }

    public void addLevel(Level level) { levels.add(level); }
    public void addGate(EntryGate gate) { entryGates.add(gate); }

    public synchronized Ticket park(String vehicleNo, VehicleType type, EntryGate gate) {
        ParkingSlot slot = allocationStrategy.findSlot(gate, levels, type);
        if (slot == null) {
            throw new RuntimeException("No available slot for type: " + type);
        }
        slot.setOccupied(true);
        return new Ticket(UUID.randomUUID().toString(), slot, vehicleNo);
    }

    public double exit(Ticket ticket) {
        ticket.getSlot().setOccupied(false);
        long hours = Duration.between(ticket.getEntryTime(), LocalDateTime.now()).toHours();
        return pricingStrategy.calculateAmount(hours, ticket.getSlot().getType());
    }

    public Map<VehicleType, Integer> getStatus() {
        Map<VehicleType, Integer> status = new EnumMap<>(VehicleType.class);
        for (VehicleType t : VehicleType.values()) status.put(t, 0);

        for (Level level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                if (!slot.isOccupied()) {
                    status.put(slot.getType(), status.get(slot.getType()) + 1);
                }
            }
        }
        return status;
    }
}