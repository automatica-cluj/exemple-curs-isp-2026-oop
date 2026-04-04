package aut.isp.ppt2026;

/**
 * Demo: Clasa si Obiect (Class and Object Basics in Java)
 *
 * Topics covered:
 * 1. Class vs Object (template vs instance)
 * 2. Constructors (default, parameterized, overloaded, chaining)
 * 3. Attributes (instance, static, final)
 * 4. Methods (instance, static, final, overloading)
 * 5. Keywords: new, null, this, static, final
 * 6. Access modifiers (public, protected, default, private)
 * 7. Object comparison (== vs equals())
 * 8. Garbage Collection basics
 * 9. Java naming conventions
 */

public class ClasaObiectDemo {
    public static void main(String[] args) {
        System.out.println("===== CLASA SI OBIECT - DEMO JAVA =====\n");

        // 1. CLASS vs OBJECT
        demoClassVsObject();

        // 2. CONSTRUCTORS
        demoConstructors();

        // 3. ATTRIBUTES
        demoAttributes();

        // 4. METHODS
        demoMethods();

        // 5. KEYWORDS
        demoKeywords();

        // 6. ACCESS MODIFIERS
        demoAccessModifiers();

        // 7. OBJECT COMPARISON
        demoObjectComparison();

        // 8. GARBAGE COLLECTION
        demoGarbageCollection();
    }

    // ===== 1. CLASS vs OBJECT =====
    static void demoClassVsObject() {
        System.out.println("1. CLASS vs OBJECT");
        System.out.println("  - Clasa = template/blueprint pentru obiecte");
        System.out.println("  - Obiect = instanta concreta a unei clase (creata cu 'new')");

        // aut.isp.ppt2026.Carte este o CLASA (template)
        // carte1 si carte2 sunt OBIECTE (instante)
        Carte carte1 = new Carte("1984", "George Orwell", 1949);
        Carte carte2 = new Carte("Dune", "Frank Herbert", 1965);

        System.out.println("  carte1: " + carte1);
        System.out.println("  carte2: " + carte2);
        System.out.println();
    }

    // ===== 2. CONSTRUCTORS =====
    static void demoConstructors() {
        System.out.println("2. CONSTRUCTORS");

        // Constructor cu parametri
        Persoana p1 = new Persoana("Ana", 25, "1234567890123");
        System.out.println("  Cu parametri: " + p1);

        // Constructor implicit cu valori default
        Persoana p2 = new Persoana();
        System.out.println("  Constructor implicit: " + p2);

        // Constructor cu numai nume (chaining via this())
        Persoana p3 = new Persoana("Bob");
        System.out.println("  Cu constructor chaining (this()): " + p3);

        // Overloading: mai multi constructori cu parametri diferiti
        System.out.println("  Constructors overloaded: ok\n");
    }

    // ===== 3. ATTRIBUTES =====
    static void demoAttributes() {
        System.out.println("3. ATTRIBUTES");

        // Atribute INSTANCE (fiecare obiect are valorile sale)
        Carte c1 = new Carte("aut.isp.ppt2026.Carte A", "aut.isp.ppt2026.Autor A", 2000);
        Carte c2 = new Carte("aut.isp.ppt2026.Carte B", "aut.isp.ppt2026.Autor B", 2010);
        System.out.println("  Instance attributes:");
        System.out.println("    c1.titlu = " + c1.titlu);
        System.out.println("    c2.titlu = " + c2.titlu);

        // Atribute STATIC (daca exista) - partajate intre toate obiectele
        // Si atribute FINAL (constante) - nu se pot modifica
        System.out.println("  Static + Final attributes:");
        System.out.println("    aut.isp.ppt2026.Contor.MAX_VALOARE (constant) = " + Contor.MAX_VALOARE);

        // Fiecare aut.isp.ppt2026.Contor incrementeaza valoarea statica numarator
        Contor cnt1 = new Contor("cnt1");
        Contor cnt2 = new Contor("cnt2");
        System.out.println("    aut.isp.ppt2026.Contor.numarator (static, partajat) = " + Contor.numarator);
        System.out.println();
    }

    // ===== 4. METHODS =====
    static void demoMethods() {
        System.out.println("4. METHODS");

        // Metode INSTANCE
        Carte carte = new Carte("Titlu", "aut.isp.ppt2026.Autor", 2020);
        System.out.println("  Metoda instance: carte.getInfo() = " + carte.getInfo());

        // Metode STATIC
        System.out.println("  Metoda static: aut.isp.ppt2026.Calculator.aduna(5, 3) = " + Calculator.aduna(5, 3));

        // Metode FINAL (nu se pot rescrie in subclase)
        Calculator calc = new Calculator();
        System.out.println("  Metoda final: calc.inmulteste(4, 7) = " + calc.inmulteste(4, 7));

        // Method overloading (acelasi nume, parametri diferiti)
        System.out.println("  Method overloading aut.isp.ppt2026.Calculator.aduna():");
        System.out.println("    aut.isp.ppt2026.Calculator.aduna(2, 3) = " + Calculator.aduna(2, 3));
        System.out.println("    aut.isp.ppt2026.Calculator.aduna(2.5, 3.7) = " + Calculator.aduna(2.5, 3.7));
        System.out.println();
    }

    // ===== 5. KEYWORDS =====
    static void demoKeywords() {
        System.out.println("5. KEYWORDS");

        // Keyword 'new' - creeaza obiecte
        Persoana p = new Persoana("Dana", 30, "9876543210");
        System.out.println("  'new' - creeaza obiect: " + p);

        // Keyword 'null' - referinta fara valoare
        Persoana p_null = null;
        System.out.println("  'null' - referinta vida: p_null = " + p_null);

        // Keyword 'this' - referinta la obiectul curent
        // (folosit in constructori si metode pentru a se referi la atributele obiectuui)
        System.out.println("  'this' - folosit in aut.isp.ppt2026.Persoana constructor si metode");

        // Keyword 'static' - atribut/metoda partajata de toate obiectele
        System.out.println("  'static' - aut.isp.ppt2026.Contor.numarator = " + Contor.numarator);

        // Keyword 'final' - nu se poate modifica
        System.out.println("  'final' - aut.isp.ppt2026.Contor.MAX_VALOARE (constant) = " + Contor.MAX_VALOARE);
        System.out.println();
    }

    // ===== 6. ACCESS MODIFIERS =====
    static void demoAccessModifiers() {
        System.out.println("6. ACCESS MODIFIERS");
        System.out.println("  public - accesibil din orice parte");
        System.out.println("  protected - accesibil in aceeasi pachet si subclase");
        System.out.println("  default (fara modificator) - accesibil in aceeasi pachet");
        System.out.println("  private - accesibil doar in aceeasi clasa");

        Carte carte = new Carte("aut.isp.ppt2026.Carte", "aut.isp.ppt2026.Autor", 2020);

        // public
        System.out.println("  carte.titlu (public) = " + carte.titlu);

        // Getter pentru atribute private
        System.out.println("  carte.getAnPublicatie() (getter pentru private) = " +
                         carte.getAnPublicatie());

        // Nu putem accesa direct anPublicatie (este private)
        // System.out.println(carte.anPublicatie);  // EROARE!
        System.out.println();
    }

    // ===== 7. OBJECT COMPARISON =====
    static void demoObjectComparison() {
        System.out.println("7. OBJECT COMPARISON (== vs equals())");

        Persoana p1 = new Persoana("Ion", 28, "1111111111111");
        Persoana p2 = new Persoana("Ion", 28, "1111111111111");
        Persoana p3 = p1;

        System.out.println("  p1 == p2: " + (p1 == p2) + " (compara referinte - FALSE)");
        System.out.println("  p1.equals(p2): " + p1.equals(p2) + " (compara continut - TRUE)");
        System.out.println("  p1 == p3: " + (p1 == p3) + " (aceeasi referinta - TRUE)");
        System.out.println();
    }

    // ===== 8. GARBAGE COLLECTION =====
    static void demoGarbageCollection() {
        System.out.println("8. GARBAGE COLLECTION");

        // Obiecte create si apoi pierdute - vor fi sterse de GC
        for (int i = 0; i < 3; i++) {
            Carte carte = new Carte("aut.isp.ppt2026.Carte " + i, "aut.isp.ppt2026.Autor " + i, 2020 + i);
            System.out.println("  Creat: " + carte);
        }

        System.out.println("  Aceste obiecte nu mai sunt referentiate...");
        System.out.println("  Garbage Collector va sterge obiectele neusate");

        // Forteaza GC (rarely used in practice)
        System.gc();
        System.out.println("  System.gc() - solicita Garbage Collection");
        System.out.println();
    }
}

// ===== CLASA: aut.isp.ppt2026.Carte =====
class Carte {
    // Atribute instance (fiecare obiect are valorile sale)
    public String titlu;
    public String autor;
    private int anPublicatie;  // private - acces restrictionat

    // Constructor cu parametri
    public Carte(String titlu, String autor, int anPublicatie) {
        this.titlu = titlu;         // 'this' = obiectul curent
        this.autor = autor;
        this.anPublicatie = anPublicatie;
    }

    // Getter pentru atribut private
    public int getAnPublicatie() {
        return anPublicatie;
    }

    // Metoda instance
    public String getInfo() {
        return titlu + " de " + autor + " (" + anPublicatie + ")";
    }

    @Override
    public String toString() {
        return "[" + titlu + ", " + autor + ", " + anPublicatie + "]";
    }
}

// ===== CLASA: aut.isp.ppt2026.Persoana =====
class Persoana {
    // Atribute instance
    private String nume;
    private int varsta;
    private String cnp;

    // Constructor cu trei parametri
    public Persoana(String nume, int varsta, String cnp) {
        this.nume = nume;
        this.varsta = varsta;
        this.cnp = cnp;
    }

    // Constructor implicit (default) - valori default
    public Persoana() {
        this("Necunoscut", 0, "");
        // Apel Constructor chaining cu 'this()'
    }

    // Constructor cu numai nume (overloading)
    public Persoana(String nume) {
        this(nume, 0, "");
        // Apel Constructor chaining cu 'this()'
    }

    // Getters
    public String getNume() {
        return nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public String getCnp() {
        return cnp;
    }

    // Suprascriere de equals() - compara continut, nu referinte
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Persoana)) return false;

        Persoana other = (Persoana) obj;
        return nume.equals(other.nume) &&
               varsta == other.varsta &&
               cnp.equals(other.cnp);
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Persoana{" + nume + ", " + varsta + " ani, " + cnp + "}";
    }
}

// ===== CLASA: aut.isp.ppt2026.Calculator =====
class Calculator {
    // Metoda static (nu depinde de vreun obiect)
    public static int aduna(int a, int b) {
        return a + b;
    }

    // Method overloading - acelasi nume, parametri diferiti
    public static double aduna(double a, double b) {
        return a + b;
    }

    // Metoda final (nu se poate rescrie in subclase)
    public final int inmulteste(int a, int b) {
        return a * b;
    }
}

// ===== CLASA: aut.isp.ppt2026.Contor =====
class Contor {
    // Atribut STATIC - partajat de toate instantele
    public static int numarator = 0;

    // Atribut FINAL - constant, nu se poate modifica
    public static final int MAX_VALOARE = 100;

    // Atribut instance
    private String id;

    // Constructor - incrementeaza numarator static
    public Contor(String id) {
        this.id = id;
        numarator++;  // Incrementeaza numaratorul partajat
    }

    public String getId() {
        return id;
    }

    // Metoda static - lucreaza cu atribute static
    public static void resetNumarator() {
        numarator = 0;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Contor{" + id + "}";
    }
}
