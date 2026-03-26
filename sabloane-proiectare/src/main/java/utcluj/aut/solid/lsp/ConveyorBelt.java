package utcluj.aut.solid.lsp;

public class ConveyorBelt {
    private Motor motor;

    public ConveyorBelt(Motor motor) {
        this.motor = motor;
    }

    public void runAtSpeed(int speed) {
        motor.start();
        motor.setSpeed(speed);
    }
}
