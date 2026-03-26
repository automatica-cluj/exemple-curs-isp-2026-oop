package utcluj.aut.dp.creational.singleton;

public class SerialPortManager {
    private static SerialPortManager instance;
    private boolean isOpen;
    private String portName;
    private int baudRate;
    private StringBuilder buffer;

    private SerialPortManager() {
        this.isOpen = false;
        this.portName = "COM1";  // Default port
        this.baudRate = 9600;    // Default baud rate
        this.buffer = new StringBuilder();
    }

    public static SerialPortManager getInstance() {
        if (instance == null) {
            instance = new SerialPortManager();
        }
        return instance;
    }

    public void openPort() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Opening serial port " + portName + " at " + baudRate + " baud");
        } else {
            System.out.println("Port is already open");
        }
    }

    public void closePort() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Closing serial port " + portName);
        } else {
            System.out.println("Port is already closed");
        }
    }

    public void configurePort(String portName, int baudRate) {
        if (!isOpen) {
            this.portName = portName;
            this.baudRate = baudRate;
            System.out.println("Port configured: " + portName + " at " + baudRate + " baud");
        } else {
            System.out.println("Cannot configure port while it's open");
        }
    }

    public void writeData(String data) {
        if (isOpen) {
            System.out.println("Writing to port: " + data);
            buffer.append(data);
        } else {
            System.out.println("Cannot write to closed port");
        }
    }

    public String readData() {
        if (isOpen) {
            String data = buffer.toString();
            buffer.setLength(0);  // Clear the buffer
            System.out.println("Reading from port: " + data);
            return data;
        } else {
            System.out.println("Cannot read from closed port");
            return null;
        }
    }

    public String getPortStatus() {
        return String.format("Port: %s, Baud Rate: %d, Status: %s", 
            portName, baudRate, isOpen ? "Open" : "Closed");
    }
} 