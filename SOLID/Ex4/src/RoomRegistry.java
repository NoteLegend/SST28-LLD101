import java.util.*;

public class RoomRegistry {

    private static final Map<String, RoomPricing> map = new HashMap<>();

    static {
        map.put(String.valueOf(LegacyRoomTypes.SINGLE), new SingleRoom());
        map.put(String.valueOf(LegacyRoomTypes.DOUBLE), new DoubleRoom());
        map.put(String.valueOf(LegacyRoomTypes.TRIPLE), new TripleRoom());
    }

    public static RoomPricing get(String type) {
        return map.getOrDefault(type, new DoubleRoom());
    }
}
