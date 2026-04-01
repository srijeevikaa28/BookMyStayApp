import java.util.*;

// Add-On Service class
class AddOnService {
    private String name;
    private double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Add-On Service Manager
class AddOnServiceManager {

    // Map: Reservation ID -> List of Services
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    // Add service to reservation
    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    // Get services for a reservation
    public List<AddOnService> getServices(String reservationId) {
        return serviceMap.getOrDefault(reservationId, new ArrayList<>());
    }

    // Calculate total cost
    public double calculateTotalCost(String reservationId) {
        double total = 0;
        for (AddOnService s : getServices(reservationId)) {
            total += s.getPrice();
        }
        return total;
    }
}

// Main App
public class BookMyStayApp {

    private Set<String> reservations = new HashSet<>();
    private AddOnServiceManager manager = new AddOnServiceManager();

    // Create reservation
    public void createReservation(String id) {
        reservations.add(id);
        System.out.println("Reservation created: " + id);
    }

    // Validate reservation
    public boolean isValidReservation(String id) {
        return reservations.contains(id);
    }

    // Add service
    public void addServiceToReservation(String id, AddOnService service) {
        if (!isValidReservation(id)) {
            System.out.println("❌ Invalid Reservation ID");
            return;
        }
        manager.addService(id, service);
        System.out.println("✅ Service added: " + service.getName());
    }

    // View services
    public void viewServices(String id) {
        List<AddOnService> services = manager.getServices(id);

        if (services.isEmpty()) {
            System.out.println("No services added.");
            return;
        }

        System.out.println("Services for " + id + ":");
        for (AddOnService s : services) {
            System.out.println("- " + s.getName() + " (₹" + s.getPrice() + ")");
        }
    }

    // Total cost
    public double getTotalCost(String id) {
        return manager.calculateTotalCost(id);
    }

    // Main method (test flow)
    public static void main(String[] args) {

        BookMyStayApp app = new BookMyStayApp();

        System.out.println("=== UC7: Add-On Service Selection ===");

        // Create reservation
        app.createReservation("R101");

        // Add services
        app.addServiceToReservation("R101", new AddOnService("Breakfast", 200));
        app.addServiceToReservation("R101", new AddOnService("Airport Pickup", 500));

        // View services
        app.viewServices("R101");

        // Total cost
        System.out.println("Total Add-On Cost: ₹" + app.getTotalCost("R101"));
    }
}