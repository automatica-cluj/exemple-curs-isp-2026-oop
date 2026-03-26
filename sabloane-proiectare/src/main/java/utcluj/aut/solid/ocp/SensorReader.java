package utcluj.aut.solid.ocp;

public class SensorReader {
    public void displayReading(Sensor sensor) {
        System.out.println("Reading: " + sensor.readValue() + " " + sensor.getUnit());
    }
}
