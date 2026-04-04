package aut.isp.ppt2026;

import java.util.*;
import java.util.stream.*;
import java.util.stream.Collectors;

/**
 * Java Streams API Demo - Functional Data Processing in Java 8+
 * A minimal demo covering core concepts and practical examples for students.
 */
public class StreamsApiDemo {
    public static void main(String[] args) {
        System.out.println("=== JAVA STREAMS API DEMO ===\n");

        // Create sample data
        List<Student> students = createStudents();
        List<Product> products = createProducts();

        // Topic 1: What is a Stream?
        demo1_WhatIsStream(students);

        // Topic 2: Stream vs Collection
        demo2_StreamVsCollection(students);

        // Topic 3: Creating Streams
        demo3_CreatingStreams(students);

        // Topic 4: Intermediate Operations
        demo4_IntermediateOperations(students, products);

        // Topic 5: Terminal Operations
        demo5_TerminalOperations(students);

        // Topic 6: Collectors
        demo6_Collectors(students, products);

        // Topic 7: Primitive Streams
        demo7_PrimitiveStreams(students);

        // Topic 8: Parallel Streams
        demo8_ParallelStreams(students);

        // Topic 9: Practical Examples
        demo9_PracticalExamples(students, products);
    }

    // =====================================================================
    // TOPIC 1: What is a Stream?
    // =====================================================================
    private static void demo1_WhatIsStream(List<Student> students) {
        System.out.println("\n--- TOPIC 1: What is a Stream? ---");
        System.out.println("A Stream is NOT a data structure. It's a pipeline for processing data.");
        System.out.println("Key characteristics:");
        System.out.println("  • Pipeline model: data flows through operations");
        System.out.println("  • Lazy evaluation: operations don't execute until a terminal op is called");
        System.out.println("  • Single-use: once consumed, a stream cannot be reused");
        System.out.println("  • Functional style: uses lambdas and method references\n");

        // Example: lazy evaluation
        System.out.println("Lazy Evaluation Example:");
        Stream<Student> stream = students.stream()
            .filter(s -> {
                System.out.println("  [filter] checking: " + s.getName());
                return s.getGrade() >= 75;
            })
            .limit(2);
        System.out.println("Stream created, but filter not executed yet (lazy)");
        System.out.println("Now calling .forEach() to trigger execution:");
        stream.forEach(s -> System.out.println("  -> " + s.getName() + " (" + s.getGrade() + ")"));

        // Example: single-use
        System.out.println("\nSingle-Use Example:");
        Stream<Student> singleUseStream = students.stream();
        singleUseStream.forEach(s -> System.out.println("  First use: " + s.getName()));
        try {
            singleUseStream.forEach(s -> System.out.println("  Second use: " + s.getName()));
        } catch (IllegalStateException e) {
            System.out.println("  ERROR: Cannot reuse stream! - " + e.getMessage());
        }
    }

    // =====================================================================
    // TOPIC 2: Stream vs Collection
    // =====================================================================
    private static void demo2_StreamVsCollection(List<Student> students) {
        System.out.println("\n--- TOPIC 2: Stream vs Collection ---");
        System.out.println("COLLECTION:");
        System.out.println("  • Stores data in memory (in-memory structure)");
        System.out.println("  • External iteration (you control the loop)");
        System.out.println("  • Can be reused multiple times");
        System.out.println("  • Examples: List, Set, Map\n");

        System.out.println("STREAM:");
        System.out.println("  • Processes data on-the-fly (doesn't store all data at once)");
        System.out.println("  • Internal iteration (stream controls the loop)");
        System.out.println("  • Single-use (consumed after terminal operation)");
        System.out.println("  • Can be lazy (elements generated on demand)\n");

        // Comparison Example
        System.out.println("Example: Get names of students with grade >= 75");
        System.out.println("Using Collection (external iteration):");
        List<String> namesCollection = new ArrayList<>();
        for (Student s : students) {  // You control the loop
            if (s.getGrade() >= 75) {
                namesCollection.add(s.getName());
            }
        }
        System.out.println("  Result: " + namesCollection);

        System.out.println("Using Stream (internal iteration):");
        List<String> namesStream = students.stream()
            .filter(s -> s.getGrade() >= 75)
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println("  Result: " + namesStream);
    }

    // =====================================================================
    // TOPIC 3: Creating Streams
    // =====================================================================
    private static void demo3_CreatingStreams(List<Student> students) {
        System.out.println("\n--- TOPIC 3: Creating Streams ---");

        System.out.println("1. From Collection (.stream()):");
        students.stream()
            .limit(2)
            .forEach(s -> System.out.println("  " + s.getName()));

        System.out.println("\n2. From Array (Arrays.stream()):");
        int[] grades = {85, 92, 78, 88};
        Arrays.stream(grades).forEach(g -> System.out.println("  " + g));

        System.out.println("\n3. Stream.of() - create from values:");
        Stream.of("Alice", "Bob", "Charlie")
            .forEach(name -> System.out.println("  " + name));

        System.out.println("\n4. Stream.generate() - infinite stream (lazy generation):");
        System.out.println("  (showing first 5 random numbers)");
        Stream.generate(Math::random)
            .limit(5)
            .forEach(num -> System.out.println("  " + String.format("%.2f", num)));

        System.out.println("\n5. Stream.iterate() - sequential stream:");
        System.out.println("  (starting from 0, add 2 each time, limit to 5)");
        Stream.iterate(0, n -> n + 2)
            .limit(5)
            .forEach(n -> System.out.println("  " + n));

        System.out.println("\n6. Stream.empty() - empty stream:");
        System.out.println("  Count of empty stream: " + Stream.empty().count());
    }

    // =====================================================================
    // TOPIC 4: Intermediate Operations (lazy, return new stream)
    // =====================================================================
    private static void demo4_IntermediateOperations(List<Student> students, List<Product> products) {
        System.out.println("\n--- TOPIC 4: Intermediate Operations (Lazy) ---");

        System.out.println("1. filter(Predicate) - keep elements matching condition:");
        System.out.println("  Students with grade >= 80:");
        students.stream()
            .filter(s -> s.getGrade() >= 80)
            .forEach(s -> System.out.println("    " + s.getName() + " (" + s.getGrade() + ")"));

        System.out.println("\n2. map(Function) - transform each element:");
        System.out.println("  aut.isp.ppt2026.Student names (uppercase):");
        students.stream()
            .map(Student::getName)
            .map(String::toUpperCase)
            .forEach(name -> System.out.println("    " + name));

        System.out.println("\n3. flatMap(Function) - map then flatten:");
        System.out.println("  (Each student mapped to list of words in name, then flattened)");
        students.stream()
            .limit(2)
            .flatMap(s -> Stream.of(s.getName().split("")))
            .distinct()
            .forEach(letter -> System.out.print(letter + " "));
        System.out.println();

        System.out.println("\n4. sorted() - sort by natural order:");
        System.out.println("  Students by name:");
        students.stream()
            .sorted(Comparator.comparing(Student::getName))
            .forEach(s -> System.out.println("    " + s.getName()));

        System.out.println("\n5. sorted(Comparator) - custom sort:");
        System.out.println("  Students by grade (descending):");
        students.stream()
            .sorted(Comparator.comparingDouble(Student::getGrade).reversed())
            .forEach(s -> System.out.println("    " + s.getName() + " (" + s.getGrade() + ")"));

        System.out.println("\n6. distinct() - remove duplicates:");
        System.out.println("  Unique categories from products:");
        products.stream()
            .map(Product::getCategory)
            .distinct()
            .forEach(cat -> System.out.println("    " + cat));

        System.out.println("\n7. limit(n) - take first n elements:");
        System.out.println("  First 3 products:");
        products.stream()
            .limit(3)
            .forEach(p -> System.out.println("    " + p.getName()));

        System.out.println("\n8. skip(n) - skip first n elements:");
        System.out.println("  Skip first 2, show next 2:");
        products.stream()
            .skip(2)
            .limit(2)
            .forEach(p -> System.out.println("    " + p.getName()));

        System.out.println("\n9. peek(Consumer) - debug/inspect intermediate values:");
        System.out.println("  Processing with peek():");
        students.stream()
            .filter(s -> s.getGrade() >= 75)
            .peek(s -> System.out.println("    [peek] filtered: " + s.getName()))
            .map(Student::getName)
            .forEach(name -> System.out.println("    [final] " + name));

        System.out.println("\n10. mapToInt/mapToDouble (primitive streams):");
        System.out.println("  Sum of all grades: " +
            students.stream()
                .mapToDouble(Student::getGrade)
                .sum());
    }

    // =====================================================================
    // TOPIC 5: Terminal Operations (trigger execution)
    // =====================================================================
    private static void demo5_TerminalOperations(List<Student> students) {
        System.out.println("\n--- TOPIC 5: Terminal Operations (Trigger Execution) ---");

        System.out.println("1. forEach(Consumer) - perform action on each element:");
        System.out.println("  Processing students:");
        students.stream()
            .limit(2)
            .forEach(s -> System.out.println("    " + s.getName() + ": " + s.getGrade()));

        System.out.println("\n2. count() - count elements:");
        long count = students.stream()
            .filter(s -> s.getGrade() >= 75)
            .count();
        System.out.println("  Students with grade >= 75: " + count);

        System.out.println("\n3. min() - find minimum:");
        OptionalDouble minGrade = students.stream()
            .mapToDouble(Student::getGrade)
            .min();
        System.out.println("  Minimum grade: " + (minGrade.isPresent() ? minGrade.getAsDouble() : "N/A"));

        System.out.println("\n4. max() - find maximum:");
        OptionalDouble maxGrade = students.stream()
            .mapToDouble(Student::getGrade)
            .max();
        System.out.println("  Maximum grade: " + (maxGrade.isPresent() ? maxGrade.getAsDouble() : "N/A"));

        System.out.println("\n5. reduce() - combine elements to single value:");
        System.out.println("  Sum of all grades (with identity 0):");
        double sum = students.stream()
            .mapToDouble(Student::getGrade)
            .reduce(0, Double::sum);
        System.out.println("    " + sum);

        System.out.println("  aut.isp.ppt2026.Product of grades 70,80,90 (no identity):");
        OptionalDouble product = Stream.of(70.0, 80.0, 90.0)
            .reduce((a, b) -> a * b);
        System.out.println("    " + (product.isPresent() ? product.getAsDouble() : "N/A"));

        System.out.println("\n6. anyMatch(Predicate) - is any element matching?");
        boolean anyHighGrade = students.stream()
            .anyMatch(s -> s.getGrade() >= 95);
        System.out.println("  Any student with grade >= 95? " + anyHighGrade);

        System.out.println("\n7. allMatch(Predicate) - are all elements matching?");
        boolean allPassed = students.stream()
            .allMatch(s -> s.getGrade() >= 50);
        System.out.println("  All students passed (>= 50)? " + allPassed);

        System.out.println("\n8. noneMatch(Predicate) - is no element matching?");
        boolean noneFailed = students.stream()
            .noneMatch(s -> s.getGrade() < 50);
        System.out.println("  No student failed (< 50)? " + noneFailed);

        System.out.println("\n9. findFirst() - get first element:");
        Optional<Student> first = students.stream()
            .filter(s -> s.getGrade() >= 85)
            .findFirst();
        System.out.println("  First student with grade >= 85: " +
            (first.isPresent() ? first.get().getName() : "N/A"));

        System.out.println("\n10. findAny() - get any element (useful for parallel):");
        Optional<Student> any = students.stream()
            .filter(s -> s.getGrade() >= 75)
            .findAny();
        System.out.println("  Any student with grade >= 75: " +
            (any.isPresent() ? any.get().getName() : "N/A"));
    }

    // =====================================================================
    // TOPIC 6: Collectors
    // =====================================================================
    private static void demo6_Collectors(List<Student> students, List<Product> products) {
        System.out.println("\n--- TOPIC 6: Collectors ---");

        System.out.println("1. toList() - collect to List:");
        List<String> names = students.stream()
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println("  " + names);

        System.out.println("\n2. toSet() - collect to Set (removes duplicates):");
        Set<String> categories = products.stream()
            .map(Product::getCategory)
            .collect(Collectors.toSet());
        System.out.println("  " + categories);

        System.out.println("\n3. toMap() - collect to Map:");
        Map<Integer, String> idToName = students.stream()
            .collect(Collectors.toMap(Student::getId, Student::getName));
        System.out.println("  " + idToName);

        System.out.println("\n4. joining() - join elements as String:");
        String studentList = students.stream()
            .map(Student::getName)
            .collect(Collectors.joining(", "));
        System.out.println("  " + studentList);

        System.out.println("\n5. groupingBy() - group by criteria:");
        System.out.println("  Students grouped by pass/fail:");
        Map<String, List<Student>> byPassFail = students.stream()
            .collect(Collectors.groupingBy(s -> s.getGrade() >= 75 ? "PASS" : "FAIL"));
        byPassFail.forEach((status, list) ->
            System.out.println("    " + status + ": " + list.stream()
                .map(Student::getName).collect(Collectors.joining(", ")))
        );

        System.out.println("\n6. partitioningBy() - split into two groups (true/false):");
        System.out.println("  Products by high/low price (>= 15):");
        Map<Boolean, List<Product>> byPrice = products.stream()
            .collect(Collectors.partitioningBy(p -> p.getPrice() >= 15));
        System.out.println("    Expensive: " + byPrice.get(true).size() + " products");
        System.out.println("    Cheap: " + byPrice.get(false).size() + " products");

        System.out.println("\n7. counting() - count elements in group:");
        System.out.println("  Products count by category:");
        Map<String, Long> countByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        countByCategory.forEach((cat, count) ->
            System.out.println("    " + cat + ": " + count)
        );

        System.out.println("\n8. summarizingDouble() - statistics:");
        DoubleSummaryStatistics gradeStats = students.stream()
            .collect(Collectors.summarizingDouble(Student::getGrade));
        System.out.println("  Grade statistics:");
        System.out.println("    Count: " + gradeStats.getCount());
        System.out.println("    Sum: " + gradeStats.getSum());
        System.out.println("    Average: " + gradeStats.getAverage());
        System.out.println("    Min: " + gradeStats.getMin());
        System.out.println("    Max: " + gradeStats.getMax());

        System.out.println("\n9. averagingDouble() - calculate average:");
        double avgGrade = students.stream()
            .collect(Collectors.averagingDouble(Student::getGrade));
        System.out.println("  Average grade: " + avgGrade);
    }

    // =====================================================================
    // TOPIC 7: Primitive Streams
    // =====================================================================
    private static void demo7_PrimitiveStreams(List<Student> students) {
        System.out.println("\n--- TOPIC 7: Primitive Streams (IntStream, DoubleStream) ---");

        System.out.println("1. Creating IntStream:");
        System.out.println("  Range 1-5:");
        IntStream.rangeClosed(1, 5)
            .forEach(i -> System.out.print("  " + i));
        System.out.println();

        System.out.println("\n2. DoubleStream sum():");
        double totalGrade = students.stream()
            .mapToDouble(Student::getGrade)
            .sum();
        System.out.println("  Total of all grades: " + totalGrade);

        System.out.println("\n3. DoubleStream average():");
        OptionalDouble avg = students.stream()
            .mapToDouble(Student::getGrade)
            .average();
        System.out.println("  Average grade: " + (avg.isPresent() ? avg.getAsDouble() : "N/A"));

        System.out.println("\n4. IntStream range() (exclusive end):");
        System.out.println("  Range 0-4:");
        IntStream.range(0, 5)
            .forEach(i -> System.out.print("  " + i));
        System.out.println();

        System.out.println("\n5. IntStream rangeClosed() (inclusive end):");
        System.out.println("  Range 0-4 (rangeClosed):");
        IntStream.rangeClosed(0, 4)
            .forEach(i -> System.out.print("  " + i));
        System.out.println();

        System.out.println("\n6. Primitive stream statistics:");
        IntSummaryStatistics idStats = students.stream()
            .mapToInt(Student::getId)
            .summaryStatistics();
        System.out.println("  aut.isp.ppt2026.Student ID statistics:");
        System.out.println("    Count: " + idStats.getCount());
        System.out.println("    Average: " + idStats.getAverage());
    }

    // =====================================================================
    // TOPIC 8: Parallel Streams
    // =====================================================================
    private static void demo8_ParallelStreams(List<Student> students) {
        System.out.println("\n--- TOPIC 8: Parallel Streams ---");
        System.out.println("Parallel streams use multiple threads for processing.");
        System.out.println("Use for large datasets; be careful with thread safety and ordering.\n");

        System.out.println("1. Sequential vs Parallel - counting:");
        long startSeq = System.nanoTime();
        long seqCount = students.stream()
            .filter(s -> s.getGrade() >= 75)
            .count();
        long seqTime = System.nanoTime() - startSeq;

        long startPar = System.nanoTime();
        long parCount = students.parallelStream()
            .filter(s -> s.getGrade() >= 75)
            .count();
        long parTime = System.nanoTime() - startPar;

        System.out.println("  Sequential: " + seqCount + " (time: " + seqTime + "ns)");
        System.out.println("  Parallel: " + parCount + " (time: " + parTime + "ns)");

        System.out.println("\n2. parallelStream() usage:");
        List<String> namesParallel = students.parallelStream()
            .filter(s -> s.getGrade() >= 80)
            .map(Student::getName)
            .collect(Collectors.toList());
        System.out.println("  Names of high performers: " + namesParallel);

        System.out.println("\n3. WARNING: Order may not be preserved in parallel:");
        System.out.println("  (Parallel streams may reorder elements)");
        students.parallelStream()
            .limit(3)
            .forEach(s -> System.out.print("  " + s.getName()));
        System.out.println();

        System.out.println("\n4. Use forEachOrdered() to preserve order in parallel:");
        students.parallelStream()
            .limit(3)
            .forEachOrdered(s -> System.out.print("  " + s.getName()));
        System.out.println();
    }

    // =====================================================================
    // TOPIC 9: Practical Examples
    // =====================================================================
    private static void demo9_PracticalExamples(List<Student> students, List<Product> products) {
        System.out.println("\n--- TOPIC 9: Practical Examples ---");

        System.out.println("1. Filter + Map + Collect (common pattern):");
        System.out.println("  Names of students with grade >= 80:");
        List<String> topStudents = students.stream()
            .filter(s -> s.getGrade() >= 80)
            .map(Student::getName)
            .sorted()
            .collect(Collectors.toList());
        System.out.println("    " + topStudents);

        System.out.println("\n2. Group students by pass/fail:");
        Map<String, List<Student>> passFail = students.stream()
            .collect(Collectors.groupingBy(s -> s.getGrade() >= 75 ? "PASS" : "FAIL"));
        passFail.forEach((status, list) -> {
            System.out.println("  " + status + ":");
            list.forEach(s -> System.out.println("    - " + s.getName() + " (" + s.getGrade() + ")"));
        });

        System.out.println("\n3. Average grade by grouping:");
        Map<String, Double> avgByStatus = students.stream()
            .collect(Collectors.groupingBy(
                s -> s.getGrade() >= 75 ? "PASS" : "FAIL",
                Collectors.averagingDouble(Student::getGrade)
            ));
        avgByStatus.forEach((status, avg) ->
            System.out.println("  " + status + " - Average: " + String.format("%.2f", avg))
        );

        System.out.println("\n4. Top 3 students by grade:");
        List<Student> top3 = students.stream()
            .sorted(Comparator.comparingDouble(Student::getGrade).reversed())
            .limit(3)
            .collect(Collectors.toList());
        System.out.println("  Top 3:");
        top3.forEach(s -> System.out.println("    " + s.getName() + " - " + s.getGrade()));

        System.out.println("\n5. Products by category and sorted by price:");
        Map<String, List<Product>> byCategory = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.collectingAndThen(
                    Collectors.toList(),
                    list -> list.stream()
                        .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                        .collect(Collectors.toList())
                )
            ));
        byCategory.forEach((cat, list) -> {
            System.out.println("  " + cat + ":");
            list.forEach(p -> System.out.println("    " + p.getName() + " - $" + p.getPrice()));
        });

        System.out.println("\n6. Count products per category:");
        Map<String, Long> countPerCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
        System.out.println("  " + countPerCategory);

        System.out.println("\n7. Find most expensive product per category:");
        Map<String, Optional<Product>> mostExpensive = products.stream()
            .collect(Collectors.groupingBy(
                Product::getCategory,
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
            ));
        mostExpensive.forEach((cat, product) -> {
            if (product.isPresent()) {
                System.out.println("  " + cat + ": " + product.get().getName() + " ($" + product.get().getPrice() + ")");
            }
        });

        System.out.println("\n8. Check if all students passed:");
        boolean allPassed = students.stream()
            .allMatch(s -> s.getGrade() >= 50);
        System.out.println("  All students passed (>= 50): " + allPassed);

        System.out.println("\n9. Find student with highest grade:");
        Optional<Student> highestGrade = students.stream()
            .max(Comparator.comparingDouble(Student::getGrade));
        if (highestGrade.isPresent()) {
            Student s = highestGrade.get();
            System.out.println("  Highest grade: " + s.getName() + " (" + s.getGrade() + ")");
        }

        System.out.println("\n10. Price range statistics for products:");
        DoubleSummaryStatistics priceStats = products.stream()
            .collect(Collectors.summarizingDouble(Product::getPrice));
        System.out.println("  Total products: " + priceStats.getCount());
        System.out.println("  Total value: $" + String.format("%.2f", priceStats.getSum()));
        System.out.println("  Average price: $" + String.format("%.2f", priceStats.getAverage()));
        System.out.println("  Min price: $" + String.format("%.2f", priceStats.getMin()));
        System.out.println("  Max price: $" + String.format("%.2f", priceStats.getMax()));
    }

    // =====================================================================
    // Helper Methods
    // =====================================================================
    private static List<Student> createStudents() {
        return Arrays.asList(
            new Student(1, "Alice", 92.5),
            new Student(2, "Bob", 78.0),
            new Student(3, "Charlie", 85.5),
            new Student(4, "Diana", 88.0),
            new Student(5, "Eve", 76.5),
            new Student(6, "Frank", 95.0),
            new Student(7, "Grace", 82.5),
            new Student(8, "Henry", 72.0)
        );
    }

    private static List<Product> createProducts() {
        return Arrays.asList(
            new Product("P001", "Laptop", 1200.00, "Electronics"),
            new Product("P002", "Mouse", 25.50, "Electronics"),
            new Product("P003", "Desk", 350.00, "Furniture"),
            new Product("P004", "Chair", 180.00, "Furniture"),
            new Product("P005", "Pen", 1.50, "Stationery"),
            new Product("P006", "Notebook", 8.99, "Stationery"),
            new Product("P007", "Monitor", 400.00, "Electronics"),
            new Product("P008", "Bookshelf", 250.00, "Furniture")
        );
    }
}

// =====================================================================
// aut.isp.ppt2026.Student Class - simple custom object for demos
// =====================================================================
class Student {
    private int id;
    private String name;
    private double grade;

    public Student(int id, String name, double grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return name + " (Grade: " + grade + ")";
    }
}

// =====================================================================
// aut.isp.ppt2026.Product Class - simple custom object for demos
// =====================================================================
class Product {
    private String code;
    private String name;
    private double price;
    private String category;

    public Product(String code, String name, double price, String category) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " ($" + price + ", " + category + ")";
    }
}
