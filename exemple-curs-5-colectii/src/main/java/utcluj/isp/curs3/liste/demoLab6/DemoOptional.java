package utcluj.isp.curs3.liste.demoLab6;

import java.util.Optional;

public class DemoOptional {
    public static void main(String[] args) {
        DemoOptional manager = new DemoOptional();

        // Attempt to find a user by ID
        Optional<User1> foundUser = manager.findUserById("123");
        foundUser.ifPresent(user -> System.out.println("User found: " + user.getName()));

        // Attempt to find a user that doesn't exist, with fallback
        String userName = manager.findUserById("999")
                .map(User1::getName)
                .orElse("No user found");
        System.out.println(userName);
    }

    public Optional<User1> findUserById(String id) {
        // Simulate user search
        if ("123".equals(id)) {
            return Optional.of(new User1(id, "John Doe"));
        } else {
            return Optional.empty();
        }
    }


}


class User1 {
    private String id;
    private String name;

    public User1(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}