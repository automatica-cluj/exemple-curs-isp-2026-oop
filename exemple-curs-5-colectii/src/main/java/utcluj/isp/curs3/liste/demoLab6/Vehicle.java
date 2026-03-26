package utcluj.isp.curs3.liste.demoLab6;

import java.util.Objects;

public class Vehicle {
    private String vin;
    private String model;

    public Vehicle(String vin, String model) {
        this.vin = vin;
        this.model = model;
    }

    public String getVin() {
        return vin;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vin, vehicle.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + vin + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
