package utcluj.isp.curs3.liste.maps1;

import java.util.HashMap;
import java.util.Objects;

public class Simple2 {
    public static void main(String[] args) {
        User user1 = new User("mihai", "123", "m@m.m");
        User user2 = new User("alex", "456", "a@a.a");
        HashMap<User, ShoppingCart> shoppingCart = new HashMap<>();
        shoppingCart.put(user1, new ShoppingCart(user1.getUsername(), "product1", 10.0, 2));
        shoppingCart.put(user2, new ShoppingCart(user2.getUsername(), "product2", 20.0, 1));


        //list all shopping carts
        for (User user : shoppingCart.keySet()) {
            ShoppingCart cart = shoppingCart.get(user);
            System.out.println("User: " + user.getUsername() + ", Product: " + cart.getProductName() + ", Price: " + cart.getPrice() + ", Quantity: " + cart.getQuantity());
        }

        //find a shopping cart for a specific user
        ShoppingCart chart = shoppingCart.get(new User("mihai", "123", "m@m.m"));
        if(chart != null) {
            System.out.println("Shopping cart found");
            System.out.println("User: " + chart.getUsername() + ", Product: " + chart.getProductName() + ", Price: " + chart.getPrice() + ", Quantity: " + chart.getQuantity());
        } else {
            System.out.println("Shopping cart not found");
        }
    }
}

class ShoppingCart {
    private String username;
    private String productName;
    private double price;
    private int quantity;

    public ShoppingCart(String username, String productName, double price, int quantity) {
        this.username = username;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public String getUsername() {
        return username;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Because we use user as a key in a map, we need to override equals and hashCode methods.
     * We compare two users based on the username.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}
