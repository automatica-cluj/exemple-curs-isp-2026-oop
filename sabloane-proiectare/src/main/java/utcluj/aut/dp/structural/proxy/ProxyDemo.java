package utcluj.aut.dp.structural.proxy;

public class ProxyDemo {
    public static void main(String[] args) {
        Image image = new ProxyImage("imagine_mare.jpg");
        
        // Imaginea va fi încărcată și afișată la primul apel
        System.out.println("Prima afișare:");
        image.display();
        
        // Imaginea nu va fi încărcată din nou la apelurile ulterioare
        System.out.println("\nA doua afișare:");
        image.display();
    }
}
