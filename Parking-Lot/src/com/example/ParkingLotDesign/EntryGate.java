// EntryGate.java
import java.util.HashMap;
import java.util.Map;

public class EntryGate {
    private final String gateId;
    // Map of SlotID to Distance from this gate
    private final Map<String, Double> slotDistances = new HashMap<>();

    public EntryGate(String gateId) {
        this.gateId = gateId;
    }

    public void addDistance(String slotId, double distance) {
        slotDistances.put(slotId, distance);
    }

    public double getDistanceToSlot(String slotId) {
        return slotDistances.getOrDefault(slotId, Double.MAX_VALUE);
    }
    
    public String getGateId() { return gateId; }
}

