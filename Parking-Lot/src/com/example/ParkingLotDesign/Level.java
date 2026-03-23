// Level.java
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final int levelId;
    private final List<ParkingSlot> slots;

    public Level(int levelId) {
        this.levelId = levelId;
        this.slots = new ArrayList<>();
    }

    public void addSlot(ParkingSlot slot) {
        slots.add(slot);
    }

    public List<ParkingSlot> getSlots() { return slots; }
}