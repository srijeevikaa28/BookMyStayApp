/**
 * Book My Stay App
 * Version 2.0
 * Demonstrates Room Types using Abstraction & Inheritance
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

    // 🔹 SINGLE ROOM
    static class SingleRoom extends Room {
        SingleRoom() {
            super("Single Room", 1, 2000);
        }
    }

    // 🔹 DOUBLE ROOM
    static class DoubleRoom extends Room {
        DoubleRoom() {
            super("Double Room", 2, 3500);
        }
    }

    // 🔹 SUITE ROOM
    static class SuiteRoom extends Room {
        SuiteRoom() {
            super("Suite Room", 3, 5000);
        }
    }

    public static void main(String[] args) {

        System.out.println("===== Welcome to Book My Stay App =====");
        System.out.println("Version: 2.0\n");

        // 🔹 OBJECT CREATION (POLYMORPHISM)
        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // 🔹 STATIC AVAILABILITY
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        // 🔹 DISPLAY DETAILS
        System.out.println("---- Room Details ----");

        single.displayDetails();
        System.out.println("Available : " + singleAvailable + "\n");

        doubleroom.displayDetails();
        System.out.println("Available : " + doubleAvailable + "\n");

        suite.displayDetails();
        System.out.println("Available : " + suiteAvailable + "\n");

        System.out.println("Application Terminated.");
    }
}