import java.util.*;

public class ParkingSimulation {
    public static void main(String[] args) {
        // 1. Setup Physical Layout
        // Gate A is on Level 0, Gate B is on Level 1
        EntryGate gateA = new EntryGate("GATE_A");
        EntryGate gateB = new EntryGate("GATE_B");

        // 2. Define Slots for Level 0
        ParkingSlot s1 = new ParkingSlot("L0-S1", VehicleType.MEDIUM);
        ParkingSlot s2 = new ParkingSlot("L0-S2", VehicleType.SMALL);
        
        // 3. Define Slots for Level 1
        ParkingSlot s3 = new ParkingSlot("L1-S1", VehicleType.MEDIUM);

        // 4. Map Distances (The "Pre-existing Layout")
        // Gate A is very close to L0-S1 (distance 5) but far from L1-S1 (distance 50)
        gateA.addDistance("L0-S1", 5.0);
        gateA.addDistance("L0-S2", 10.0);
        gateA.addDistance("L1-S1", 50.0);

        // Gate B is on Level 1, so it's closer to L1-S1 (distance 2)
        gateB.addDistance("L0-S1", 40.0);
        gateB.addDistance("L1-S1", 2.0);

        // 5. Use Factory to Create Parking Lot
        Map<Integer, List<ParkingSlot>> levelMap = new HashMap<>();
        levelMap.put(0, Arrays.asList(s1, s2));
        levelMap.put(1, Arrays.asList(s3));

        ParkingLot myLot = ParkingLotFactory.createParkingLot(
            2, 
            levelMap, 
            Arrays.asList(gateA, gateB)
        );

        // 6. Simulation: Parking a Car (MEDIUM) at Gate B
        System.out.println("Status before: " + myLot.getStatus());
            
        // Should pick L1-S1 because it's closer to Gate B than L0-S1
        Ticket t1 = myLot.park("KA-01-HH-1234", VehicleType.MEDIUM, gateB);
        System.out.println("Parked at Slot: " + t1.getSlot().getId());
        // 7. Simulation: Exit and Payment
        // Thread.sleep(2000); // Simulate time passing
        double fee = myLot.exit(t1);
        System.out.println("Fees Paid: $" + fee);
        
        System.out.println("Status after: " + myLot.getStatus());
    }
}