package utcluj.aut.solid;

import utcluj.aut.solid.dip.CloudStorage;
import utcluj.aut.solid.dip.DatabaseStorage;
import utcluj.aut.solid.dip.PLCController;
import utcluj.aut.solid.isp.CNCMachine;
import utcluj.aut.solid.isp.WaterPump;
import utcluj.aut.solid.lsp.ACMotor;
import utcluj.aut.solid.lsp.ConveyorBelt;
import utcluj.aut.solid.lsp.DCMotor;
import utcluj.aut.solid.ocp.HumiditySensor;
import utcluj.aut.solid.ocp.PressureSensor;
import utcluj.aut.solid.ocp.SensorReader;
import utcluj.aut.solid.ocp.TemperatureSensorOCP;
import utcluj.aut.solid.srp.AlertSystem;
import utcluj.aut.solid.srp.DataLogger;
import utcluj.aut.solid.srp.TemperatureSensor;

public class IndustrialAutomationDemo {
    public static void main(String[] args) {
        // SRP Example
        System.out.println("=== Single Responsibility Principle ===");
        TemperatureSensor tempSensor = new TemperatureSensor();
        DataLogger logger = new DataLogger();
        AlertSystem alertSystem = new AlertSystem();

        double temp = tempSensor.readTemperature();
        logger.saveToDatabase(temp);
        alertSystem.sendTemperatureAlert(temp);

        // OCP Example
        System.out.println("\n=== Open/Closed Principle ===");
        SensorReader reader = new SensorReader();
        reader.displayReading(new TemperatureSensorOCP());
        reader.displayReading(new PressureSensor());
        reader.displayReading(new HumiditySensor());

        // LSP Example
        System.out.println("\n=== Liskov Substitution Principle ===");
        ConveyorBelt belt1 = new ConveyorBelt(new ACMotor());
        ConveyorBelt belt2 = new ConveyorBelt(new DCMotor());
        belt1.runAtSpeed(1500);
        belt2.runAtSpeed(2000);

        // ISP Example
        System.out.println("\n=== Interface Segregation Principle ===");
        WaterPump pump = new WaterPump();
        pump.start();
        pump.stop();

        CNCMachine cnc = new CNCMachine();
        cnc.start();
        cnc.adjustSpeed(500);
        cnc.generateReport();

        // DIP Example
        System.out.println("\n=== Dependency Inversion Principle ===");
        PLCController plcWithDB = new PLCController(new DatabaseStorage());
        PLCController plcWithCloud = new PLCController(new CloudStorage());

        plcWithDB.saveProcessData("Temperature: 75°C");
        plcWithCloud.saveProcessData("Pressure: 2.5 bar");
    }
}
