package utcluj.aut.solid.dip;

public class CloudStorage implements DataStorage {
    public void save(String data) {
        System.out.println("Saving to cloud: " + data);
    }

    public String load() {
        return "Data from cloud";
    }
}
