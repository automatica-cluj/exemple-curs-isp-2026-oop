package utcluj.aut.engine.d1;

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

class Engine{
    ArrayList<ECU> list = new ArrayList<>();

    public void addECU(ECU ecu){
        list.add(ecu);
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
        return true;
    }
}

class EngineFactory{
    private TestBank bank;

    public EngineFactory(TestBank bank) {
        this.bank = bank;
    }

    public Engine createEngine(){
        ECU ecu = new ECU();
        Engine e = new Engine();
        e.addECU(ecu);
        bank.setEngine(e);
        boolean result = bank.testEngine();
        bank.setEngine(null);
        return result?e:null;
    }

}

public class CarDemo {
    public static void main(String[] args) {
        EngineFactory ef = new EngineFactory(new TestBank());
        Engine e1 = ef.createEngine();

    }
}
