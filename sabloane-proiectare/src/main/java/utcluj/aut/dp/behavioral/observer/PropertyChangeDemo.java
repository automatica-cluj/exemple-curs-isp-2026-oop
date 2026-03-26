package utcluj.aut.dp.behavioral.observer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

class RobotCoordinates {
    private int x;
    private int y;
    private final PropertyChangeSupport support;

    public RobotCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public void setCoordinates(int newX, int newY) {
        int oldX = this.x;
        int oldY = this.y;
        this.x = newX;
        this.y = newY;
        support.firePropertyChange("x", oldX, newX);
        support.firePropertyChange("y", oldY, newY);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

class Robot extends Thread {
    private final RobotCoordinates coordinates;
    private boolean running = true;

    public Robot(RobotCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void run() {
        int x = coordinates.getX();
        int y = coordinates.getY();
        while (running) {
            try {
                Thread.sleep(1000); // update every second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
            x += (int)(Math.random() * 11) - 5; // random move -5..+5
            y += (int)(Math.random() * 11) - 5;
            coordinates.setCoordinates(x, y);
        }
    }

    public void stopRobot() {
        running = false;
    }
}

class RobotTracker {
    public void track(RobotCoordinates coordinates) {
        coordinates.addPropertyChangeListener(evt -> {
            if ("x".equals(evt.getPropertyName()) || "y".equals(evt.getPropertyName())) {
                System.out.println("Robot moved to: (" + coordinates.getX() + ", " + coordinates.getY() + ")");
            }
        });
    }
}

public class PropertyChangeDemo {
    public static void main(String[] args) throws InterruptedException {
        RobotCoordinates coordinates = new RobotCoordinates(0, 0);
        RobotTracker tracker = new RobotTracker();
        tracker.track(coordinates);
        Robot robot = new Robot(coordinates);
        robot.start();
        Thread.sleep(5000); // let the robot move for 5 seconds
        robot.stopRobot();
        robot.join();
    }
}
