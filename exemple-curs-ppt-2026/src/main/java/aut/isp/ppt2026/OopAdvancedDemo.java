package aut.isp.ppt2026;

import java.util.*;

/**
 * Minimal Demo: Agregarea, Compozitia si Mostenirea (Aggregation, Composition, Inheritance)
 *
 * Topics covered:
 * 1. Aggregation (weak has-a) vs Composition (strong has-a)
 * 2. Inheritance (is-a) hierarchy and method overriding
 * 3. Polymorphism (runtime dispatch)
 * 4. super keyword, final keyword, type casting, instanceof
 */
public class OopAdvancedDemo {
    public static void main(String[] args) {
        System.out.println("=== OOP Advanced: Agregarea, Compozitia, Mostenirea ===\n");

        // 1. COMPOSITION DEMO: aut.isp.ppt2026.Masina creates aut.isp.ppt2026.Motor internally (strong has-a)
        System.out.println("--- 1. COMPOSITION: aut.isp.ppt2026.Masina has-a aut.isp.ppt2026.Motor (strong) ---");
        Masina masina1 = new Masina("Tesla Model 3", 250);
        masina1.afiseazaDetalii();
        masina1.porneste();
        masina1.opreste();
        System.out.println();

        // 2. INHERITANCE DEMO: aut.isp.ppt2026.Angajat hierarchy
        System.out.println("--- 2. INHERITANCE: aut.isp.ppt2026.Angajat -> aut.isp.ppt2026.Manager -> aut.isp.ppt2026.Director ---");
        Angajat programator = new Programator("Ana", 3000);
        Angajat manager = new Manager("Bogdan", 5000, 10);
        Angajat director = new Director("Cristian", 8000, 20, "Romania");

        System.out.println(programator);
        System.out.println(manager);
        System.out.println(director);
        System.out.println();

        // 3. POLYMORPHISM DEMO: Array of aut.isp.ppt2026.Angajat with different types
        System.out.println("--- 3. POLYMORPHISM: Runtime method dispatch ---");
        Angajat[] angajati = { programator, manager, director };
        System.out.println("Calculare salarii (polimorf):");
        for (Angajat a : angajati) {
            System.out.println("  " + a.getNume() + ": " + a.calculeazaSalariu() + " RON");
        }
        System.out.println();

        // 4. DOWNCASTING DEMO: Extract specific type info
        System.out.println("--- 4. TYPE CASTING & instanceof ---");
        for (Angajat a : angajati) {
            // Pattern matching instanceof (Java 16+)
            if (a instanceof Manager m) {
                System.out.println("  aut.isp.ppt2026.Manager " + m.getNume() + " supervizeaza " +
                                 m.getSubordinati() + " persoane");
            } else if (a instanceof Programator p) {
                System.out.println("  aut.isp.ppt2026.Programator " + p.getNume());
            }
        }
        System.out.println();

        // 5. AGGREGATION DEMO: aut.isp.ppt2026.Departament has a List<aut.isp.ppt2026.Angajat> (weak has-a)
        System.out.println("--- 5. AGGREGATION: aut.isp.ppt2026.Departament has-a List<aut.isp.ppt2026.Angajat> (weak) ---");
        Departament depSoftware = new Departament("Software", "Tudor");
        depSoftware.adaugaAngajat(programator);
        depSoftware.adaugaAngajat(manager);
        depSoftware.afiseazaAngajati();
        System.out.println("  Budget total: " + depSoftware.calculeazaBudget() + " RON");
        System.out.println();

        // 6. final KEYWORD DEMO
        System.out.println("--- 6. final KEYWORD ---");
        System.out.println("  Clasa aut.isp.ppt2026.Programator este final (nu se poate extinde)");
        System.out.println("  Metoda aut.isp.ppt2026.Motor.getCapacitate() este final (nu se poate suprascrie)");
        System.out.println();

        // 7. AGGREGATION vs COMPOSITION difference
        System.out.println("--- 7. COMPOSITION vs AGGREGATION ---");
        System.out.println("  COMPOSITION (aut.isp.ppt2026.Motor in aut.isp.ppt2026.Masina):");
        System.out.println("    - aut.isp.ppt2026.Motor exista DOAR in aut.isp.ppt2026.Masina (ciclu de viata legat)");
        System.out.println("    - Creeat in constructor, distrus cu aut.isp.ppt2026.Masina");
        System.out.println();
        System.out.println("  AGGREGATION (aut.isp.ppt2026.Angajat in aut.isp.ppt2026.Departament):");
        System.out.println("    - Angajati exista independent (ciclu de viata separat)");
        System.out.println("    - Creati extern, trecuti in aut.isp.ppt2026.Departament");
        System.out.println("    - Pot fi scoși din departament fara sa fie distrusí");
        System.out.println();

        // 8. super KEYWORD DEMO
        System.out.println("--- 8. super KEYWORD ---");
        System.out.println("  aut.isp.ppt2026.Director apeleaza super.calculeazaSalariu() + bonusuri");
        System.out.println("  Fiecare clasa apeleaza super() in constructor");
        System.out.println();

        // 9. INHERITANCE OF ACCESS LEVELS
        System.out.println("--- 9. ACCESS MODIFIERS IN INHERITANCE ---");
        System.out.println("  public: mostenit de subclase si ceilalti");
        System.out.println("  protected: mostenit de subclase (din orice pachet)");
        System.out.println("  package-private (default): mostenit in acelasi pachet");
        System.out.println("  private: NU mostenit (nu se vede in subclase)");
        System.out.println();

        // 10. OVERLOADING vs OVERRIDING
        System.out.println("--- 10. OVERLOADING vs OVERRIDING ---");
        Vehicul auto = new Automobil("BMW", 2.0);
        auto.afiseaza();  // apeleaza aut.isp.ppt2026.Automobil.afiseaza(void)
        auto.afiseaza("Motors cu euro 6");  // apeleaza aut.isp.ppt2026.Vehicul.afiseaza(String) - inherited
        System.out.println();

        System.out.println("=== Demo completed ===");
    }
}

// ============================================================================
// COMPOSITION: aut.isp.ppt2026.Motor is part of aut.isp.ppt2026.Masina (cannot exist alone)
// ============================================================================
class Motor {
    private double capacitate;  // in litri
    private String tip;         // benzina, diesel, electric
    private boolean pornit;

    public Motor(double capacitate, String tip) {
        this.capacitate = capacitate;
        this.tip = tip;
        this.pornit = false;
    }

    public final double getCapacitate() {  // final: cannot be overridden
        return capacitate;
    }

    public String getTip() {
        return tip;
    }

    public void porneste() {
        if (!pornit) {
            pornit = true;
            System.out.println("  aut.isp.ppt2026.Motor " + tip + " (" + capacitate + "L) a pornit");
        }
    }

    public void opreste() {
        if (pornit) {
            pornit = false;
            System.out.println("  aut.isp.ppt2026.Motor s-a oprit");
        }
    }

    public boolean estePornit() {
        return pornit;
    }
}

// ============================================================================
// COMPOSITION: aut.isp.ppt2026.Masina creates aut.isp.ppt2026.Motor internally
// ============================================================================
class Masina {
    private String marca;
    private Motor motor;  // Internal composition: aut.isp.ppt2026.Motor created here

    public Masina(String marca, double capacitateMotor) {
        this.marca = marca;
        // Composition: aut.isp.ppt2026.Motor is created INSIDE aut.isp.ppt2026.Masina, not received externally
        this.motor = new Motor(capacitateMotor, "benzina");
    }

    public void afiseazaDetalii() {
        System.out.println("  aut.isp.ppt2026.Masina: " + marca);
        System.out.println("  aut.isp.ppt2026.Motor: " + motor.getTip() + " " + motor.getCapacitate() + "L");
    }

    public void porneste() {
        motor.porneste();
    }

    public void opreste() {
        motor.opreste();
    }
}

// ============================================================================
// INHERITANCE: Base class aut.isp.ppt2026.Vehicul
// ============================================================================
class Vehicul {
    protected String marca;  // protected: inherited by subclasses
    private int anFabricatie; // private: NOT inherited (not visible in subclasses)

    public Vehicul(String marca) {
        this.marca = marca;
        this.anFabricatie = 2024;
    }

    public String getMarca() {
        return marca;
    }

    public void afiseaza() {
        System.out.println("  aut.isp.ppt2026.Vehicul marca: " + marca);
    }

    // Overload: same method name, different signature
    public void afiseaza(String detaliu) {
        System.out.println("  aut.isp.ppt2026.Vehicul " + marca + " - " + detaliu);
    }
}

// ============================================================================
// INHERITANCE: aut.isp.ppt2026.Automobil extends aut.isp.ppt2026.Vehicul
// ============================================================================
class Automobil extends Vehicul {
    private double capacitateMotor;

    public Automobil(String marca, double capacitateMotor) {
        super(marca);  // super() must be first statement
        this.capacitateMotor = capacitateMotor;
    }

    @Override  // Override: runtime polymorphism
    public void afiseaza() {
        System.out.println("  aut.isp.ppt2026.Automobil marca: " + marca + " motor " + capacitateMotor + "L");
    }

    public double getCapacitateMotor() {
        return capacitateMotor;
    }
}

// ============================================================================
// INHERITANCE: aut.isp.ppt2026.Angajat (base class for employee hierarchy)
// ============================================================================
class Angajat {
    protected String nume;
    protected double salaruBase;

    public Angajat(String nume, double salaruBase) {
        this.nume = nume;
        this.salaruBase = salaruBase;
    }

    public String getNume() {
        return nume;
    }

    // Virtual method: will be overridden in subclasses
    public double calculeazaSalariu() {
        return salaruBase;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " {" + nume + ", " + salaruBase + " RON}";
    }
}

// ============================================================================
// INHERITANCE: aut.isp.ppt2026.Programator extends aut.isp.ppt2026.Angajat (final class: cannot be extended further)
// ============================================================================
final class Programator extends Angajat {
    public Programator(String nume, double salaruBase) {
        super(nume, salaruBase);
    }

    @Override
    public double calculeazaSalariu() {
        // aut.isp.ppt2026.Programator: base salary + 10% bonus
        return salaruBase * 1.10;
    }
}

// ============================================================================
// INHERITANCE: aut.isp.ppt2026.Manager extends aut.isp.ppt2026.Angajat
// ============================================================================
class Manager extends Angajat {
    protected int subordinati;

    public Manager(String nume, double salaruBase, int subordinati) {
        super(nume, salaruBase);
        this.subordinati = subordinati;
    }

    public int getSubordinati() {
        return subordinati;
    }

    @Override
    public double calculeazaSalariu() {
        // aut.isp.ppt2026.Manager: base + 20% + 5% per subordinate
        return salaruBase * 1.20 + (subordinati * salaruBase * 0.05);
    }
}

// ============================================================================
// INHERITANCE: aut.isp.ppt2026.Director extends aut.isp.ppt2026.Manager (multi-level inheritance)
// ============================================================================
class Director extends Manager {
    private String regiune;

    public Director(String nume, double salaruBase, int subordinati, String regiune) {
        super(nume, salaruBase, subordinati);  // super() calls aut.isp.ppt2026.Manager constructor
        this.regiune = regiune;
    }

    public String getRegiune() {
        return regiune;
    }

    @Override
    public double calculeazaSalariu() {
        // aut.isp.ppt2026.Director: uses super to call aut.isp.ppt2026.Manager logic, then adds region bonus
        double salarManagerial = super.calculeazaSalariu();
        double bonusRegional = 2000;  // fixed bonus for each region
        return salarManagerial + bonusRegional;
    }

    @Override
    public String toString() {
        return super.toString() + " {regiune: " + regiune + "}";
    }
}

// ============================================================================
// AGGREGATION: aut.isp.ppt2026.Departament has a List<aut.isp.ppt2026.Angajat> (weak has-a)
// ============================================================================
class Departament {
    private String nume;
    private String sef;
    private List<Angajat> angajati;  // Aggregation: list of externally-created objects

    public Departament(String nume, String sef) {
        this.nume = nume;
        this.sef = sef;
        this.angajati = new ArrayList<>();
    }

    // Agregation: receive objects created externally
    public void adaugaAngajat(Angajat angajat) {
        angajati.add(angajat);
    }

    public void stergeAngajat(Angajat angajat) {
        angajati.remove(angajat);
    }

    public void afiseazaAngajati() {
        System.out.println("  aut.isp.ppt2026.Departament: " + nume + " (Sef: " + sef + ")");
        for (Angajat a : angajati) {
            System.out.println("    - " + a.getNume() + " (" + a.getClass().getSimpleName() + ")");
        }
    }

    public double calculeazaBudget() {
        double total = 0;
        for (Angajat a : angajati) {
            total += a.calculeazaSalariu();
        }
        return total;
    }
}
