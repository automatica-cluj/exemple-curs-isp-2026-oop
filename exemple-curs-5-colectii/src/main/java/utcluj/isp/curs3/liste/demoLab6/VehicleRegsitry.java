package utcluj.isp.curs3.liste.demoLab6;
import java.util.*;

public class VehicleRegsitry {
    private HashSet<Vehicle> list = new HashSet<>();

    public void addVehicle(Vehicle v){
        list.add(v);
    }

    public void removeVehicle(Vehicle v){
        list.remove(v);
    }

    public void removeVehicle(String vin){
        list.remove(new Vehicle(vin,""));
    }

    public boolean searchByVin(String vin){
        return list.contains(new Vehicle(vin,""));
    }

    public Vehicle getByVin(String vin){
        for(Vehicle v:list)
            if(v.getVin().equalsIgnoreCase(vin))
                return v;
        return null;
    }

    public Vehicle getByVinWithStrems(String vin) {// Convert list to a stream
        Optional<Vehicle> result = list.stream()
                .filter(v -> v.getVin().equalsIgnoreCase(vin)) // Use filter to find matching VIN
                .findFirst(); // Retrieve the first matching result, if any

        return result.orElse(null); // Return the found Vehicle or null if not found
    }

    public void displayAllVehicles1(){
        for(Vehicle v:list)
            System.out.println(v);
    }

    public void displayAllVehicles2(){
        list.stream().forEach(System.out::println);
    }
}
