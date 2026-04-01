import java.util.*;

// Reservation class
class Reservation {
    String reservationId;
    String roomType;

    public Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

// Shared Inventory (Thread-Safe)
class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public RoomInventory() {
        inventory.put("Single", 1);
    }

    // synchronized method (critical section)
    public synchronized void allocateRoom(Reservation r) {
        int available = inventory.getOrDefault(r.roomType, 0);

        if (available > 0) {
            System.out.println(Thread.currentThread().getName() +
                    " booking SUCCESS for " + r.reservationId);

            inventory.put(r.roomType, available - 1);
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " booking FAILED for " + r.reservationId + " (No rooms)");
        }
    }

    public void displayInventory() {
        System.out.println("Final Inventory: " + inventory);
    }
}

// Booking Processor (Runnable)
class BookingProcessor implements Runnable {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) {
            Reservation r;

            // synchronized block for queue access
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }

            // allocate room
            inventory.allocateRoom(r);
        }
    }
}

// Main App
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== UC11: Concurrent Booking Simulation ===");

        Queue<Reservation> bookingQueue = new LinkedList<>();
        RoomInventory inventory = new RoomInventory();

        // Add booking requests
        bookingQueue.add(new Reservation("R101", "Single"));
        bookingQueue.add(new Reservation("R102", "Single"));

        // Create threads
        Thread t1 = new Thread(new BookingProcessor(bookingQueue, inventory), "Thread-1");
        Thread t2 = new Thread(new BookingProcessor(bookingQueue, inventory), "Thread-2");

        // Start threads
        t1.start();
        t2.start();

        // Wait for completion
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Final state
        inventory.displayInventory();
    }
}