package utcluj.aut.solid.ocp;

public class TemperatureSensorOCP implements Sensor {
    public double readValue() {
        return 25.5; // sample reading
    }

    public String getUnit() {
        return "°C";
    }
}
