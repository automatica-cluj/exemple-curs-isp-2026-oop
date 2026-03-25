package utcluj.aut.d2;

import java.util.ArrayList;

class RAM{
    private int capacity;

    public RAM(int capacity) {
        this.capacity = capacity;
    }
}//. ram

class CPU{
    private String model;

    public CPU(String model) {
        this.model = model;
    }
}//. cpu

class ECU{
    private  CPU cpu;
    private RAM ram;

    ECU(){
        cpu = new CPU("Intel");
        ram = new RAM(512);
    }
}

class CombustionEngine extends Engine{
    private String fuelType;

    public CombustionEngine(String fuelType) {
        this.fuelType = fuelType;
    }
}

class ElectricEngine extends Engine{
    private String voltage;

    public ElectricEngine(String voltage) {
        this.voltage = voltage;
    }
}

class Engine{
    ArrayList<ECU> list = new ArrayList<>();

    public void addECU(ECU ecu){
        list.add(ecu);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{ecuCount=" + list.size() + "}";
    }
}

class TestBank{
    private Engine engine;

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public boolean testEngine(){
        // Simulate occasional test bench failures.
        return Math.random() >= 0.2;
    }
}

class EngineFactory{
    private TestBank bank;

    public EngineFactory(TestBank bank) {
        this.bank = bank;
    }

    public Engine createEngine(){
        ECU ecu = new ECU();
        Engine e = Math.random() < 0.5
                ? new ElectricEngine("400V")
                : new CombustionEngine("Gasoline");
        e.addECU(ecu);
        bank.setEngine(e);
        boolean result = bank.testEngine();
        bank.setEngine(null);
        return result ? e : null;
    }

}

public class CarDemo {
    public static void main(String[] args) {
        EngineFactory ef = new EngineFactory(new TestBank());
        for (int i = 1; i <= 5; i++) {
            Engine engine = ef.createEngine();
            if (engine == null) {
                System.out.println("Engine " + i + ": test failed");
            } else {
                System.out.println("Engine " + i + ": " + engine);
            }
        }
    }
}
