package utcluj.aut.dp.behavioral.observer;

import java.util.Observable;
import java.util.Observer;

class ObservedObject extends Observable {
    private String watchedValue;

    public ObservedObject(String value) {
        watchedValue = value;
    }

    public void setValue(String value) {
        if (!watchedValue.equals(value)) {
            System.out.println("Value changed to new value: " + value);
            watchedValue = value;
            setChanged();
            notifyObservers(value);
        }
    }
}

public class ObservableDemo implements Observer {
    public static void main(String[] args) {
        ObservedObject watched = new ObservedObject("Original Value");
        ObservableDemo watcher = new ObservableDemo();

        watched.setValue("New Value");
        watched.addObserver(watcher);
        watched.setValue("Latest Value");
    }

    @Override
    public void update(Observable obj, Object arg) {
        System.out.println("Update called with Arguments: " + arg);
    }
}
