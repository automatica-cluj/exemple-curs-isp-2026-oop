package democurs;

//b
class Car{
    private Engine engine;

    public void startEngine(){
        engine.start();
    }

    public Engine getEngine(){
        return engine;
    }
}

//c
class Engine{
    public void start(){
        System.out.println("Engine started");
    }
}

//a
public class Main {
    public static void main(String[] args) {
        Car car = new Car();
        car.startEngine();
        car.getEngine().start();
    }
}
