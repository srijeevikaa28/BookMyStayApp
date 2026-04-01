import java.io.*;
import java.util.*;

// ------------------------------
// Reservation Class (Serializable)
// ------------------------------
class Reservation implements Serializable {
    private String reservationId;
    private String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }

    public String getReservationId() {
        return reservationId;
    }

    public String getRoomType() {
        return roomType;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId + ", Room Type: " + roomType;
    }
}

// ------------------------------
// Inventory Class (Serializable)
// ------------------------------
class RoomInventory implements Serializable {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoom(String type, int count) {
        inventory.put(type, count);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void displayInventory() {
        System.out.println("Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}

// ------------------------------
// Persistence Service
// ------------------------------
class PersistenceService {

    private static final String FILE_NAME = "system_data.ser";

    // Save data
    public void saveData(List<Reservation> reservations, RoomInventory inventory) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reservations);
            oos.writeObject(inventory);
            System.out.println("✅ Data saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving data: " + e.getMessage());
        }
    }

    // Load data
    public Object[] loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            List<Reservation> reservations = (List<Reservation>) ois.readObject();
            RoomInventory inventory = (RoomInventory) ois.readObject();
            System.out.println("✅ Data loaded successfully.");
            return new Object[]{reservations, inventory};
        } catch (Exception e) {
            System.out.println("⚠️ No previous data found. Starting fresh.");
            return new Object[]{new ArrayList<Reservation>(), new RoomInventory()};
        }
    }
}

// ------------------------------
// Main Class (UC12)
// ------------------------------
public class UseCase12Persistence {

    public static void main(String[] args) {

        PersistenceService persistenceService = new PersistenceService();

        // Load previous data
        Object[] data = persistenceService.loadData();

        List<Reservation> reservations = (List<Reservation>) data[0];
        RoomInventory inventory = (RoomInventory) data[1];

        // If first run → initialize inventory
        if (inventory.getInventory().isEmpty()) {
            inventory.addRoom("Single", 5);
            inventory.addRoom("Double", 3);
            inventory.addRoom("Suite", 2);
        }

        // Simulate new booking
        Reservation r1 = new Reservation("R101", "Single");
        reservations.add(r1);

        System.out.println("\n=== Current Reservations ===");
        for (Reservation r : reservations) {
            System.out.println(r);
        }

        System.out.println();
        inventory.displayInventory();

        // Save state before exit
        persistenceService.saveData(reservations, inventory);
    }
}