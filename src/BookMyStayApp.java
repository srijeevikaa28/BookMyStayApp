import java.util.*;

// Reservation class
class Reservation {
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

// Booking History (stores data)
class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        history.add(reservation);
    }

    // Get all reservations
    public List<Reservation> getAllReservations() {
        return history;
    }
}

// Report Service (read-only)
class BookingReportService {

    public void displayAllBookings(List<Reservation> reservations) {
        System.out.println("=== Booking History ===");

        if (reservations.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        for (Reservation r : reservations) {
            System.out.println(r);
        }
    }

    public void displayTotalBookings(List<Reservation> reservations) {
        System.out.println("Total Bookings: " + reservations.size());
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("=== UC8: Booking History & Reporting ===");

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings
        history.addReservation(new Reservation("R101", "Single"));
        history.addReservation(new Reservation("R102", "Double"));
        history.addReservation(new Reservation("R103", "Suite"));

        // Display reports
        reportService.displayAllBookings(history.getAllReservations());
        reportService.displayTotalBookings(history.getAllReservations());
    }
}