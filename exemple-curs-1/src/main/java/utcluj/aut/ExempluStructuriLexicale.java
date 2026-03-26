package utcluj.aut;

public class ExempluStructuriLexicale {
    public static void main(String[] args) {
        // Declarare și inițializare de variabile
        int numar = 10;          // literal întreg
        double pi = 3.14159;     // literal cu virgulă mobilă
        char litera = 'A';       // literal caracter
        String mesaj = "Hello";  // literal șir de caractere
        boolean estePar = true;  // literal boolean

        // Utilizare operatori
        int suma = numar + 5;
        double produs = numar * pi;
        numar++;  // incrementare

        // Structuri de control
        if (numar % 2 == 0) {
            estePar = true;
        } else {
            estePar = false;
        }

        // Operatorul ternar
        String rezultat = estePar ? "par" : "impar";

        // Afișare rezultate
        System.out.println("Numărul " + numar + " este " + rezultat);
        System.out.println("Suma: " + suma);
        System.out.println("Produsul: " + produs);
    }
}