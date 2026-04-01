import java.util.*;

public class BookMyStayApp {

    // 🔹 Reservation Class
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // 🔹 Booking Queue (FIFO)
    static class BookingQueue {
        private Queue<Reservation> queue = new LinkedList<>();

        void addRequest(Reservation r) {
            queue.add(r);
        }

        Reservation getNextRequest() {
            return queue.poll(); // FIFO
        }

        boolean hasRequests() {
            return !queue.isEmpty();
        }
    }

    // 🔹 Inventory Service
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        RoomInventory() {
            inventory.put("Single Room", 2);
            inventory.put("Double Room", 1);
            inventory.put("Suite Room", 1);
        }

        int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        void reduceRoom(String roomType) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }
    }

    // 🔥 UC6 — Booking Service (Allocation)
    static class BookingService {

        private RoomInventory inventory;

        // 🔹 Track allocated room IDs (global uniqueness)
        private Set<String> allocatedRoomIds = new HashSet<>();

        // 🔹 Map room type → allocated IDs
        private Map<String, Set<String>> roomAllocations = new HashMap<>();

        private int roomCounter = 1;

        BookingService(RoomInventory inventory) {
            this.inventory = inventory;
        }

        void processBookings(BookingQueue queue) {

            System.out.println("---- Booking Confirmation ----");

            while (queue.hasRequests()) {

                Reservation r = queue.getNextRequest();

                int available = inventory.getAvailability(r.roomType);

                if (available > 0) {

                    // 🔹 Generate unique room ID
                    String roomId = r.roomType.substring(0, 2).toUpperCase() + roomCounter++;

                    // 🔹 Ensure uniqueness
                    if (!allocatedRoomIds.contains(roomId)) {

                        allocatedRoomIds.add(roomId);

                        // 🔹 Map room type → IDs
                        roomAllocations
                                .computeIfAbsent(r.roomType, k -> new HashSet<>())
                                .add(roomId);

                        // 🔹 Update inventory
                        inventory.reduceRoom(r.roomType);

                        System.out.println("Booking Confirmed");
                        System.out.println("Guest : " + r.guestName);
                        System.out.println("Room  : " + r.roomType);
                        System.out.println("Room ID : " + roomId);
                        System.out.println();
                    }

                } else {
                    System.out.println("Booking Failed (No Availability)");
                    System.out.println("Guest : " + r.guestName);
                    System.out.println("Room  : " + r.roomType);
                    System.out.println();
                }
            }
        }
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");
        System.out.println("Version: 6.0\n");

        // 🔹 Queue
        BookingQueue queue = new BookingQueue();
        queue.addRequest(new Reservation("Alice", "Single Room"));
        queue.addRequest(new Reservation("Bob", "Double Room"));
        queue.addRequest(new Reservation("Charlie", "Single Room"));
        queue.addRequest(new Reservation("David", "Suite Room"));

        // 🔹 Inventory
        RoomInventory inventory = new RoomInventory();

        // 🔹 Booking Service
        BookingService service = new BookingService(inventory);

        // 🔹 Process bookings
        service.processBookings(queue);
    }
}