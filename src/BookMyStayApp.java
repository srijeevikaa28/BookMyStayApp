import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
    }

    public void bookRoom(String roomType) throws InvalidBookingException {

        // Validate room type
        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("❌ Invalid Room Type");
        }

        // Check availability
        int available = inventory.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("❌ No rooms available for " + roomType);
        }

        // Update inventory
        inventory.put(roomType, available - 1);

        System.out.println("✅ Booking confirmed for " + roomType);
    }

    public void displayInventory() {
        System.out.println("Current Inventory: " + inventory);
    }
}

// Main App
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== UC9: Error Handling & Validation ===");

        RoomInventory inventory = new RoomInventory();

        try {
            inventory.bookRoom("Single");   // valid
            inventory.bookRoom("Suite");    // no availability
            inventory.bookRoom("Luxury");   // invalid type
        } catch (InvalidBookingException e) {
            System.out.println(e.getMessage());
        }

        // System continues safely
        inventory.displayInventory();
    }
}