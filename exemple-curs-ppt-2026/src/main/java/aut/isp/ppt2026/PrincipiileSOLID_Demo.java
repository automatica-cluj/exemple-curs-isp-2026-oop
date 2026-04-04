import java.util.ArrayList;
import java.util.List;

/**
 * Principiile SOLID - Demonstratie pentru Studenti
 *
 * SOLID sunt 5 principii fundamentale ale design-ului OOP:
 * S - Single Responsibility Principle
 * O - Open/Closed Principle
 * L - Liskov Substitution Principle
 * I - Interface Segregation Principle
 * D - Dependency Inversion Principle
 */

public class PrincipiileSOLID_Demo {
    public static void main(String[] args) {
        System.out.println("===== DEMONSTRATIE PRINCIPIILE SOLID =====\n");

        demonstreareSRP();
        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstreareOCP();
        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrareLSP();
        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrareISP();
        System.out.println("\n" + "=".repeat(50) + "\n");

        demonstrareDIP();
    }

    // ==================== S - SINGLE RESPONSIBILITY PRINCIPLE ====================
    // Fiecare clasa are o SINGURA RESPONSABILITATE - o singura ratie sa se schimbe
    //
    // BAD: O clasa Angajat care face totul - calculeaza salariu, genereaza rapoarte, salveaza in DB
    // GOOD: Impartim responsabilitatile in clase separate

    static class Angajat {
        private String nume;
        private double salariu;
        private String pozitie;

        public Angajat(String nume, double salariu, String pozitie) {
            this.nume = nume;
            this.salariu = salariu;
            this.pozitie = pozitie;
        }

        public String getNume() { return nume; }
        public double getSalariu() { return salariu; }
        public String getPozitie() { return pozitie; }
    }

    static class CalculatorSalariu {
        // Responsabilitate: calculeaza bonusuri, taxe
        public double calculeazaSalarNetRAPT(Angajat angajat) {
            double netto = angajat.getSalariu() * 0.82; // 18% taxe
            return netto;
        }

        public double calculeazaBonusRAPT(Angajat angajat) {
            return angajat.getSalariu() * 0.15; // Bonus 15%
        }
    }

    static class RaportGenerator {
        // Responsabilitate: genereaza rapoarte
        public void genereazaRaportRAPT(Angajat angajat, CalculatorSalariu calc) {
            System.out.println("--- RAPORT SALARIAL ---");
            System.out.println("Nume: " + angajat.getNume());
            System.out.println("Pozitie: " + angajat.getPozitie());
            System.out.println("Salariu brut: " + angajat.getSalariu());
            System.out.println("Salariu net: " + calc.calculeazaSalarNetRAPT(angajat));
            System.out.println("Bonus: " + calc.calculeazaBonusRAPT(angajat));
        }
    }

    static void demonstreareSRP() {
        System.out.println("1. SINGLE RESPONSIBILITY PRINCIPLE (SRP)");
        System.out.println("Fiecare clasa are O singura responsabilitate.\n");

        Angajat ion = new Angajat("Ion Popescu", 3000, "Software Developer");
        CalculatorSalariu calc = new CalculatorSalariu();
        RaportGenerator gen = new RaportGenerator();

        gen.genereazaRaportRAPT(ion, calc);
        System.out.println("\n✓ Clasa Angajat: doar date despre angajat");
        System.out.println("✓ Clasa CalculatorSalariu: doar logica de calcul");
        System.out.println("✓ Clasa RaportGenerator: doar generarea rapoartelor");
    }

    // ==================== O - OPEN/CLOSED PRINCIPLE ====================
    // Clasele sunt DESCHISE pentru extensie, INCHISE pentru modificare
    //
    // BAD: If-else chain cand adaugam forme geometrice noi
    // GOOD: Clasa abstracta FormaGeometrica, fiecare forma implementeaza aria()

    abstract static class FormaGeometrica {
        // Deschisa pentru extensie: orice clasa poate implementa
        public abstract double calculeazaAria();
    }

    static class Cerc extends FormaGeometrica {
        private double raza;

        public Cerc(double raza) {
            this.raza = raza;
        }

        @Override
        public double calculeazaAria() {
            return Math.PI * raza * raza;
        }
    }

    static class Dreptunghi extends FormaGeometrica {
        private double lungime;
        private double latime;

        public Dreptunghi(double lungime, double latime) {
            this.lungime = lungime;
            this.latime = latime;
        }

        @Override
        public double calculeazaAria() {
            return lungime * latime;
        }
    }

    static class Triunghi extends FormaGeometrica {
        private double baza;
        private double inaltime;

        public Triunghi(double baza, double inaltime) {
            this.baza = baza;
            this.inaltime = inaltime;
        }

        @Override
        public double calculeazaAria() {
            return (baza * inaltime) / 2;
        }
    }

    static void demonstreareOCP() {
        System.out.println("2. OPEN/CLOSED PRINCIPLE (OCP)");
        System.out.println("Deschisa pentru extensie, inchisa pentru modificare.\n");

        List<FormaGeometrica> forme = new ArrayList<>();
        forme.add(new Cerc(5));
        forme.add(new Dreptunghi(4, 6));
        forme.add(new Triunghi(8, 3));

        double ariaTotal = 0;
        for (FormaGeometrica forma : forme) {
            System.out.println("Aria: " + String.format("%.2f", forma.calculeazaAria()));
            ariaTotal += forma.calculeazaAria();
        }

        System.out.println("\nAria totala: " + String.format("%.2f", ariaTotal));
        System.out.println("\n✓ Deschisa pentru extensie: Am adaugat Triunghi fara sa modificam clasa de baza");
        System.out.println("✓ Inchisa pentru modificare: Nu am modificat Cerc sau Dreptunghi");
    }

    // ==================== L - LISKOV SUBSTITUTION PRINCIPLE ====================
    // Subclasele TREBUIE sa fie substituite cu siguranta pentru clasa parinte
    //
    // BAD: Patrat extends Dreptunghi, dar setWidth/setHeight nu functioneaza la fel
    // GOOD: Folosim interfata Shape - fiecare forma independenta

    interface Forma {
        double aria();
    }

    static class Patrat implements Forma {
        private double latura;

        public Patrat(double latura) {
            this.latura = latura;
        }

        public void setLatura(double latura) {
            this.latura = latura;
        }

        @Override
        public double aria() {
            return latura * latura;
        }
    }

    static class DreptunghiLSP implements Forma {
        private double lungime;
        private double latime;

        public DreptunghiLSP(double lungime, double latime) {
            this.lungime = lungime;
            this.latime = latime;
        }

        public void setLungime(double lungime) {
            this.lungime = lungime;
        }

        public void setLatime(double latime) {
            this.latime = latime;
        }

        @Override
        public double aria() {
            return lungime * latime;
        }
    }

    static void demonstrareLSP() {
        System.out.println("3. LISKOV SUBSTITUTION PRINCIPLE (LSP)");
        System.out.println("Subclasele pot inlocui clasa parinte fara probleme.\n");

        Patrat patrat = new Patrat(5);
        DreptunghiLSP dreptunghi = new DreptunghiLSP(4, 6);

        System.out.println("Aria patrat: " + patrat.aria());
        System.out.println("Aria dreptunghi: " + dreptunghi.aria());

        System.out.println("\n✓ Patrat si Dreptunghi sunt implementari independente");
        System.out.println("✓ Patrat nu trebuie sa extinda Dreptunghi");
        System.out.println("✓ Fiecare forma are propriul comportament consistent");
    }

    // ==================== I - INTERFACE SEGREGATION PRINCIPLE ====================
    // Clientii NU TREBUIE fortati sa depinda de metode pe care nu le folosesc
    //
    // BAD: IWorker mare cu work(), eat(), sleep() - dar Robot nu mananca/doarme
    // GOOD: Interfete mici si specifice: Workable, Feedable, Sleepable

    interface Workable {
        void lucreaza();
    }

    interface Feedable {
        void mananca();
    }

    interface Sleepable {
        void doarme();
    }

    static class Angajat_ISP implements Workable, Feedable, Sleepable {
        private String nume;

        public Angajat_ISP(String nume) {
            this.nume = nume;
        }

        @Override
        public void lucreaza() {
            System.out.println(nume + " lucreaza la proiect...");
        }

        @Override
        public void mananca() {
            System.out.println(nume + " mananca la restaurant...");
        }

        @Override
        public void doarme() {
            System.out.println(nume + " doarme 8 ore...");
        }
    }

    static class Robot_ISP implements Workable {
        private String model;

        public Robot_ISP(String model) {
            this.model = model;
        }

        @Override
        public void lucreaza() {
            System.out.println("Robot " + model + " lucreaza non-stop...");
        }
        // Robotul NU implementeaza Feedable si Sleepable - perfect!
    }

    static void demonstrareISP() {
        System.out.println("4. INTERFACE SEGREGATION PRINCIPLE (ISP)");
        System.out.println("Clientii nu sunt fortati sa depinda de metode nefolosite.\n");

        Angajat_ISP maria = new Angajat_ISP("Maria");
        Robot_ISP robot = new Robot_ISP("T-1000");

        System.out.println("--- Angajat ---");
        maria.lucreaza();
        maria.mananca();
        maria.doarme();

        System.out.println("\n--- Robot ---");
        robot.lucreaza();

        System.out.println("\n✓ Angajat implementeaza Workable, Feedable, Sleepable");
        System.out.println("✓ Robot implementeaza doar Workable");
        System.out.println("✓ Nu forteaza Robotul sa implementeze metode nefolosite");
    }

    // ==================== D - DEPENDENCY INVERSION PRINCIPLE ====================
    // Depinde de ABSTRACTII, nu de CONCRETII
    //
    // BAD: NotificareService creeaza direct EmailSender()
    // GOOD: NotificareService depinde de interfata Notificator, inject dependency

    interface Notificator {
        void trimiteNotificare(String mesaj);
    }

    static class EmailNotificator implements Notificator {
        @Override
        public void trimiteNotificare(String mesaj) {
            System.out.println("Email trimis: " + mesaj);
        }
    }

    static class SmsNotificator implements Notificator {
        @Override
        public void trimiteNotificare(String mesaj) {
            System.out.println("SMS trimis: " + mesaj);
        }
    }

    static class NotificareService {
        // Depinde de abstractie (Notificator), nu de concretie
        private Notificator notificator;

        public NotificareService(Notificator notificator) {
            this.notificator = notificator; // Dependency Injection
        }

        public void alerteazaClient(String mesaj) {
            notificator.trimiteNotificare(mesaj);
        }
    }

    static void demonstrareDIP() {
        System.out.println("5. DEPENDENCY INVERSION PRINCIPLE (DIP)");
        System.out.println("Depinde de abstractii, nu de implementari concrete.\n");

        // Injectam EmailNotificator
        NotificareService serviceEmail = new NotificareService(new EmailNotificator());
        serviceEmail.alerteazaClient("Bun venit la platforma!");

        // Injectam SmsNotificator - fara a modifica NotificareService
        NotificareService serviceSms = new NotificareService(new SmsNotificator());
        serviceSms.alerteazaClient("Cod de verificare: 12345");

        System.out.println("\n✓ NotificareService depinde de Notificator (abstractie)");
        System.out.println("✓ Putem schimba implementarea prin dependency injection");
        System.out.println("✓ Nu modificam NotificareService cand adaugam SmsNotificator");
    }
}
