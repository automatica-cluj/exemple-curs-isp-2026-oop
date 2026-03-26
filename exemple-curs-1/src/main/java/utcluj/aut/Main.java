package utcluj.aut;

public class Main {

    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        System.out.println("Hello, World!");
        String $name = "John";
        System.out.println($name);

        //read from console
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Enter a number: ");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println("Sum: " + add(a, b));

    }
}