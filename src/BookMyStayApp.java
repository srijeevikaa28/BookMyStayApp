import java.util.LinkedList;
import java.util.Queue;

public class BookMyStayApp {

    // 🔹 Reservation Class
    static class Reservation {
        String guestName;
        String roomType;

        Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        void displayReservation() {
            System.out.println("Guest Name : " + guestName);
            System.out.println("Room Type  : " + roomType);
            System.out.println();
        }
    }

    // 🔹 Booking Queue Class
    static class BookingQueue {

        private Queue<Reservation> queue;

        BookingQueue() {
            queue = new LinkedList<>();
        }

        // Add booking request
        void addRequest(Reservation reservation) {
            queue.add(reservation);
        }

        // Display all requests (FIFO order)
        void displayRequests() {
            System.out.println("---- Booking Requests (FIFO) ----");

            for (Reservation r : queue) {
                r.displayReservation();
            }
        }
    }

    // 🔹 MAIN METHOD
    public static void main(String[] args) {

        System.out.println("===== Book My Stay App =====");
        System.out.println("Version: 5.0\n");

        // 🔹 Initialize Booking Queue
        BookingQueue bookingQueue = new BookingQueue();

        // 🔹 Add booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));

        // 🔹 Display requests (FIFO order)
        bookingQueue.displayRequests();

        System.out.println("All requests stored in queue. No allocation done.");
    }
}