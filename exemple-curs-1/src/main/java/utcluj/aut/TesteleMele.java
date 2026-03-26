package utcluj.aut;

public class TesteleMele {

    static void testArray(){
        int[] numere = {10, 20, 30, 40, 50};

    // Accesarea elementelor
            int primulElement = numere[0];  // 10
            int ultimulElement = numere[4];  // 50

    // Modificarea valorilor
            numere[2] = 35;  // Acum array-ul este {10, 20, 35, 40, 50}
    }

    public static void testIf(){
        int nota = 8;
        if (nota >= 9) {
            System.out.println("Excelent");
        } else if (nota >= 7) {
            System.out.println("Bine");
        } else if (nota >= 5) {
            System.out.println("Satisfăcător");
        } else {
            System.out.println("Nesatisfăcător");
        }
    }

    public static void main(String[] args) {
      testIf();
      testArray();

    }
}
