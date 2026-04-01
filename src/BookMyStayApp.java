import java.util.*;

// Reservation class
class Reservation {
    String reservationId;
    String roomType;
    String roomId;

    public Reservation(String reservationId, String roomType, String roomId) {
        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Inventory Service
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
    }

    public void incrementRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public void displayInventory() {
        System.out.println("Inventory: " + inventory);
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, Reservation> confirmedBookings = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();
    private RoomInventory inventory;

    public CancellationService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    // Add confirmed booking (simulate)
    public void addBooking(Reservation r) {
        confirmedBookings.put(r.reservationId, r);
    }

    // Cancel booking
    public void cancelBooking(String reservationId) {

        // Validation
        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("❌ Invalid or already cancelled reservation");
            return;
        }

        Reservation r = confirmedBookings.get(reservationId);

        // Push to rollback stack
        rollbackStack.push(r.roomId);

        // Restore inventory
        inventory.incrementRoom(r.roomType);

        // Remove booking
        confirmedBookings.remove(reservationId);

        System.out.println("✅ Booking cancelled: " + reservationId);
        System.out.println("↩ Rolled back Room ID: " + r.roomId);
    }

    public void displayRollbackStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

// Main App
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== UC10: Booking Cancellation & Rollback ===");

        RoomInventory inventory = new RoomInventory();
        CancellationService service = new CancellationService(inventory);

        // Simulate confirmed booking
        service.addBooking(new Reservation("R101", "Single", "S1"));

        // Cancel booking
        service.cancelBooking("R101");

        // Try invalid cancellation
        service.cancelBooking("R101");

        // Display state
        inventory.displayInventory();
        service.displayRollbackStack();
    }
}