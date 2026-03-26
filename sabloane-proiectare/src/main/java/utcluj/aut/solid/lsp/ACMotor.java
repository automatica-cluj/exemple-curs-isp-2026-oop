package utcluj.aut.solid.lsp;

public class ACMotor extends Motor {
    public void setSpeed(int rpm) {
        if (rpm > 0 && rpm <= 3600) {
            System.out.println("AC Motor speed set to: " + rpm + " RPM");
        }
    }
}
