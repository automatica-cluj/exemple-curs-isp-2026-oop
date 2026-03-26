package utcluj.aut.solid.lsp.ex2;

// Liskov Substitution Principle (LSP) - Detailed Example
// "Objects of a superclass should be replaceable with objects of its subclasses
// without breaking the application"

import java.util.*;

// ===== VIOLATION OF LSP =====
// First, let's see what happens when we violate LSP

// Base class for industrial valves
abstract class ValveBad {
    protected boolean isOpen = false;
    protected String valveId;

    public ValveBad(String valveId) {
        this.valveId = valveId;
    }

    // This method assumes all valves can be partially opened
    public abstract void setOpeningPercentage(int percentage);

    public void open() {
        isOpen = true;
        System.out.println(valveId + " opened");
    }

    public void close() {
        isOpen = false;
        System.out.println(valveId + " closed");
    }

    public boolean isOpen() {
        return isOpen;
    }
}

// Proportional valve - can be partially opened
class ProportionalValveBad extends ValveBad {
    private int openingPercentage = 0;

    public ProportionalValveBad(String valveId) {
        super(valveId);
    }

    @Override
    public void setOpeningPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.openingPercentage = percentage;
        System.out.println(valveId + " set to " + percentage + "% open");
    }
}

// ON/OFF valve - can only be fully open or closed (LSP VIOLATION!)
class OnOffValveBad extends ValveBad {
    public OnOffValveBad(String valveId) {
        super(valveId);
    }

    @Override
    public void setOpeningPercentage(int percentage) {
        // This violates LSP - changing expected behavior
        throw new UnsupportedOperationException("ON/OFF valve cannot be partially opened!");
    }
}

// ===== CORRECT IMPLEMENTATION OF LSP =====
// Now let's implement it correctly

// Base interface for all valves
interface Valve {
    void open();
    void close();
    boolean isOpen();
    String getId();
}

// Interface for valves that support variable opening
interface VariableValve extends Valve {
    void setOpeningPercentage(int percentage);
    int getOpeningPercentage();
}

// Base implementation with common functionality
abstract class BaseValve implements Valve {
    protected boolean isOpen = false;
    protected String valveId;

    public BaseValve(String valveId) {
        this.valveId = valveId;
    }

    @Override
    public void open() {
        isOpen = true;
        System.out.println(valveId + " opened");
    }

    @Override
    public void close() {
        isOpen = false;
        System.out.println(valveId + " closed");
    }

    @Override
    public boolean isOpen() {
        return isOpen;
    }

    @Override
    public String getId() {
        return valveId;
    }
}

// Concrete implementation for ON/OFF valves
class OnOffValve extends BaseValve {
    public OnOffValve(String valveId) {
        super(valveId);
    }

    // Only has open/close functionality
}

// Concrete implementation for proportional valves
class ProportionalValve extends BaseValve implements VariableValve {
    private int openingPercentage = 0;

    public ProportionalValve(String valveId) {
        super(valveId);
    }

    @Override
    public void setOpeningPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.openingPercentage = percentage;
        if (percentage > 0) {
            isOpen = true;
        } else {
            isOpen = false;
        }
        System.out.println(valveId + " set to " + percentage + "% open");
    }

    @Override
    public int getOpeningPercentage() {
        return openingPercentage;
    }

    @Override
    public void open() {
        setOpeningPercentage(100);
    }

    @Override
    public void close() {
        setOpeningPercentage(0);
    }
}

// Emergency shutdown valve with additional safety features
class EmergencyShutdownValve extends OnOffValve {
    private boolean emergencyMode = false;

    public EmergencyShutdownValve(String valveId) {
        super(valveId);
    }

    public void triggerEmergencyShutdown() {
        emergencyMode = true;
        super.close();
        System.out.println(valveId + " EMERGENCY SHUTDOWN ACTIVATED!");
    }

    @Override
    public void open() {
        if (emergencyMode) {
            System.out.println(valveId + " blocked - emergency shutdown active");
            return;
        }
        super.open();
    }

    public void resetEmergency() {
        emergencyMode = false;
        System.out.println(valveId + " emergency mode reset");
    }
}

// System that uses valves - demonstrates LSP compliance
class FlowControlSystem {
    private List<Valve> valves = new ArrayList<>();
    private List<VariableValve> variableValves = new ArrayList<>();

    public void addValve(Valve valve) {
        valves.add(valve);
        if (valve instanceof VariableValve) {
            variableValves.add((VariableValve) valve);
        }
    }

    // Works with any valve type (LSP compliant)
    public void openAllValves() {
        System.out.println("\n--- Opening all valves ---");
        for (Valve valve : valves) {
            valve.open();
        }
    }

    public void closeAllValves() {
        System.out.println("\n--- Closing all valves ---");
        for (Valve valve : valves) {
            valve.close();
        }
    }

    // Only works with variable valves
    public void setAllVariableValvesTo(int percentage) {
        System.out.println("\n--- Setting variable valves to " + percentage + "% ---");
        for (VariableValve valve : variableValves) {
            valve.setOpeningPercentage(percentage);
        }
    }

    public void performEmergencyShutdown() {
        System.out.println("\n--- EMERGENCY SHUTDOWN ---");
        for (Valve valve : valves) {
            valve.close();
            if (valve instanceof EmergencyShutdownValve) {
                ((EmergencyShutdownValve) valve).triggerEmergencyShutdown();
            }
        }
    }
}

// Demonstration of LSP violations and correct usage
public class LSPDetailedExample {
    public static void main(String[] args) {
        System.out.println("=== LSP VIOLATION EXAMPLE ===");
        demonstrateLSPViolation();

       // System.out.println("\n\n=== LSP CORRECT IMPLEMENTATION ===");
       // demonstrateCorrectLSP();
    }

    private static void demonstrateLSPViolation() {
        List<ValveBad> valves = new ArrayList<>();
        valves.add(new ProportionalValveBad("PROP-001"));
        valves.add(new OnOffValveBad("ONOFF-001"));

        // This code will fail for ON/OFF valve - violates LSP
        for (ValveBad valve : valves) {
            try {
                valve.setOpeningPercentage(50); // Throws exception for OnOffValveBad!
            } catch (UnsupportedOperationException e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    private static void demonstrateCorrectLSP() {
        FlowControlSystem system = new FlowControlSystem();

        // Add different valve types
        OnOffValve onOffValve = new OnOffValve("ONOFF-001");
        ProportionalValve propValve1 = new ProportionalValve("PROP-001");
        ProportionalValve propValve2 = new ProportionalValve("PROP-002");
        EmergencyShutdownValve emergencyValve = new EmergencyShutdownValve("EMRG-001");

        system.addValve(onOffValve);
        system.addValve(propValve1);
        system.addValve(propValve2);
        system.addValve(emergencyValve);

        // All valves work with basic operations
        system.openAllValves();
        system.closeAllValves();

        // Only variable valves respond to percentage setting
        system.setAllVariableValvesTo(75);

        // Test emergency shutdown
        system.performEmergencyShutdown();

        // Try to open emergency valve while in emergency mode
        System.out.println("\n--- Trying to open emergency valve ---");
        emergencyValve.open(); // Blocked due to emergency mode

        // Reset emergency and try again
        emergencyValve.resetEmergency();
        emergencyValve.open(); // Now it works

        // Demonstrate substitutability
        System.out.println("\n--- Demonstrating LSP Substitutability ---");
        testValveOperations(onOffValve);
        testValveOperations(propValve1);
        testValveOperations(emergencyValve);
    }

    // This method works with any valve type - LSP in action
    private static void testValveOperations(Valve valve) {
        System.out.println("\nTesting valve: " + valve.getId());
        valve.open();
        System.out.println("Is open? " + valve.isOpen());
        valve.close();
        System.out.println("Is open? " + valve.isOpen());
    }
}
//
//// Additional example: Industrial pump hierarchy
//abstract class Pump {
//    protected String pumpId;
//    protected boolean isRunning = false;
//
//    public Pump(String pumpId) {
//        this.pumpId = pumpId;
//    }
//
//    public void start() {
//        isRunning = true;
//        System.out.println(pumpId + " started");
//    }
//
//    public void stop() {
//        isRunning = false;
//        System.out.println(pumpId + " stopped");
//    }
//
//    public boolean isRunning() {
//        return isRunning;
//    }
//
//    // This method maintains the contract for all subclasses
//    public abstract double getFlowRate();
//}
//
//// Centrifugal pump implementation
//class CentrifugalPump extends Pump {
//    private double flowRate = 100.0; // liters/minute
//
//    public CentrifugalPump(String pumpId) {
//        super(pumpId);
//    }
//
//    @Override
//    public double getFlowRate() {
//        return isRunning ? flowRate : 0.0;
//    }
//
//    public void setFlowRate(double rate) {
//        this.flowRate = rate;
//        System.out.println(pumpId + " flow rate set to " + rate + " L/min");
//    }
//}
//
//// Positive displacement pump implementation
//class PositiveDisplacementPump extends Pump {
//    private double strokeVolume = 0.5; // liters per stroke
//    private double strokesPerMinute = 60;
//
//    public PositiveDisplacementPump(String pumpId) {
//        super(pumpId);
//    }
//
//    @Override
//    public double getFlowRate() {
//        return isRunning ? (strokeVolume * strokesPerMinute) : 0.0;
//    }
//
//    public void setStrokesPerMinute(double spm) {
//        this.strokesPerMinute = spm;
//        System.out.println(pumpId + " set to " + spm + " strokes/min");
//    }
//}