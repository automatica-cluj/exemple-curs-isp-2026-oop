package utcluj.aut.solid.lsp;

public class DCMotor extends Motor {
    public void setSpeed(int rpm) {
        if (rpm > 0 && rpm <= 5000) {
            System.out.println("DC Motor speed set to: " + rpm + " RPM");
        }
    }
}
