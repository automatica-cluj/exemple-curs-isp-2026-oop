package utcluj.aut.solid.ocp;

public class PressureSensor implements Sensor {
    public double readValue() {
        return 101.3; // sample reading
    }

    public String getUnit() {
        return "kPa";
    }
}
