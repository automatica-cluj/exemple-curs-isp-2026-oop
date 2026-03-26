package utcluj.aut.dp.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

class Observable {
    private List<Observer> observers = new ArrayList<>();

    public void changeState(Object event) {
        notifyAllObservers(event);
    }

    public void register(Observer observer) {
        observers.add(observer);
    }

    private void notifyAllObservers(Object event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}

interface Observer {
    void update(Object event);
}

class Alarm extends Observable {
    void startAlarm() {
        System.out.println("Alarm has been started!");
        this.changeState("START");
    }

    void stopAlarm() {
        System.out.print("Alarm has been stopped!");
        this.changeState("STOP");
    }
}

class AlarmMonitor implements Observer {
    @Override
    public void update(Object event) {
        System.out.println("Alarm status has changed!");
        System.out.println("Received event: Event class:" + event.getClass() + ":" + event.toString());
    }
}

public class Client {
    public static void main(String[] args) throws InterruptedException {
        Alarm fireAlarm = new Alarm();
        AlarmMonitor monitor = new AlarmMonitor();
        fireAlarm.register(monitor);

        fireAlarm.startAlarm();
        Thread.sleep(500);
        fireAlarm.stopAlarm();
    }
}
