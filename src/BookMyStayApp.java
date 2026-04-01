import java.util.HashMap;
import java.util.Map;

/**
 * Book My Stay App
 * Version 3.0
 * Demonstrates Centralized Inventory using HashMap
 */
public class BookMyStayApp {

    // 🔹 ABSTRACT CLASS
    static abstract class Room {
        String roomType;
        int beds;
        double price;

        Room(String roomType, int beds, double price) {
            this.roomType = roomType;
            this.beds = beds;
            this.price = price;
        }

        void displayDetails() {
            System.out.println("Room Type : " + roomType);
            System.out.println("Beds      : " + beds);
            System.out.println("Price     : " + price);
        }
    }

    // 🔹 ROOM TYPES
    static class SingleRoom extends Room {
        SingleRoom() {
            super("Single Room", 1, 2000);
        }
    }

    static class DoubleRoom extends Room {
        DoubleRoom() {
            super("Double Room", 2, 3500);
        }
    }

    static class SuiteRoom extends Room {
        SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    // 🔥 UC3 — INVENTORY CLASS (IMPORTANT)
    static class RoomInventory {

        private Map<String, Integer> inventory;

        // Constructor
        RoomInventory() {
            inventory = new HashMap<>();

            // Initial availability
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 2);
        }

        // Get availability
        int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Update availability
        void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }

        // Display all inventory
        void displayInventory() {
            System.out.println("---- Room Inventory ----");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " → Available: " + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("===== Welcome to Book My Stay App =====");
        System.out.println("Version: 3.0\n");

        // 🔹 Room objects
        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 🔹 Inventory initialization
        RoomInventory inventory = new RoomInventory();

        // 🔹 Display Room Details + Availability
        System.out.println("---- Room Details ----");

        single.displayDetails();
        System.out.println("Available : " + inventory.getAvailability("Single Room") + "\n");

        doubleroom.displayDetails();
        System.out.println("Available : " + inventory.getAvailability("Double Room") + "\n");

        suite.displayDetails();
        System.out.println("Available : " + inventory.getAvailability("Suite Room") + "\n");

        // 🔹 Show full inventory
        inventory.displayInventory();

        // 🔹 Example update
        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Update:");
        inventory.displayInventory();

        System.out.println("\nApplication Terminated.");
    }
}