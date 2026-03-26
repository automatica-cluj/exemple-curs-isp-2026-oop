package utcluj.aut.solid.lsp;

public abstract class Motor {
    protected boolean isRunning = false;

    public void start() {
        isRunning = true;
        System.out.println("Motor started");
    }

    public void stop() {
        isRunning = false;
        System.out.println("Motor stopped");
    }

    public abstract void setSpeed(int rpm);
}
