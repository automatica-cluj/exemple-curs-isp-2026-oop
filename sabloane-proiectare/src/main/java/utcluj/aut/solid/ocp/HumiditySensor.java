package utcluj.aut.solid.ocp;

public class HumiditySensor implements Sensor {
    public double readValue() {
        return 65.0; // sample reading
    }

    public String getUnit() {
        return "%";
    }
}
