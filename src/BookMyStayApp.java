import java.util.HashMap;
import java.util.Map;

public class BookMyStayApp {

    static class Room {
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

    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single Room", 5);
            inventory.put("Double Room", 3);
            inventory.put("Suite Room", 0);
        }

        int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }
    }

    public static void main(String[] args) {

        Room r1 = new Room("Single Room", 1, 2000);
        Room r2 = new Room("Double Room", 2, 3500);
        Room r3 = new Room("Suite Room", 3, 5000);

        Room[] rooms = {r1, r2, r3};

        RoomInventory inventory = new RoomInventory();

        System.out.println("---- Available Rooms ----");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.roomType);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available : " + available);
                System.out.println();
            }
        }
    }
}