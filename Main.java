package vehicle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] tokens = scanner.nextLine().split(" ");
        Vehicle car = getVehicle(tokens);

        tokens = scanner.nextLine().split(" ");
        Vehicle truck = getVehicle(tokens);

        tokens = scanner.nextLine().split(" ");
        Vehicle bus = getVehicle(tokens);


        Map<String, Vehicle> vehicles = new LinkedHashMap<>();
        vehicles.put("Car", car);
        vehicles.put("Truck", truck);
        vehicles.put("Bus", bus);

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            tokens = scanner.nextLine().split(" ");
            String commandName = tokens[0];
            String vehicleType = tokens[1];
            Vehicle vehicle = vehicles.get(vehicleType);
            try{
                switch (commandName) {
                    case "Drive" -> {
                        double distance = Double.parseDouble(tokens[2]);
                        if (vehicle instanceof Bus) {
                            ((Bus) vehicle).setEmpty(false);
                        }
                        System.out.println(vehicle.drive(distance));
                    }
                    case "Refuel" -> {
                        double liters = Double.parseDouble(tokens[2]);
                        vehicles.get(vehicleType).refuel(liters);
                    }
                    case "DriveEmpty" -> {
                        double driveEmptyBusDistance = Double.parseDouble(tokens[2]);
                        if (vehicle instanceof Bus) {
                            ((Bus) vehicle).setEmpty(true);
                        }
                        System.out.println(vehicle.drive(driveEmptyBusDistance));
                    }
                    default -> throw new IllegalArgumentException("No such command");
                }
            } catch (IllegalArgumentException exception){
                System.out.println(exception.getMessage());
            }
        }
        vehicles.values().forEach(System.out::println);
    }

    private static Vehicle getVehicle(String[] tokens) {
        String vehicleType = tokens[0];
        double fuelAmount = Double.parseDouble(tokens[1]);
        double consumption = Double.parseDouble(tokens[2]);
        double tankCapacity = Double.parseDouble(tokens[3]);
        return switch (vehicleType) {
            case "Car" -> new Car(fuelAmount, consumption, tankCapacity);
            case "Bus" -> new Bus(fuelAmount, consumption, tankCapacity);
            case "Truck" -> new Truck(fuelAmount, consumption, tankCapacity);
            default -> null;
        };
    }
}
