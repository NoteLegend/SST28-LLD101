import java.util.List;
import java.util.Map;

public class ParkingLotFactory {
    public static ParkingLot createParkingLot(
            int numFloors, 
            Map<Integer, List<ParkingSlot>> levelMap,
            List<EntryGate> gates) {
        
        // Using standard strategies by default, but could be injected
        ParkingLot lot = new ParkingLot(new NearestSlotStrategy(), new HourlyPricingStrategy());

        // Setup Levels
        for (int i = 0; i < numFloors; i++) {
            Level level = new Level(i);
            List<ParkingSlot> slots = levelMap.getOrDefault(i, new java.util.ArrayList<>());
            slots.forEach(level::addSlot);
            lot.addLevel(level);
        }

        // Setup Gates
        gates.forEach(lot::addGate);

        return lot;
    }
}