package utcluj.aut.dp.creational.singleton;

public class SerialPortDemo {
    public static void main(String[] args) {
        // Get the serial port manager instance
        SerialPortManager port1 = SerialPortManager.getInstance();
        
        // Configure the port
        System.out.println("Configuring serial port...");
        port1.configurePort("COM3", 115200);
        
        // Open the port
        System.out.println("\nOpening the port...");
        port1.openPort();
        
        // Simulate different parts of the application using the port
        System.out.println("\nSimulating different parts of the application:");
        
        // Part 1: Sensor Reader
        System.out.println("\nPart 1: Sensor Reader");
        SerialPortManager sensorPort = SerialPortManager.getInstance();
        System.out.println("Sensor Reader Status: " + sensorPort.getPortStatus());
        sensorPort.writeData("READ_SENSOR");
        String sensorData = sensorPort.readData();
        System.out.println("Sensor Data: " + sensorData);
        
        // Part 2: Data Logger
        System.out.println("\nPart 2: Data Logger");
        SerialPortManager loggerPort = SerialPortManager.getInstance();
        System.out.println("Data Logger Status: " + loggerPort.getPortStatus());
        loggerPort.writeData("LOG_DATA");
        String logData = loggerPort.readData();
        System.out.println("Log Data: " + logData);
        
        // Part 3: Command Controller
        System.out.println("\nPart 3: Command Controller");
        SerialPortManager controllerPort = SerialPortManager.getInstance();
        System.out.println("Command Controller Status: " + controllerPort.getPortStatus());
        controllerPort.writeData("SEND_COMMAND");
        String commandData = controllerPort.readData();
        System.out.println("Command Data: " + commandData);
        
        // Verify all references point to the same instance
        System.out.println("\nVerifying singleton behavior:");
        System.out.println("Are all references the same instance? " + 
            (port1 == sensorPort && sensorPort == loggerPort && loggerPort == controllerPort));
        
        // Close the port
        System.out.println("\nClosing the port...");
        port1.closePort();
        
        // Try to use the port after closing
        System.out.println("\nTrying to use closed port:");
        port1.writeData("TEST_DATA");
    }
} 