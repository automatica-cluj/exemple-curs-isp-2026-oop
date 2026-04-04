/**
 * PrincipiileOOP_Demo.java
 *
 * Demonstrație pentru cei 4 piloni ai Programării Orientate pe Obiecte (OOP)
 * - Incapsulare (Encapsulation)
 * - Moștenire (Inheritance)
 * - Polimorfism (Polymorphism)
 * - Abstractizare (Abstraction)
 */

public class PrincipiileOOP_Demo {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRAȚIE: PRINCIPIILE OOP ===\n");

        // ============================================================
        // 1. INCAPSULARE (Encapsulation)
        // ============================================================
        System.out.println("1. INCAPSULARE");
        System.out.println("   - Date private cu getters/setters");
        System.out.println("   - Validare în setters");
        System.out.println("   - Ascunderea detaliilor interne\n");

        ContBancar cont = new ContBancar("Ion Popescu", 1000);
        System.out.println("Titular: " + cont.getTitular());
        System.out.println("Sold inițial: " + cont.getSold() + " RON");

        cont.depozita(500);
        System.out.println("După depunere de 500 RON: " + cont.getSold() + " RON");

        cont.retrage(200);
        System.out.println("După retragere de 200 RON: " + cont.getSold() + " RON");

        // Tentativă de retragere mai mult decât soldul
        System.out.println("\nTentativă retragere 2000 RON (mai mult decât soldul):");
        cont.retrage(2000);
        System.out.println("Soldul rămâne: " + cont.getSold() + " RON");

        // Tentativă de setare a soldului negativ
        System.out.println("\nTentativă setare vârstă negativă:");
        Persoana p = new Persoana("Ana", -5);
        System.out.println("Vârsta setată: " + p.getVarsta() + " ani\n");

        // ============================================================
        // 2. MOȘTENIRE (Inheritance)
        // ============================================================
        System.out.println("\n2. MOȘTENIRE");
        System.out.println("   - Reutilizare de cod prin extends");
        System.out.println("   - Ierarhie: Animal -> Caine, Pisica");
        System.out.println("   - super() pentru apelul constructorului părintelui\n");

        Caine caine = new Caine("Rex", 3);
        Pisica pisica = new Pisica("Miau", 2);

        System.out.println("Câine: " + caine.getNume() + ", vârstă: " + caine.getVarsta());
        System.out.println("Pisică: " + pisica.getNume() + ", vârstă: " + pisica.getVarsta());

        caine.mănâncă();
        pisica.mănâncă();

        // ============================================================
        // 3. POLIMORFISM (Polymorphism)
        // ============================================================
        System.out.println("\n3. POLIMORFISM");
        System.out.println("   a) Supraîncărcare (Compile-time): Calculator.aduna()");
        System.out.println("   b) Suprascriereun(Runtime): emiteSunet() diferit pe fiecare Animal\n");

        // 3a. Supraîncărcare metode (Method Overloading)
        Calculator calc = new Calculator();
        System.out.println("Supraîncărcare metode:");
        System.out.println("  aduna(5, 3) = " + calc.aduna(5, 3));
        System.out.println("  aduna(5.5, 2.5) = " + calc.aduna(5.5, 2.5));
        System.out.println("  aduna(1, 2, 3) = " + calc.aduna(1, 2, 3));

        // 3b. Suprascriere metode (Method Overriding) + Dispatch dinamic
        System.out.println("\nSuprascriere metode + Dispatch dinamic:");
        Animal[] animale = new Animal[]{
            new Caine("Pluto", 4),
            new Pisica("Garfield", 5),
            new Caine("Lassie", 6),
            new Pisica("Tom", 3)
        };

        for (Animal animal : animale) {
            System.out.println(animal.getNume() + " (" + animal.getClass().getSimpleName() + "): ");
            animal.emiteSunet();
            animal.mănâncă();
        }

        // 3c. Polimorfism prin interfețe (Interface Polymorphism)
        System.out.println("\nPolimorfism prin interfețe (Desenabil):");
        Desenabil[] desene = new Desenabil[]{
            new Cerc(5),
            new Patrat(4),
            new Cerc(3)
        };

        for (Desenabil desen : desene) {
            desen.deseneaza();
        }

        // ============================================================
        // 4. ABSTRACTIZARE (Abstraction)
        // ============================================================
        System.out.println("\n4. ABSTRACTIZARE");
        System.out.println("   - Clase abstracte ascund complexitate");
        System.out.println("   - Interfețe definesc contractul");
        System.out.println("   - Codul se ocupă de abstraxiune, nu implementări\n");

        FormaGeometrica[] forme = new FormaGeometrica[]{
            new Triunghi(3, 4, 5),
            new Dreptunghi(5, 7),
            new Cerc(4)
        };

        for (FormaGeometrica forma : forme) {
            System.out.println("Forma: " + forma.getClass().getSimpleName());
            System.out.println("  Aria: " + String.format("%.2f", forma.aria()));
            System.out.println("  Perimetrul: " + String.format("%.2f", forma.perimetru()));
        }

        // Interfață Plătibil
        System.out.println("\nSistem de plăți (interfață Platibil):");
        Platibil[] plati = new Platibil[]{
            new ContBancar("Maria", 5000),
            new Card("Visa", 3000)
        };

        double sumaTotal = 0;
        for (Platibil plata : plati) {
            double suma = plata.calculeazaSuma();
            System.out.println("  Sumă: " + suma + " RON");
            sumaTotal += suma;
        }
        System.out.println("Total: " + sumaTotal + " RON");

        System.out.println("\n=== SFÂRȘIT DEMONSTRAȚIE ===");
    }
}

// ============================================================
// 1. INCAPSULARE - Exemplu: ContBancar
// ============================================================

class ContBancar implements Platibil {
    private String titular;
    private double sold;

    public ContBancar(String titular, double sold) {
        this.titular = titular;
        setSold(sold); // Validare prin setter
    }

    public String getTitular() {
        return titular;
    }

    public double getSold() {
        return sold;
    }

    private void setSold(double sold) {
        if (sold >= 0) {
            this.sold = sold;
        } else {
            System.out.println("Eroare: Soldul nu poate fi negativ!");
            this.sold = 0;
        }
    }

    public void depozita(double suma) {
        if (suma > 0) {
            sold += suma;
            System.out.println("  ✓ Depunere de " + suma + " RON reușită");
        } else {
            System.out.println("  ✗ Suma de depunere trebuie să fie pozitivă!");
        }
    }

    public void retrage(double suma) {
        if (suma > 0 && suma <= sold) {
            sold -= suma;
            System.out.println("  ✓ Retragere de " + suma + " RON reușită");
        } else if (suma > sold) {
            System.out.println("  ✗ Fonduri insuficiente! Soldul disponibil: " + sold);
        } else {
            System.out.println("  ✗ Suma trebuie să fie pozitivă!");
        }
    }

    @Override
    public double calculeazaSuma() {
        return sold;
    }
}

// ============================================================
// 1. INCAPSULARE - Exemplu: Persoana cu validare
// ============================================================

class Persoana {
    private String nume;
    private int varsta;

    public Persoana(String nume, int varsta) {
        this.nume = nume;
        setVarsta(varsta); // Validare prin setter
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        if (varsta >= 0 && varsta <= 150) {
            this.varsta = varsta;
        } else {
            System.out.println("  ✗ Vârsta trebuie să fie între 0 și 150!");
            this.varsta = 0;
        }
    }
}

// ============================================================
// 2. MOȘTENIRE - Clasa de bază: Animal
// ============================================================

class Animal {
    private String nume;
    private int varsta;

    public Animal(String nume, int varsta) {
        this.nume = nume;
        this.varsta = varsta;
    }

    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void mănâncă() {
        System.out.println("  🍽️  " + nume + " mănâncă...");
    }

    // Metodă care va fi suprascrisă de subclase
    public void emiteSunet() {
        System.out.println("  🔊 " + nume + " emite un sunet...");
    }
}

// ============================================================
// 2. MOȘTENIRE - Subclasă: Caine
// ============================================================

class Caine extends Animal {
    public Caine(String nume, int varsta) {
        super(nume, varsta); // Apel constructor de bază
    }

    @Override
    public void emiteSunet() {
        System.out.println("  🔊 " + getNume() + " latră: Hău, hău!");
    }
}

// ============================================================
// 2. MOȘTENIRE - Subclasă: Pisica
// ============================================================

class Pisica extends Animal {
    public Pisica(String nume, int varsta) {
        super(nume, varsta);
    }

    @Override
    public void emiteSunet() {
        System.out.println("  🔊 " + getNume() + " miaune: Miau, miau!");
    }
}

// ============================================================
// 3. POLIMORFISM - Supraîncărcare: Calculator
// ============================================================

class Calculator {
    // Adună 2 numere întregi
    public int aduna(int a, int b) {
        return a + b;
    }

    // Adună 2 numere reale
    public double aduna(double a, double b) {
        return a + b;
    }

    // Adună 3 numere întregi
    public int aduna(int a, int b, int c) {
        return a + b + c;
    }
}

// ============================================================
// 3. POLIMORFISM - Interfață: Desenabil
// ============================================================

interface Desenabil {
    void deseneaza();
}

// ============================================================
// 3. POLIMORFISM - Implementare: Cerc
// ============================================================

class Cerc implements Desenabil, FormaGeometrica {
    private double raza;

    public Cerc(double raza) {
        this.raza = raza;
    }

    public double getRaza() {
        return raza;
    }

    @Override
    public void deseneaza() {
        System.out.println("  ⭕ Desenez cerc cu raza " + raza);
    }

    @Override
    public double aria() {
        return Math.PI * raza * raza;
    }

    @Override
    public double perimetru() {
        return 2 * Math.PI * raza;
    }
}

// ============================================================
// 3. POLIMORFISM - Implementare: Patrat
// ============================================================

class Patrat implements Desenabil, FormaGeometrica {
    private double latura;

    public Patrat(double latura) {
        this.latura = latura;
    }

    public double getLatura() {
        return latura;
    }

    @Override
    public void deseneaza() {
        System.out.println("  ◻️  Desenez pătrat cu latura " + latura);
    }

    @Override
    public double aria() {
        return latura * latura;
    }

    @Override
    public double perimetru() {
        return 4 * latura;
    }
}

// ============================================================
// 4. ABSTRACTIZARE - Clasă abstractă: FormaGeometrica
// ============================================================

abstract class FormaGeometrica {
    public abstract double aria();

    public abstract double perimetru();
}

// ============================================================
// 4. ABSTRACTIZARE - Implementare: Triunghi
// ============================================================

class Triunghi extends FormaGeometrica {
    private double a, b, c;

    public Triunghi(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double aria() {
        // Formula lui Heron
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double perimetru() {
        return a + b + c;
    }
}

// ============================================================
// 4. ABSTRACTIZARE - Implementare: Dreptunghi
// ============================================================

class Dreptunghi extends FormaGeometrica {
    private double lungime;
    private double latime;

    public Dreptunghi(double lungime, double latime) {
        this.lungime = lungime;
        this.latime = latime;
    }

    @Override
    public double aria() {
        return lungime * latime;
    }

    @Override
    public double perimetru() {
        return 2 * (lungime + latime);
    }
}

// ============================================================
// 4. ABSTRACTIZARE - Interfață: Platibil
// ============================================================

interface Platibil {
    double calculeazaSuma();
}

// ============================================================
// 4. ABSTRACTIZARE - Implementare: Card
// ============================================================

class Card implements Platibil {
    private String tip;
    private double limita;

    public Card(String tip, double limita) {
        this.tip = tip;
        this.limita = limita;
    }

    public String getTip() {
        return tip;
    }

    public double getLimita() {
        return limita;
    }

    @Override
    public double calculeazaSuma() {
        return limita;
    }
}
