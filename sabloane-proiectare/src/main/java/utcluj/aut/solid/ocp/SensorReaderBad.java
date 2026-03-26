package utcluj.aut.solid.ocp;

// BAD: breaks OCP by requiring modification for each new sensor type
public class SensorReaderBad {
    public void displayReading(Object sensor) {
        if (sensor instanceof TemperatureSensorOCP) {
            TemperatureSensorOCP temp = (TemperatureSensorOCP) sensor;
            System.out.println("Temperature: " + temp.readValue() + " " + temp.getUnit());
        } else if (sensor instanceof PressureSensor) {
            PressureSensor pressure = (PressureSensor) sensor;
            System.out.println("Pressure: " + pressure.readValue() + " " + pressure.getUnit());
        } else if (sensor instanceof HumiditySensor) {
            HumiditySensor humidity = (HumiditySensor) sensor;
            System.out.println("Humidity: " + humidity.readValue() + " " + humidity.getUnit());
        } else {
            System.out.println("Unknown sensor type");
        }
    }
}
