package utcluj.aut.dp.creational.singleton;

public class SerialDemo {
    public static void main(String[] args) {
        SerialPortManager manager1 = SerialPortManager.getInstance();
        SerialPortManager manager2 = SerialPortManager.getInstance();

        System.out.println("Manager 1: " + manager1);
        System.out.println("Manager 2: " + manager2);
        System.out.println("Are both instances the same? " + (manager1 == manager2));

        manager1.openPort();
        manager1.writeData("12345");
        manager2.writeData("67890");
        System.out.println("Data in manager1: " + manager1.readData());

    }
}
