package utcluj.isp.curs3.liste.demoLab6;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

@Getter
class ActiveSession{
    private String userName;
    private HashMap<Product, Integer> shopingChart = new HashMap<>();

    public ActiveSession(String userName) {
        this.userName = userName;
    }

    public void addToChart(Product p, int q){
        shopingChart.put(p,q);
    }
}

@Getter
@AllArgsConstructor
class Product{
    private String name;
    private double price;
}

class OnlineStore{
    private ArrayList<Product> products = new ArrayList<>();
    private HashMap<String, ActiveSession> sessions= new HashMap<>();
    OnlineStore(){
        products.add(new Product("Laptop", 1000));
        products.add(new Product("Mouse", 20));
        products.add(new Product("Keyboard", 50));
    }

    public void addSession(String userName){
        System.out.println("Creating session for "+userName);
        sessions.put(userName, new ActiveSession(userName));
    }

//    public void addProductToHopingChart(String product; int q, String user){
//        //validate user is logedin
//        //search and get referene to Product
//        //get ActiveSession obejct and call addToSHpoigCrat(p,q)
//    }

    public void removeSession(String userName){
        System.out.println("Removing session for "+userName);
    }
}

class LoginSystem{
    private HashSet<User> users = new HashSet<>();
    private OnlineStore onlineStore;

    public LoginSystem(OnlineStore onlineStore) {
        this.onlineStore = onlineStore;
    }

    public void register(String name, String pwd){
        users.add(new User(name,pwd));
    }

    public void login(String name, String pwd){
        for(User u: users)
            if(u.getPassword().equals(pwd)&&u.getUserId().equals(name)){
                System.out.println("Login complet!");
                onlineStore.addSession(name);
            }
    }
}

@Getter
@AllArgsConstructor
class User{
    private String userId;
    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}

public class DemoStore {
    public static void main(String[] args) {
        User u1 = new User("user1","1234");
        User u2 = new User("user2","1234");
        LoginSystem loginSystem = new LoginSystem(new OnlineStore());
        loginSystem.register(u1.getUserId(),u1.getPassword());
        loginSystem.register(u2.getUserId(),u2.getPassword());
        loginSystem.login("user1","1234");
    }
}
