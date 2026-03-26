package utcluj.aut.solid.dip;

public class PLCController {
    private DataStorage storage;

    public PLCController(DataStorage storage) {
        this.storage = storage;
    }

    public void saveProcessData(String data) {
        storage.save(data);
    }

    public String loadProcessData() {
        return storage.load();
    }
}
