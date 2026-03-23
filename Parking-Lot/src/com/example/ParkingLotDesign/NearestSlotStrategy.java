// NearestSlotStrategy.java
import java.util.List;

public class NearestSlotStrategy implements AllocationStrategy {
    @Override
    public ParkingSlot findSlot(EntryGate gate, List<Level> levels, VehicleType type) {
        ParkingSlot nearestSlot = null;
        double minDistance = Double.MAX_VALUE;

        for (Level level : levels) {
            for (ParkingSlot slot : level.getSlots()) {
                if (!slot.isOccupied() && slot.getType() == type) {
                    double distance = gate.getDistanceToSlot(slot.getId());
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestSlot = slot;
                    }
                }
            }
        }
        return nearestSlot;
    }
}

// ParkingLotFactory.java
