package aut.isp.ppt2026;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.OptionalDouble;

/**
 * OPTIONAL IN JAVA — Solving the Null Problem
 *
 * Topics covered:
 * 1. The null problem — NullPointerException and defensive pyramids
 * 2. Creating Optional — empty(), of(), ofNullable()
 * 3. Accessing values — isPresent(), isEmpty(), get(), ifPresent()
 * 4. Default values — orElse(), orElseGet(), orElseThrow()
 * 5. Transformations — map(), flatMap(), filter()
 * 6. Chaining operations — the aut.isp.ppt2026.User -> aut.isp.ppt2026.Address -> City chain
 * 7. Optional vs null comparison
 * 8. Primitive optionals — OptionalInt, OptionalDouble
 * 9. Best practices and anti-patterns
 */

public class OptionalNullDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. THE NULL PROBLEM ===\n");
        demonstrateNullProblem();

        System.out.println("\n=== 2. CREATING OPTIONAL ===\n");
        demonstrateCreatingOptional();

        System.out.println("\n=== 3. ACCESSING VALUES ===\n");
        demonstrateAccessingValues();

        System.out.println("\n=== 4. DEFAULT VALUES ===\n");
        demonstrateDefaultValues();

        System.out.println("\n=== 5. TRANSFORMATIONS ===\n");
        demonstrateTransformations();

        System.out.println("\n=== 6. CHAINING OPERATIONS ===\n");
        demonstrateChainingOperations();

        System.out.println("\n=== 7. OPTIONAL VS NULL COMPARISON ===\n");
        demonstrateOptionalVsNull();

        System.out.println("\n=== 8. PRIMITIVE OPTIONALS ===\n");
        demonstratePrimitiveOptionals();

        System.out.println("\n=== 9. BEST PRACTICES ===\n");
        demonstrateBestPractices();

        System.out.println("\n=== 10. ANTI-PATTERNS ===\n");
        demonstrateAntiPatterns();
    }

    // ============================================================================
    // 1. THE NULL PROBLEM
    // ============================================================================

    private static void demonstrateNullProblem() {
        System.out.println("Problem 1: NullPointerException");
        User user = null;
        try {
            String city = user.getAddress().get().getCity();
            System.out.println("City: " + city);
        } catch (NullPointerException e) {
            System.out.println("ERROR: " + e.getClass().getSimpleName() + " - user is null!");
        }

        System.out.println("\nProblem 2: Defensive null-check pyramid (hard to read)");
        User user2 = new User(1, "Alice", null);
        String city2;
        if (user2 != null) {
            if (user2.getAddress().isPresent()) {
                Address address = user2.getAddress().get();
                if (address.getCity() != null) {
                    city2 = address.getCity();
                } else {
                    city2 = "Unknown";
                }
            } else {
                city2 = "Unknown";
            }
        } else {
            city2 = "Unknown";
        }
        System.out.println("City (pyramid approach): " + city2);
    }

    // ============================================================================
    // 2. CREATING OPTIONAL
    // ============================================================================

    private static void demonstrateCreatingOptional() {
        System.out.println("Optional.empty() — wraps nothing");
        Optional<String> empty = Optional.empty();
        System.out.println("empty: " + empty);

        System.out.println("\nOptional.of(value) — wraps a non-null value");
        Optional<String> nonEmpty = Optional.of("Hello");
        System.out.println("nonEmpty: " + nonEmpty);

        System.out.println("\nOptional.of(null) — throws NullPointerException!");
        try {
            Optional<String> bad = Optional.of(null);
        } catch (NullPointerException e) {
            System.out.println("ERROR: Optional.of(null) throws " + e.getClass().getSimpleName());
        }

        System.out.println("\nOptional.ofNullable(value) — safe wrapper");
        Optional<String> maybe = Optional.ofNullable(null);
        System.out.println("ofNullable(null): " + maybe);
        Optional<String> maybe2 = Optional.ofNullable("World");
        System.out.println("ofNullable(\"World\"): " + maybe2);
    }

    // ============================================================================
    // 3. ACCESSING VALUES
    // ============================================================================

    private static void demonstrateAccessingValues() {
        Optional<String> opt1 = Optional.of("Java");
        Optional<String> opt2 = Optional.empty();

        System.out.println("isPresent() — checks if value exists");
        System.out.println("\"Java\" isPresent: " + opt1.isPresent());
        System.out.println("empty isPresent: " + opt2.isPresent());

        System.out.println("\nisEmpty() — Java 11+, checks if empty");
        System.out.println("\"Java\" isEmpty: " + opt1.isEmpty());
        System.out.println("empty isEmpty: " + opt2.isEmpty());

        System.out.println("\nget() — retrieves value (risky!)");
        System.out.println("\"Java\" get: " + opt1.get());
        try {
            String value = opt2.get();
        } catch (Exception e) {
            System.out.println("empty get throws: " + e.getClass().getSimpleName());
        }

        System.out.println("\nifPresent(Consumer) — safe and functional");
        opt1.ifPresent(value -> System.out.println("Found: " + value));
        opt2.ifPresent(value -> System.out.println("Found: " + value)); // does nothing
    }

    // ============================================================================
    // 4. DEFAULT VALUES
    // ============================================================================

    private static void demonstrateDefaultValues() {
        Optional<String> opt1 = Optional.of("Actual");
        Optional<String> opt2 = Optional.empty();

        System.out.println("orElse(defaultValue) — eager evaluation");
        System.out.println("opt1.orElse(\"Default\"): " + opt1.orElse("Default"));
        System.out.println("opt2.orElse(\"Default\"): " + opt2.orElse("Default"));

        System.out.println("\norElseGet(Supplier) — lazy evaluation");
        System.out.println("opt1.orElseGet(() -> \"Generated\"): " +
            opt1.orElseGet(() -> {
                System.out.println("  [Supplier called]");
                return "Generated";
            }));
        System.out.println("opt2.orElseGet(() -> \"Generated\"): " +
            opt2.orElseGet(() -> {
                System.out.println("  [Supplier called]");
                return "Generated";
            }));

        System.out.println("\norElseThrow() — throws NoSuchElementException if empty");
        try {
            System.out.println("opt1.orElseThrow(): " + opt1.orElseThrow());
            String value = opt2.orElseThrow();
        } catch (Exception e) {
            System.out.println("opt2.orElseThrow() throws: " + e.getClass().getSimpleName());
        }

        System.out.println("\norElseThrow(Supplier) — custom exception");
        try {
            opt2.orElseThrow(() -> new IllegalArgumentException("Value not found!"));
        } catch (Exception e) {
            System.out.println("opt2 throws custom: " + e.getClass().getSimpleName());
        }
    }

    // ============================================================================
    // 5. TRANSFORMATIONS
    // ============================================================================

    private static void demonstrateTransformations() {
        Optional<String> name = Optional.of("alice");

        System.out.println("map(Function) — transform the value");
        Optional<String> uppercase = name.map(String::toUpperCase);
        System.out.println("name.map(String::toUpperCase): " + uppercase);

        Optional<Integer> length = name.map(String::length);
        System.out.println("name.map(String::length): " + length);

        System.out.println("\nmap() on empty returns empty");
        Optional<String> empty = Optional.empty();
        System.out.println("empty.map(String::toUpperCase): " + empty.map(String::toUpperCase));

        System.out.println("\nfilter(Predicate) — keep value if condition true");
        Optional<String> longName = name.filter(s -> s.length() > 3);
        System.out.println("name.filter(length > 3): " + longName);
        Optional<String> shortName = name.filter(s -> s.length() < 3);
        System.out.println("name.filter(length < 3): " + shortName);

        System.out.println("\nflatMap(Function) — avoid Optional<Optional<T>>");
        UserRepository repo = new UserRepository();
        Optional<User> user = repo.findUserById(1);

        // WRONG: map with a function returning Optional -> Optional<Optional<aut.isp.ppt2026.Address>>
        System.out.println("user.map(aut.isp.ppt2026.User::getAddress): " + user.map(User::getAddress));

        // RIGHT: flatMap with a function returning Optional -> Optional<aut.isp.ppt2026.Address>
        System.out.println("user.flatMap(aut.isp.ppt2026.User::getAddress): " + user.flatMap(User::getAddress));
    }

    // ============================================================================
    // 6. CHAINING OPERATIONS
    // ============================================================================

    private static void demonstrateChainingOperations() {
        UserRepository repo = new UserRepository();

        System.out.println("The classic chain: findUser -> getAddress -> getCity");
        System.out.println("(Without Optional — nested null checks nightmare)");

        User user = repo.findUserById(1).orElse(null);
        String cityOld;
        if (user != null) {
            Optional<Address> address = user.getAddress();
            if (address.isPresent()) {
                String city = address.get().getCity();
                if (city != null) {
                    cityOld = city;
                } else {
                    cityOld = "Unknown";
                }
            } else {
                cityOld = "Unknown";
            }
        } else {
            cityOld = "Unknown";
        }
        System.out.println("Result (old way): " + cityOld);

        System.out.println("\nWith Optional — clean chain:");
        String cityNew = repo.findUserById(1)
            .flatMap(User::getAddress)
            .map(Address::getCity)
            .orElse("Unknown");
        System.out.println("Result (new way): " + cityNew);

        System.out.println("\naut.isp.ppt2026.User 2 (no address):");
        String city2 = repo.findUserById(2)
            .flatMap(User::getAddress)
            .map(Address::getCity)
            .orElse("Unknown");
        System.out.println("Result: " + city2);

        System.out.println("\naut.isp.ppt2026.User 999 (not found):");
        String city3 = repo.findUserById(999)
            .flatMap(User::getAddress)
            .map(Address::getCity)
            .orElse("Unknown");
        System.out.println("Result: " + city3);
    }

    // ============================================================================
    // 7. OPTIONAL VS NULL COMPARISON
    // ============================================================================

    private static void demonstrateOptionalVsNull() {
        System.out.println("Method returns null (OLD approach):");
        Address nullAddress = UserRepository.findAddressOld(1);
        if (nullAddress != null) {
            System.out.println("aut.isp.ppt2026.Address found: " + nullAddress);
        } else {
            System.out.println("aut.isp.ppt2026.Address not found");
        }

        System.out.println("\nMethod returns Optional (NEW approach):");
        Optional<Address> optionalAddress = UserRepository.findAddressNew(1);
        optionalAddress.ifPresentOrElse(
            addr -> System.out.println("aut.isp.ppt2026.Address found: " + addr),
            () -> System.out.println("aut.isp.ppt2026.Address not found")
        );

        System.out.println("\nOptional advantages:");
        System.out.println("1. Intent is clear — method might return nothing");
        System.out.println("2. Safer — compile-time awareness, no forgotten null checks");
        System.out.println("3. Functional — enables map, flatMap, filter chains");
        System.out.println("4. Self-documenting — no @Nullable annotations needed");
    }

    // ============================================================================
    // 8. PRIMITIVE OPTIONALS
    // ============================================================================

    private static void demonstratePrimitiveOptionals() {
        System.out.println("OptionalInt — for int values (avoids Integer boxing)");
        OptionalInt age1 = OptionalInt.of(25);
        OptionalInt age2 = OptionalInt.empty();
        System.out.println("age1 (25): " + age1.orElse(0));
        System.out.println("age2 (empty): " + age2.orElse(0));

        System.out.println("\nOptionalDouble — for double values");
        OptionalDouble price1 = OptionalDouble.of(19.99);
        OptionalDouble price2 = OptionalDouble.empty();
        System.out.println("price1 (19.99): " + price1.orElse(0.0));
        System.out.println("price2 (empty): " + price2.orElse(0.0));

        System.out.println("\nWhy primitives? Avoid Integer/Double wrapper objects.");
        System.out.println("Use for performance-critical code with many values.");
    }

    // ============================================================================
    // 9. BEST PRACTICES
    // ============================================================================

    private static void demonstrateBestPractices() {
        System.out.println("BEST PRACTICE 1: Use Optional as a return type");
        System.out.println("✓ public Optional<aut.isp.ppt2026.User> findUser(int id)");
        System.out.println("✗ public aut.isp.ppt2026.User findUser(int id) // unclear if nullable\n");

        System.out.println("BEST PRACTICE 2: Don't use Optional as a parameter");
        System.out.println("✗ public void processUser(Optional<aut.isp.ppt2026.User> user)");
        System.out.println("✓ Overload: processUser(aut.isp.ppt2026.User) and processUserIfPresent(Optional<aut.isp.ppt2026.User>)");
        System.out.println("  Or use: public void processUser(aut.isp.ppt2026.User user) // caller handles Optional\n");

        System.out.println("BEST PRACTICE 3: Don't use Optional as a field");
        System.out.println("✗ class Order { Optional<Discount> discount; }");
        System.out.println("✓ class Order { Discount discount; } // nullable\n");

        System.out.println("BEST PRACTICE 4: Return Optional.empty(), not null");
        System.out.println("✓ return Optional.empty();");
        System.out.println("✗ return null;\n");

        System.out.println("BEST PRACTICE 5: Use functional methods");
        Optional<Comanda> order = Optional.of(new Comanda(100, Optional.of(10.0)));
        order.map(Comanda::getDiscountAmount).ifPresent(d ->
            System.out.println("Discount: $" + d)
        );
    }

    // ============================================================================
    // 10. ANTI-PATTERNS
    // ============================================================================

    private static void demonstrateAntiPatterns() {
        Optional<String> opt = Optional.of("Hello");
        Optional<String> empty = Optional.empty();

        System.out.println("ANTI-PATTERN 1: isPresent() + get() instead of functional");
        System.out.println("✗ BAD:");
        if (opt.isPresent()) {
            System.out.println("  Value: " + opt.get());
        }

        System.out.println("✓ GOOD:");
        opt.ifPresent(v -> System.out.println("  Value: " + v));

        System.out.println("\nANTI-PATTERN 2: get() without checking (risky!)");
        System.out.println("✗ BAD: String value = empty.get(); // throws!");
        System.out.println("✓ GOOD: String value = empty.orElse(\"default\");");

        System.out.println("\nANTI-PATTERN 3: Using Optional for null parameters");
        System.out.println("✗ BAD: void process(Optional<aut.isp.ppt2026.User> user)");
        System.out.println("✓ GOOD: void process(aut.isp.ppt2026.User user) { ... }");

        System.out.println("\nANTI-PATTERN 4: Optional<Optional<T>> from map()");
        System.out.println("✗ BAD: opt.map(this::methodReturnsOptional)");
        System.out.println("✓ GOOD: opt.flatMap(this::methodReturnsOptional)");
    }
}

// ============================================================================
// CUSTOM CLASSES
// ============================================================================

class User {
    private int id;
    private String name;
    private Optional<Address> address;

    public User(int id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = Optional.ofNullable(address);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.User(" + id + ", " + name + ", " + address + ")";
    }
}

class Address {
    private String city;
    private String street;

    public Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Address(" + city + ", " + street + ")";
    }
}

class Comanda {
    private double amount;
    private Optional<Double> discount;

    public Comanda(double amount, Optional<Double> discount) {
        this.amount = amount;
        this.discount = discount;
    }

    public double getAmount() {
        return amount;
    }

    public Optional<Double> getDiscount() {
        return discount;
    }

    public double getDiscountAmount() {
        return discount.orElse(0.0);
    }

    public double getFinalPrice() {
        return amount - getDiscountAmount();
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Comanda(amount=" + amount + ", discount=" + discount + ")";
    }
}

class UserRepository {
    public Optional<User> findUserById(int id) {
        // Mock data
        if (id == 1) {
            return Optional.of(new User(1, "Alice", new Address("New York", "5th Ave")));
        } else if (id == 2) {
            return Optional.of(new User(2, "Bob", null)); // Bob has no address
        } else {
            return Optional.empty(); // aut.isp.ppt2026.User not found
        }
    }

    // OLD APPROACH: returns null
    static Address findAddressOld(int userId) {
        if (userId == 1) {
            return new Address("New York", "5th Ave");
        }
        return null;
    }

    // NEW APPROACH: returns Optional
    static Optional<Address> findAddressNew(int userId) {
        if (userId == 1) {
            return Optional.of(new Address("New York", "5th Ave"));
        }
        return Optional.empty();
    }
}
