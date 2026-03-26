package utcluj.aut.solid.isp;

public class CNCMachine implements BasicOperations, SpeedControl, Maintenance, Reporting {
    public void start() {
        System.out.println("CNC Machine started");
    }

    public void stop() {
        System.out.println("CNC Machine stopped");
    }

    public void adjustSpeed(int speed) {
        System.out.println("CNC speed adjusted to: " + speed);
    }

    public void checkOilLevel() {
        System.out.println("Oil level checked");
    }

    public void performMaintenance() {
        System.out.println("Maintenance performed");
    }

    public void generateReport() {
        System.out.println("Report generated");
    }
}
