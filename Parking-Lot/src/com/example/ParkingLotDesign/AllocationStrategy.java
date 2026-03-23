import java.util.List;

public interface AllocationStrategy {
    ParkingSlot findSlot(EntryGate gate, List<Level> levels, VehicleType type);
}