import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Vehicle {
    private String licensePlate;
    private long entryTime;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = System.currentTimeMillis();
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public long getEntryTime() {
        return entryTime;
    }
}

class ParkingLot {
    private int capacity;
    private Map<String, Vehicle> parkedVehicles;
    private static final double RATE_PER_HOUR = 2.0;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkedVehicles = new HashMap<>();
    }

    public boolean parkVehicle(String licensePlate) {
        if (parkedVehicles.size() >= capacity) {
            System.out.println("Parking lot is full.");
            return false;
        }
        if (parkedVehicles.containsKey(licensePlate)) {
            System.out.println("Vehicle already parked.");
            return false;
        }
        parkedVehicles.put(licensePlate, new Vehicle(licensePlate));
        System.out.println("Vehicle parked successfully.");
        return true;
    }

    public double retrieveVehicle(String licensePlate) {
        Vehicle vehicle = parkedVehicles.remove(licensePlate);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return 0.0;
        }
        long parkedTime = System.currentTimeMillis() - vehicle.getEntryTime();
        double hours = parkedTime / (1000.0 * 60 * 60);
        double fee = Math.ceil(hours) * RATE_PER_HOUR;
        System.out.println("Vehicle retrieved. Parking fee: $" + fee);
        return fee;
    }

    public void displayAvailableSpots() {
        System.out.println("Available spots: " + (capacity - parkedVehicles.size()));
    }
}

public class ParkingLotSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = new ParkingLot(5); // Example with 5 slots

        while (true) {
            System.out.println("\n1. Park Vehicle\n2. Retrieve Vehicle\n3. Display Available Spots\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle license plate: ");
                    String licensePlate = scanner.nextLine();
                    parkingLot.parkVehicle(licensePlate);
                    break;
                case 2:
                    System.out.print("Enter vehicle license plate: ");
                    licensePlate = scanner.nextLine();
                    parkingLot.retrieveVehicle(licensePlate);
                    break;
                case 3:
                    parkingLot.displayAvailableSpots();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
