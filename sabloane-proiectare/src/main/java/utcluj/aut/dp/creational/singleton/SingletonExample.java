package utcluj.aut.dp.creational.singleton;

public class SingletonExample {
    private static SingletonExample instance;
    private String data;

    // Private constructor to prevent instantiation
    private SingletonExample() {
        data = "Initialized";
    }

    // Public static method to get the instance
    public static SingletonExample getInstance() {
        if (instance == null) {
            instance = new SingletonExample();
        }
        return instance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
} 