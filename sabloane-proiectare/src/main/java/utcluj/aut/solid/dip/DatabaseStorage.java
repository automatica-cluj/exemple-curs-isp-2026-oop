package utcluj.aut.solid.dip;

public class DatabaseStorage implements DataStorage {
    public void save(String data) {
        System.out.println("Saving to database: " + data);
    }

    public String load() {
        return "Data from database";
    }
}
