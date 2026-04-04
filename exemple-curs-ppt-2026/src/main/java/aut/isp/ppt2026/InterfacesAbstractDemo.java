package aut.isp.ppt2026;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InterfacesAbstractDemo {
    public static void main(String[] args) {
        System.out.println("=== ENUMS Demo ===\n");
        demoEnums();

        System.out.println("\n=== ABSTRACT CLASSES Demo ===\n");
        demoAbstractClasses();

        System.out.println("\n=== INTERFACES Demo ===\n");
        demoInterfaces();

        System.out.println("\n=== FUNCTIONAL INTERFACES & LAMBDAS Demo ===\n");
        demoFunctionalInterfaces();

        System.out.println("\n=== DEFAULT & STATIC METHODS in Interfaces ===\n");
        demoDefaultStaticMethods();

        System.out.println("\n=== INNER CLASSES Demo ===\n");
        demoInnerClasses();
    }

    // ==================== 1. ENUMS ====================
    // Simple enum
    enum ZiSaptamana {
        LUNI, MARTI, MIERCURI, JOI, VINERI, SAMBATA, DUMINICA
    }

    // Enum with fields and constructor
    enum Prioritate {
        MICA(1, "Nu e urgent"),
        MEDIE(2, "Normal"),
        MARE(3, "Very important"),
        CRITICA(4, "Do now!");

        private final int nivel;
        private final String descriere;

        Prioritate(int nivel, String descriere) {
            this.nivel = nivel;
            this.descriere = descriere;
        }

        public int getNivel() { return nivel; }
        public String getDescriere() { return descriere; }
    }

    static void demoEnums() {
        // Using simple enum
        ZiSaptamana zi = ZiSaptamana.VINERI;
        System.out.println("Ziua selectata: " + zi.name());
        System.out.println("Ordinal (index): " + zi.ordinal());

        // valueOf() - convert String to enum
        ZiSaptamana zi2 = ZiSaptamana.valueOf("LUNI");
        System.out.println("valueOf('LUNI'): " + zi2);

        // values() - all enum constants
        System.out.println("Toate zilele din enumerare: ");
        for (ZiSaptamana z : ZiSaptamana.values()) {
            System.out.println("  - " + z.name() + " (index " + z.ordinal() + ")");
        }

        // Switch with enum
        System.out.println("\nSwitch cu enum:");
        switch (zi) {
            case LUNI, MARTI, MIERCURI, JOI, VINERI:
                System.out.println("Zi de lucru!");
                break;
            case SAMBATA, DUMINICA:
                System.out.println("Weekend!");
                break;
        }

        // Enum with fields
        System.out.println("\nEnum cu campuri:");
        for (Prioritate p : Prioritate.values()) {
            System.out.println("  " + p.name() + ": nivel=" + p.getNivel()
                             + ", descriere='" + p.getDescriere() + "'");
        }
    }

    // ==================== 2. ABSTRACT CLASSES ====================
    // Cannot instantiate abstract class directly
    abstract class FormaGeometrica {
        protected String nume;

        public FormaGeometrica(String nume) {
            this.nume = nume;
        }

        // Abstract method - must be implemented by subclasses
        public abstract double aria();

        // Concrete method
        public void afisareDetalii() {
            System.out.println("Forma: " + nume + ", Aria: " + aria());
        }
    }

    // Subclass implementing abstract method
    class Cerc extends FormaGeometrica {
        private double raza;

        public Cerc(double raza) {
            super("Cerc");
            this.raza = raza;
        }

        @Override
        public double aria() {
            return Math.PI * raza * raza;
        }
    }

    // Another subclass
    class Dreptunghi extends FormaGeometrica {
        private double lungime;
        private double latime;

        public Dreptunghi(double lungime, double latime) {
            super("Dreptunghi");
            this.lungime = lungime;
            this.latime = latime;
        }

        @Override
        public double aria() {
            return lungime * latime;
        }
    }

    void demoAbstractClasses() {
        // FormaGeometrica f = new FormaGeometrica("Test"); // ERROR: cannot instantiate

        // Use subclasses instead
        FormaGeometrica cerc = new Cerc(5.0);
        cerc.afisareDetalii(); // Output: Forma: Cerc, Aria: 78.53...

        FormaGeometrica drept = new Dreptunghi(4.0, 6.0);
        drept.afisareDetalii(); // Output: Forma: Dreptunghi, Aria: 24.0

        // Polymorphism - store different shapes in same reference type
        List<FormaGeometrica> forme = new ArrayList<>();
        forme.add(cerc);
        forme.add(drept);
        forme.add(new Cerc(3.0));

        System.out.println("Total aria: " + forme.stream()
                .mapToDouble(FormaGeometrica::aria)
                .sum());
    }

    // ==================== 3. INTERFACES ====================
    interface Platibil {
        double calculeazaSuma();
    }

    interface Imprimabil {
        void tiparest();
    }

    // Class implementing multiple interfaces
    class Factura implements Platibil, Imprimabil {
        private String id;
        private double suma;
        private LocalDate data;

        public Factura(String id, double suma, LocalDate data) {
            this.id = id;
            this.suma = suma;
            this.data = data;
        }

        @Override
        public double calculeazaSuma() {
            return suma;
        }

        @Override
        public void tiparest() {
            System.out.println("Tiparire Factura #" + id + ": " + suma + " RON (data: " + data + ")");
        }
    }

    void demoInterfaces() {
        Factura f = new Factura("INV-001", 499.99, LocalDate.now());

        System.out.println("Factura suma: " + f.calculeazaSuma() + " RON");
        f.tiparest();

        // Store in interface reference types (polymorphism)
        List<Platibil> documente = new ArrayList<>();
        documente.add(f);

        System.out.println("\nTotal de platit: " +
            documente.stream().mapToDouble(Platibil::calculeazaSuma).sum() + " RON");
    }

    // ==================== 4. FUNCTIONAL INTERFACES & LAMBDAS ====================
    @FunctionalInterface
    interface Operatie {
        int executa(int a, int b);
    }

    @FunctionalInterface
    interface Predicat {
        boolean testeaza(Prioritate p);
    }

    void demoFunctionalInterfaces() {
        // Lambda expressions - functional interface implementation
        Operatie adunare = (a, b) -> a + b;
        Operatie scadere = (a, b) -> a - b;
        Operatie inmultire = (a, b) -> a * b;

        System.out.println("5 + 3 = " + adunare.executa(5, 3));
        System.out.println("5 - 3 = " + scadere.executa(5, 3));
        System.out.println("5 * 3 = " + inmultire.executa(5, 3));

        // Functional interface with predicate
        Predicat esteUrgent = p -> p.getNivel() >= 3;

        System.out.println("\nPrioritati urgente:");
        for (Prioritate p : Prioritate.values()) {
            if (esteUrgent.testeaza(p)) {
                System.out.println("  - " + p.name());
            }
        }
    }

    // ==================== 5. DEFAULT & STATIC METHODS IN INTERFACES ====================
    interface RepositorObiecte {
        void salveaza(Object obj);

        // Default method - has implementation, but can be overridden
        default void afiseaza() {
            System.out.println("Afisare standard: obiect salvat");
        }

        // Static method - belongs to interface, not to implementing class
        static String getInfo() {
            return "Interfata RepositorObiecte v1.0";
        }
    }

    class RepositorFacturi implements RepositorObiecte {
        @Override
        public void salveaza(Object obj) {
            System.out.println("Factura salvata in baza de date");
        }

        @Override
        public void afiseaza() {
            System.out.println("Afisare facturi: lista cu toate facturile");
        }
    }

    void demoDefaultStaticMethods() {
        RepositorFacturi repo = new RepositorFacturi();
        repo.salveaza(new Factura("INV-002", 199.99, LocalDate.now()));
        repo.afiseaza();

        // Static method called on interface
        System.out.println(RepositorObiecte.getInfo());
    }

    // ==================== 6. INNER CLASSES ====================
    // Static inner class
    static class Utilizator {
        String nume;
        String email;

        public Utilizator(String nume, String email) {
            this.nume = nume;
            this.email = email;
        }

        @Override
        public String toString() {
            return nume + " <" + email + ">";
        }
    }

    // Instance inner class (non-static)
    class Raport {
        private String titlu;

        public Raport(String titlu) {
            this.titlu = titlu;
        }

        // Inner class can access outer class members
        public void genereaza() {
            System.out.println("Raport: " + titlu);
            System.out.println("Data: " + LocalDate.now());
        }
    }

    // Anonymous class example (brief - nowadays replaced by lambdas)
    void demoInnerClasses() {
        // Static inner class
        Utilizator user = new Utilizator("Ion Pop", "ion@example.com");
        System.out.println("Utilizator: " + user);

        // Instance inner class
        Raport raport = new Raport("Vanzari Q1 2026");
        raport.genereaza();

        // Anonymous class (old style - for reference)
        Platibil platibilAnon = new Platibil() {
            @Override
            public double calculeazaSuma() {
                return 1500.00;
            }
        };
        System.out.println("\nSuma anonim: " + platibilAnon.calculeazaSuma() + " RON");

        // Modern approach: use lambda instead
        Platibil platibilLambda = () -> 2000.00;
        System.out.println("Suma lambda: " + platibilLambda.calculeazaSuma() + " RON");
    }

    // ==================== 7. ABSTRACT CLASS vs INTERFACE ====================
    /*
     * ABSTRACT CLASS:
     * - Can have state (non-final fields)
     * - Constructor with logic
     * - Access modifiers (protected, private)
     * - IS-A relationship (inheritance)
     * - Uneaza concepte relacionate (ex: FormaGeometrica)
     *
     * INTERFACE:
     * - Cannot have state (only constants)
     * - No constructor
     * - Everything is public
     * - CAN-DO relationship (contract)
     * - Define capabilities (ex: Platibil - "can be paid")
     *
     * Use abstract class when: classes share common code or state
     * Use interface when: you need to define a contract/capability
     */
}
