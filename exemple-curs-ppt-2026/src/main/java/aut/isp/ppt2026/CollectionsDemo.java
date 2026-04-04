import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Java Collections Framework — Complete Demo
 * Based on: 08-collections-framework.pptx
 *
 * This single file demonstrates every major concept a student needs:
 *   1. equals() & hashCode()
 *   2. Generics
 *   3. List  (ArrayList, LinkedList)
 *   4. Set   (HashSet, LinkedHashSet, TreeSet)
 *   5. Queue (PriorityQueue, ArrayDeque)
 *   6. Map   (HashMap, LinkedHashMap, TreeMap)
 *   7. Iterating collections
 *   8. Comparable & Comparator
 *   9. Lambda expressions
 *  10. Streams API
 *  11. Best practices (immutable collections, removeIf, etc.)
 *
 * All demos use custom classes (Student, Product, Task) — not plain Strings.
 */


// =========================================================================
// CUSTOM CLASSES — defined as top-level (non-static) classes in the same file
// =========================================================================

/**
 * Student — demonstrates equals/hashCode and Comparable.
 * Two students are equal if they share the same id.
 * Natural ordering: by grade ascending.
 */
class Student implements Comparable<Student> {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int    getId()    { return id; }
    public String getName()  { return name; }
    public double getGrade() { return grade; }

    // --- equals: two students are "the same" if they share the same id ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                     // same reference
        if (!(o instanceof Student)) return false;      // wrong type
        return this.id == ((Student) o).id;             // compare by id
    }

    // --- hashCode: MUST match equals — same id -> same hash ---
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // --- Comparable: natural ordering by grade (ascending) ---
    @Override
    public int compareTo(Student other) {
        return Double.compare(this.grade, other.grade);
    }

    @Override
    public String toString() {
        return name + "(id=" + id + ", grade=" + grade + ")";
    }
}


/**
 * Product — used in List, Set, and Map demos.
 * Two products are equal if they share the same code.
 */
class Product implements Comparable<Product> {
    private String code;
    private String name;
    private double price;

    public Product(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public String getCode()  { return code; }
    public String getName()  { return name; }
    public double getPrice() { return price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        return this.code.equals(((Product) o).code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    // Natural ordering: by price ascending
    @Override
    public int compareTo(Product other) {
        return Double.compare(this.price, other.price);
    }

    @Override
    public String toString() {
        return name + "(" + code + ", $" + price + ")";
    }
}


/**
 * Task — used in Queue demos.
 * Each task has a priority (lower number = higher priority).
 */
class Task implements Comparable<Task> {
    private int priority;
    private String description;

    public Task(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    public int    getPriority()    { return priority; }
    public String getDescription() { return description; }

    // Natural ordering: by priority ascending (1 is most urgent)
    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.priority, other.priority);
    }

    @Override
    public String toString() {
        return "[P" + priority + "] " + description;
    }
}


/**
 * Generic Pair class — demonstrates generics with ANY two types.
 */
class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst()  { return first; }
    public B getSecond() { return second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}


// =========================================================================
// MAIN CLASS — runs every demo in order
// =========================================================================

public class CollectionsDemo {

    public static void main(String[] args) {

        separator("1. equals() & hashCode()");
        demoEqualsHashCode();

        separator("2. Generics");
        demoGenerics();

        separator("3. Lists — ArrayList & LinkedList");
        demoLists();

        separator("4. Sets — HashSet, LinkedHashSet, TreeSet");
        demoSets();

        separator("5. Queues — PriorityQueue & ArrayDeque");
        demoQueues();

        separator("6. Maps — HashMap, LinkedHashMap, TreeMap");
        demoMaps();

        separator("7. Iterating Collections");
        demoIterating();

        separator("8. Comparable & Comparator");
        demoSorting();

        separator("9. Lambda Expressions");
        demoLambdas();

        separator("10. Streams API");
        demoStreams();

        separator("11. Best Practices");
        demoBestPractices();
    }


    // =========================================================================
    // 1. equals() & hashCode()
    // =========================================================================
    static void demoEqualsHashCode() {
        Student s1 = new Student(1, "Alice", 9.5);
        Student s2 = new Student(1, "Alice", 9.5);   // same id as s1
        Student s3 = new Student(2, "Bob",   7.0);

        // Without overriding equals, s1 == s2 would be false (different objects).
        // Because we overrode equals to compare by id, this is true:
        System.out.println("s1.equals(s2)? " + s1.equals(s2));  // true  — same id
        System.out.println("s1.equals(s3)? " + s1.equals(s3));  // false — different id

        // hashCode consistency — equal objects MUST have the same hash
        System.out.println("s1 hash: " + s1.hashCode());
        System.out.println("s2 hash: " + s2.hashCode() + "  (same as s1!)");

        // Why it matters: HashSet uses hashCode to find the bucket
        Set<Student> set = new HashSet<>();
        set.add(s1);
        set.add(s2);  // duplicate id -> won't be added
        System.out.println("Set size (should be 1): " + set.size());
    }


    // =========================================================================
    // 2. Generics
    // =========================================================================
    static void demoGenerics() {
        // Generic class — Pair<A, B> works with ANY two types
        Pair<Student, Double> examResult = new Pair<>(
            new Student(1, "Alice", 9.5),
            9.5
        );
        Student student = examResult.getFirst();   // no cast needed!
        double  score   = examResult.getSecond();
        System.out.println("Pair: " + examResult);

        // Generic method — the type parameter <T> is inferred automatically
        printItem(new Student(2, "Bob", 8.0));
        printItem(new Product("P01", "Laptop", 999.99));
        printItem(42);

        // With collections: List<Student> only accepts Students
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice", 9.0));
        // students.add(new Product(...));  // COMPILE ERROR — generics catch this early!
        System.out.println("Type-safe list: " + students);
    }

    /** Generic method: type parameter <T> is inferred automatically. */
    static <T> void printItem(T item) {
        System.out.println("  -> " + item);
    }


    // =========================================================================
    // 3. Lists
    // =========================================================================
    static void demoLists() {
        // --- ArrayList: fast random access O(1), backed by dynamic array ---
        List<Product> products = new ArrayList<>();
        products.add(new Product("P01", "Laptop",  999.99));
        products.add(new Product("P02", "Mouse",    29.99));
        products.add(new Product("P03", "Keyboard", 79.99));
        products.add(new Product("P02", "Mouse",    29.99));  // duplicates allowed!
        System.out.println("ArrayList:");
        products.forEach(p -> System.out.println("  " + p));
        System.out.println("  get(0): " + products.get(0));             // fast O(1)
        System.out.println("  indexOf(Mouse): " + products.indexOf(new Product("P02", "Mouse", 29.99)));

        // replace element at index
        products.set(0, new Product("P04", "Monitor", 349.99));
        System.out.println("  After set(0, Monitor): " + products.get(0));

        // --- LinkedList: fast insert/remove at ends O(1), slow random access ---
        LinkedList<Task> taskList = new LinkedList<>();
        taskList.addFirst(new Task(1, "Fix critical bug"));
        taskList.addLast(new Task(3, "Write documentation"));
        taskList.addLast(new Task(2, "Code review"));
        System.out.println("LinkedList:");
        taskList.forEach(t -> System.out.println("  " + t));
        System.out.println("  getFirst(): " + taskList.getFirst());
        System.out.println("  getLast():  " + taskList.getLast());

        // Key takeaway: use ArrayList by default; LinkedList only for heavy
        // insertions/deletions at both ends.
    }


    // =========================================================================
    // 4. Sets
    // =========================================================================
    static void demoSets() {
        Product laptop   = new Product("P01", "Laptop",  999.99);
        Product mouse    = new Product("P02", "Mouse",    29.99);
        Product keyboard = new Product("P03", "Keyboard", 79.99);
        Product dupMouse = new Product("P02", "Mouse",    29.99);  // same code as mouse

        // --- HashSet: O(1) add/contains, NO ordering ---
        Set<Product> hashSet = new HashSet<>();
        hashSet.add(mouse);
        hashSet.add(laptop);
        hashSet.add(keyboard);
        hashSet.add(dupMouse);    // duplicate code "P02" -> ignored!
        System.out.println("HashSet (no order, size=" + hashSet.size() + "):");
        hashSet.forEach(p -> System.out.println("  " + p));

        // --- LinkedHashSet: O(1) operations, INSERTION order preserved ---
        Set<Product> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(mouse);
        linkedHashSet.add(laptop);
        linkedHashSet.add(keyboard);
        System.out.println("LinkedHashSet (insertion order):");
        linkedHashSet.forEach(p -> System.out.println("  " + p));

        // --- TreeSet: O(log n), elements always SORTED (uses compareTo -> by price) ---
        Set<Product> treeSet = new TreeSet<>();
        treeSet.add(laptop);
        treeSet.add(mouse);
        treeSet.add(keyboard);
        System.out.println("TreeSet (sorted by price via Comparable):");
        treeSet.forEach(p -> System.out.println("  " + p));

        // contains() — fast lookup using equals/hashCode
        System.out.println("hashSet.contains(mouse)? " + hashSet.contains(mouse));
    }


    // =========================================================================
    // 5. Queues
    // =========================================================================
    static void demoQueues() {
        // --- PriorityQueue: elements come out by priority (lowest number first) ---
        Queue<Task> pq = new PriorityQueue<>();
        pq.offer(new Task(3, "Write tests"));
        pq.offer(new Task(1, "Fix critical bug"));
        pq.offer(new Task(2, "Code review"));
        System.out.println("PriorityQueue — tasks processed by priority:");
        System.out.println("  poll(): " + pq.poll());   // P1 — most urgent
        System.out.println("  poll(): " + pq.poll());   // P2
        System.out.println("  peek(): " + pq.peek());   // P3 — view without removing

        // --- ArrayDeque: double-ended queue — use as STACK or QUEUE ---

        // Use as a Stack (LIFO): push/pop
        Deque<Task> stack = new ArrayDeque<>();
        stack.push(new Task(1, "Open editor"));
        stack.push(new Task(2, "Write code"));
        stack.push(new Task(3, "Save file"));
        System.out.println("ArrayDeque as Stack (LIFO):");
        System.out.println("  pop(): " + stack.pop());   // "Save file" — last in, first out

        // Use as a Queue (FIFO): offerLast/pollFirst
        Deque<Task> queue = new ArrayDeque<>();
        queue.offerLast(new Task(1, "Customer A request"));
        queue.offerLast(new Task(2, "Customer B request"));
        queue.offerLast(new Task(3, "Customer C request"));
        System.out.println("ArrayDeque as Queue (FIFO):");
        System.out.println("  pollFirst(): " + queue.pollFirst());  // Customer A — first in, first out
    }


    // =========================================================================
    // 6. Maps
    // =========================================================================
    static void demoMaps() {
        Student alice   = new Student(1, "Alice",   9.5);
        Student bob     = new Student(2, "Bob",     7.0);
        Student charlie = new Student(3, "Charlie", 8.5);

        // --- HashMap: O(1) get/put, NO ordering ---
        Map<Integer, Student> hashMap = new HashMap<>();
        hashMap.put(alice.getId(),   alice);
        hashMap.put(bob.getId(),     bob);
        hashMap.put(charlie.getId(), charlie);
        System.out.println("HashMap (id -> Student):");
        System.out.println("  get(2): " + hashMap.get(2));
        System.out.println("  containsKey(1): " + hashMap.containsKey(1));

        // --- LinkedHashMap: insertion order preserved ---
        Map<Integer, Student> linkedMap = new LinkedHashMap<>();
        linkedMap.put(bob.getId(),     bob);
        linkedMap.put(alice.getId(),   alice);
        linkedMap.put(charlie.getId(), charlie);
        System.out.println("LinkedHashMap (insertion order):");
        linkedMap.values().forEach(s -> System.out.println("  " + s));

        // --- TreeMap: keys always sorted ---
        Map<Integer, Student> treeMap = new TreeMap<>();
        treeMap.put(charlie.getId(), charlie);
        treeMap.put(alice.getId(),   alice);
        treeMap.put(bob.getId(),     bob);
        System.out.println("TreeMap (sorted by key = id):");
        treeMap.values().forEach(s -> System.out.println("  " + s));

        // --- Iterating a Map with entrySet() ---
        System.out.println("Iterating with entrySet():");
        for (Map.Entry<Integer, Student> entry : hashMap.entrySet()) {
            System.out.println("  ID " + entry.getKey() + " -> " + entry.getValue());
        }
    }


    // =========================================================================
    // 7. Iterating Collections
    // =========================================================================
    static void demoIterating() {
        List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Alice",   9.0),
            new Student(2, "Bob",     4.5),
            new Student(3, "Charlie", 8.0),
            new Student(4, "Diana",   3.0)
        ));

        // Way 1: Enhanced for-each loop (simplest)
        System.out.println("For-each loop:");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        // Way 2: Iterator — allows SAFE removal during iteration
        System.out.println("Iterator with removal (remove failing students, grade < 5):");
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getGrade() < 5.0) {
                it.remove();           // safe way to remove during iteration!
            }
        }
        System.out.println("  After removal: " + students);

        // Way 3: forEach + lambda (Java 8+)
        System.out.println("forEach + lambda:");
        students.forEach(s -> System.out.println("  " + s.getName() + ": " + s.getGrade()));

        // Way 4: forEach + method reference
        System.out.println("forEach + method reference:");
        students.forEach(System.out::println);
    }


    // =========================================================================
    // 8. Comparable & Comparator
    // =========================================================================
    static void demoSorting() {
        List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Alice",   8.5),
            new Student(2, "Bob",     9.2),
            new Student(3, "Charlie", 7.0),
            new Student(4, "Diana",   9.2)
        ));

        // Comparable — natural ordering (by grade, defined in compareTo)
        Collections.sort(students);
        System.out.println("Sorted by grade (Comparable - natural order):");
        students.forEach(s -> System.out.println("  " + s));

        // Comparator — sort by name instead
        students.sort(Comparator.comparing(Student::getName));
        System.out.println("Sorted by name (Comparator):");
        students.forEach(s -> System.out.println("  " + s));

        // Chained Comparator — by grade descending, then by name ascending
        students.sort(
            Comparator.comparingDouble(Student::getGrade).reversed()
                      .thenComparing(Student::getName)
        );
        System.out.println("Sorted by grade DESC, then name ASC:");
        students.forEach(s -> System.out.println("  " + s));
    }


    // =========================================================================
    // 9. Lambda Expressions
    // =========================================================================
    static void demoLambdas() {
        // Lambda = anonymous function: (parameters) -> expression

        List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Alice",   9.5),
            new Student(2, "Bob",     4.0),
            new Student(3, "Charlie", 8.0),
            new Student(4, "Diana",   6.0)
        ));

        // Predicate<T> — takes T, returns boolean
        Predicate<Student> isPassing = s -> s.getGrade() >= 5.0;
        System.out.println("Alice passing? " + isPassing.test(students.get(0)));  // true
        System.out.println("Bob passing?   " + isPassing.test(students.get(1)));  // false

        // Function<T, R> — takes T, returns R (transform Student -> their name)
        Function<Student, String> toName = s -> s.getName();
        System.out.println("Name of student 3: " + toName.apply(students.get(2)));

        // Consumer<T> — takes T, returns nothing (print a formatted student)
        Consumer<Student> printStudent = s ->
            System.out.println("  " + s.getName() + " scored " + s.getGrade());
        System.out.println("Consumer in action:");
        students.forEach(printStudent);

        // Supplier<T> — takes nothing, returns T (create a default student)
        Supplier<Student> defaultStudent = () -> new Student(0, "Unknown", 0.0);
        System.out.println("Default student: " + defaultStudent.get());

        // Comparator with lambda
        Comparator<Student> byGradeDesc = (a, b) -> Double.compare(b.getGrade(), a.getGrade());
        students.sort(byGradeDesc);
        System.out.println("Sorted by grade descending:");
        students.forEach(s -> System.out.println("  " + s));
    }


    // =========================================================================
    // 10. Streams API
    // =========================================================================
    static void demoStreams() {
        List<Student> students = List.of(
            new Student(1, "Alice",   9.5),
            new Student(2, "Bob",     4.0),
            new Student(3, "Charlie", 8.0),
            new Student(4, "Diana",   3.5),
            new Student(5, "Eve",     7.0),
            new Student(6, "Frank",   9.5)
        );

        // Filter + Map + Sort + Collect — get names of passing students, sorted
        List<String> passingNames = students.stream()
            .filter(s -> s.getGrade() >= 5.0)          // keep passing students
            .map(Student::getName)                      // extract name
            .sorted()                                   // sort alphabetically
            .collect(Collectors.toList());              // collect into a list
        System.out.println("Passing student names (sorted): " + passingNames);

        // Count
        long failCount = students.stream()
            .filter(s -> s.getGrade() < 5.0)
            .count();
        System.out.println("Number of failing students: " + failCount);

        // Numeric stream — average grade of passing students
        double avgPassing = students.stream()
            .filter(s -> s.getGrade() >= 5.0)
            .mapToDouble(Student::getGrade)
            .average()
            .orElse(0.0);
        System.out.println("Average grade (passing only): " + avgPassing);

        // Grouping — group students by pass/fail
        Map<Boolean, List<Student>> passFailGroups = students.stream()
            .collect(Collectors.partitioningBy(s -> s.getGrade() >= 5.0));
        System.out.println("Passing: " + passFailGroups.get(true));
        System.out.println("Failing: " + passFailGroups.get(false));

        // Min and Max
        Optional<Student> topStudent = students.stream()
            .max(Comparator.comparingDouble(Student::getGrade));
        topStudent.ifPresent(s -> System.out.println("Top student: " + s));

        // Collecting to a Map (id -> Student)
        Map<Integer, Student> studentMap = students.stream()
            .collect(Collectors.toMap(Student::getId, Function.identity()));
        System.out.println("Student with id 3: " + studentMap.get(3));
    }


    // =========================================================================
    // 11. Best Practices
    // =========================================================================
    static void demoBestPractices() {
        // 1. Program to interfaces (use List, not ArrayList on the left side)
        List<Student> list = new ArrayList<>();  // GOOD
        // ArrayList<Student> list = new ArrayList<>();  // AVOID

        // 2. Immutable collections — data that should not change
        List<Student> immutable = List.of(
            new Student(1, "Alice", 9.0),
            new Student(2, "Bob",   8.0)
        );
        // immutable.add(new Student(3, "Charlie", 7.0));  // throws UnsupportedOperationException!
        System.out.println("Immutable list size: " + immutable.size());

        // 3. removeIf — safe way to remove elements (instead of modifying during loop)
        List<Student> mutable = new ArrayList<>(List.of(
            new Student(1, "Alice",   9.0),
            new Student(2, "Bob",     4.0),
            new Student(3, "Charlie", 3.5),
            new Student(4, "Diana",   8.0)
        ));
        mutable.removeIf(s -> s.getGrade() < 5.0);  // remove all failing students
        System.out.println("After removeIf (grade < 5): " + mutable);

        // 4. Always use generics — never use raw types
        // List rawList = new ArrayList();              // RAW TYPE — no type safety!
        List<Student> safeList = new ArrayList<>();     // GENERIC — type safe

        System.out.println("\nKey rules to remember:");
        System.out.println("  - Always override equals() AND hashCode() together");
        System.out.println("  - Always use generics — never raw types");
        System.out.println("  - Program to interfaces (List, Set, Map — not implementations)");
        System.out.println("  - Don't modify a collection during for-each — use Iterator or removeIf");
        System.out.println("  - Use List.of() / Set.of() for immutable collections");
    }


    // =========================================================================
    // Helper
    // =========================================================================
    static void separator(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + title);
        System.out.println("=".repeat(60));
    }
}
