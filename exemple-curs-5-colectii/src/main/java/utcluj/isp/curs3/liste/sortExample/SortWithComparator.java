package utcluj.isp.curs3.liste.sortExample;
import java.util.*;

public class SortWithComparator {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("Audi", "A4", 2019, 25000));
        vehicles.add(new Vehicle("BMW", "X5", 2020, 35000));
        vehicles.add(new Vehicle("Mercedes", "E200", 2018, 20000));
        vehicles.add(new Vehicle("Volkswagen", "Golf", 2017, 15000));

        System.out.println("Before sorting:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        /**
         * Comparing using a comparator.
         */
        java.util.Collections.sort(vehicles, new VehicleComparator());
        System.out.println("After sorting:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        /**
         * Comparing using the Comparable interface.
         */
        java.util.Collections.sort(vehicles);
        System.out.println("After sorting by make:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

    }
}

class VehicleComparator implements java.util.Comparator<Vehicle> {
    @Override
    public int compare(Vehicle o1, Vehicle o2) {
        return (int) (o1.getPrice() - o2.getPrice());
    }
}

class Vehicle implements Comparable<Vehicle>{
    private String make;
    private String model;
    private int year;
    private double price;

    public Vehicle(String make, String model, int year, double price) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }

    /**
     * Compares this object with the specified object for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     * We compare the make of the vehicle. This will allow us to sort alphabetically the vehicles by make.
     *
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Vehicle o) {
        return this.make.compareTo(o.getMake());
    }
}


