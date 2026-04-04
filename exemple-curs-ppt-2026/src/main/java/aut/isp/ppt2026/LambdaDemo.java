package aut.isp.ppt2026;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * LAMBDA EXPRESSIONS & FUNCTIONAL PROGRAMMING DEMO
 *
 * Topics:
 * 1. Lambda basics: anonymous functions, before/after comparison
 * 2. Lambda syntax: all forms
 * 3. Functional interfaces: @FunctionalInterface, custom aut.isp.ppt2026.Calculator
 * 4. Built-in functional interfaces: Function, Predicate, Consumer, Supplier, UnaryOperator, BinaryOperator
 * 5. Function composition: andThen(), compose(), Predicate logic
 * 6. Variable capture: effectively final, workarounds
 * 7. Method references: 4 types
 * 8. Lambda vs Anonymous class: when to use each
 */
public class LambdaDemo {
    public static void main(String[] args) {
        System.out.println("=== LAMBDA EXPRESSIONS & FUNCTIONAL PROGRAMMING ===\n");

        // Sample data
        Student s1 = new Student(1, "Alice", 85);
        Student s2 = new Student(2, "Bob", 72);
        Student s3 = new Student(3, "Charlie", 95);
        Student s4 = new Student(4, "Diana", 65);
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        // ============================================================
        // 1. LAMBDA BASICS: Anonymous Class vs Lambda
        // ============================================================
        System.out.println("1. LAMBDA BASICS: Anonymous Class vs Lambda\n");

        // BEFORE: Using anonymous class
        aut.isp.ppt2026.Calculator anonymousCalc = new aut.isp.ppt2026.Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        System.out.println("Anonymous class (add): " + anonymousCalc.calculate(10, 5));

        // AFTER: Using lambda
        aut.isp.ppt2026.Calculator lambdaCalc = (a, b) -> a + b;
        System.out.println("Lambda (add): " + lambdaCalc.calculate(10, 5));
        System.out.println();

        // ============================================================
        // 2. LAMBDA SYNTAX: All Forms
        // ============================================================
        System.out.println("2. LAMBDA SYNTAX: All Forms\n");

        // Form 1: () -> expr (no parameters, single expression)
        Supplier<String> greeting = () -> "Hello from Lambda!";
        System.out.println("Form 1 [() -> expr]: " + greeting.get());

        // Form 2: x -> expr (single parameter, no parentheses, single expression)
        UnaryOperator<Integer> square = x -> x * x;
        System.out.println("Form 2 [x -> expr]: square(5) = " + square.apply(5));

        // Form 3: (x, y) -> expr (multiple parameters, single expression)
        aut.isp.ppt2026.Calculator multiply = (a, b) -> a * b;
        System.out.println("Form 3 [(x, y) -> expr]: multiply(4, 3) = " + multiply.calculate(4, 3));

        // Form 4: (x, y) -> { block; return r; } (block with multiple statements)
        aut.isp.ppt2026.Calculator divide = (a, b) -> {
            if (b == 0) {
                System.out.println("  [Warning: division by zero, returning 0]");
                return 0;
            }
            return a / b;
        };
        System.out.println("Form 4 [(x, y) -> { ... }]: divide(20, 4) = " + divide.calculate(20, 4));
        System.out.println();

        // ============================================================
        // 3. FUNCTIONAL INTERFACES: @FunctionalInterface & Custom aut.isp.ppt2026.Calculator
        // ============================================================
        System.out.println("3. FUNCTIONAL INTERFACES: @FunctionalInterface\n");
        System.out.println("aut.isp.ppt2026.Calculator is a custom @FunctionalInterface with single abstract method: calculate(int, int)\n");

        // We've already shown aut.isp.ppt2026.Calculator above with lambda syntax
        aut.isp.ppt2026.Calculator subtract = (a, b) -> a - b;
        System.out.println("Custom aut.isp.ppt2026.Calculator example (subtract): " + subtract.calculate(10, 3));
        System.out.println();

        // ============================================================
        // 4. BUILT-IN FUNCTIONAL INTERFACES (with aut.isp.ppt2026.Student objects)
        // ============================================================
        System.out.println("4. BUILT-IN FUNCTIONAL INTERFACES (with aut.isp.ppt2026.Student objects)\n");

        // 4a. Function<T, R>: Transform/map values
        System.out.println("4a. Function<T, R>: Extract grade from aut.isp.ppt2026.Student");
        Function<Student, Integer> getGrade = student -> student.getGrade();
        System.out.println("  Alice's grade: " + getGrade.apply(s1));
        System.out.println("  Bob's grade: " + getGrade.apply(s2));

        // 4b. Predicate<T>: Test a condition
        System.out.println("\n4b. Predicate<T>: Test if student is passing (grade >= 70)");
        Predicate<Student> isPassing = student -> student.getGrade() >= 70;
        for (Student s : students) {
            System.out.println("  " + s.getName() + " passing? " + isPassing.test(s));
        }

        // 4c. Consumer<T>: Process/print values (no return)
        System.out.println("\n4c. Consumer<T>: Print student info");
        Consumer<Student> printStudent = student ->
            System.out.println("  ID: " + student.getId() + ", Name: " + student.getName() + ", Grade: " + student.getGrade());
        students.forEach(printStudent);

        // 4d. Supplier<T>: Provide/create values
        System.out.println("\n4d. Supplier<T>: Create default student");
        Supplier<Student> defaultStudent = () -> new Student(0, "Default", 70);
        Student def = defaultStudent.get();
        System.out.println("  Default: ID=" + def.getId() + ", Name=" + def.getName() + ", Grade=" + def.getGrade());

        // 4e. UnaryOperator<T>: Transform a single value (returns same type)
        System.out.println("\n4e. UnaryOperator<T>: Add bonus to student grade");
        UnaryOperator<Student> addBonus = student -> {
            student.setGrade(student.getGrade() + 5);
            return student;
        };
        Student bonusStudent = new Student(5, "Eve", 80);
        System.out.println("  Before bonus: " + bonusStudent.getGrade());
        addBonus.apply(bonusStudent);
        System.out.println("  After bonus: " + bonusStudent.getGrade());

        // 4f. BinaryOperator<T>: Combine two values of same type
        System.out.println("\n4f. BinaryOperator<T>: Average two grades");
        BinaryOperator<Integer> averageGrades = (g1, g2) -> (g1 + g2) / 2;
        int avg = averageGrades.apply(s1.getGrade(), s2.getGrade());
        System.out.println("  Average of Alice(" + s1.getGrade() + ") and Bob(" + s2.getGrade() + "): " + avg);
        System.out.println();

        // ============================================================
        // 5. FUNCTION COMPOSITION: andThen(), compose(), Predicate logic
        // ============================================================
        System.out.println("5. FUNCTION COMPOSITION: andThen(), compose(), Predicate logic\n");

        // andThen: First function -> output becomes input to second function
        System.out.println("5a. Function andThen():");
        Function<Student, Integer> extractGrade = student -> student.getGrade();
        Function<Integer, String> gradeToLetter = grade -> {
            if (grade >= 90) return "A";
            if (grade >= 80) return "B";
            if (grade >= 70) return "C";
            return "F";
        };
        Function<Student, String> getLetterGrade = extractGrade.andThen(gradeToLetter);
        System.out.println("  Alice (grade " + s1.getGrade() + ") -> " + getLetterGrade.apply(s1));
        System.out.println("  Bob (grade " + s2.getGrade() + ") -> " + getLetterGrade.apply(s2));

        // compose: Reverse order - second function input, then output to first
        System.out.println("\n5b. Function compose():");
        Function<Integer, Integer> addTen = x -> x + 10;
        Function<Integer, Integer> multiplyByTwo = x -> x * 2;
        Function<Integer, Integer> composedFunc = addTen.compose(multiplyByTwo);
        // composedFunc: multiplyByTwo first, then addTen
        // compose((x * 2) + 10)
        int result = composedFunc.apply(5); // (5 * 2) + 10 = 20
        System.out.println("  compose: addTen.compose(multiplyByTwo).apply(5) = " + result);

        // Predicate logic: and(), or(), negate()
        System.out.println("\n5c. Predicate and(), or(), negate():");
        Predicate<Student> highGrade = s -> s.getGrade() >= 85;
        Predicate<Student> nameStartsWithC = s -> s.getName().startsWith("C");

        Predicate<Student> highAndNameC = highGrade.and(nameStartsWithC);
        Predicate<Student> highOrNameC = highGrade.or(nameStartsWithC);
        Predicate<Student> notHigh = highGrade.negate();

        System.out.println("  Students with high grade (>=85) AND name starts with 'C':");
        students.stream().filter(highAndNameC).forEach(s -> System.out.println("    - " + s.getName()));

        System.out.println("  Students with high grade (>=85) OR name starts with 'C':");
        students.stream().filter(highOrNameC).forEach(s -> System.out.println("    - " + s.getName()));

        System.out.println("  Students NOT with high grade:");
        students.stream().filter(notHigh).forEach(s -> System.out.println("    - " + s.getName()));
        System.out.println();

        // ============================================================
        // 6. VARIABLE CAPTURE: Effectively Final & Workarounds
        // ============================================================
        System.out.println("6. VARIABLE CAPTURE: Effectively Final & Workarounds\n");

        // Effectively final: variable not modified after initialization
        System.out.println("6a. Effectively Final (works):");
        int bonusPoints = 10; // Effectively final (not reassigned)
        Function<Student, Integer> applyBonus = student -> student.getGrade() + bonusPoints;
        System.out.println("  Alice grade + " + bonusPoints + ": " + applyBonus.apply(s1));

        // Problem: non-effectively final variable
        System.out.println("\n6b. Non-effectively final variable (problem):");
        System.out.println("  // int counter = 0;");
        System.out.println("  // counter++;  // ERROR: can't use in lambda!");
        System.out.println("  // Consumer<String> c = x -> System.out.println(counter);");

        // Workaround 1: Use effectively final variable
        System.out.println("\n6c. Workaround 1: Effectively final variable");
        final int finalCounter = 0;
        Consumer<String> printWithCounter = x -> System.out.println("  Counter is: " + finalCounter);
        printWithCounter.accept("test");

        // Workaround 2: AtomicInteger (mutable wrapper)
        System.out.println("\n6d. Workaround 2: AtomicInteger (mutable wrapper)");
        AtomicInteger counter = new AtomicInteger(0);
        Consumer<String> incrementCounter = x -> counter.incrementAndGet();
        incrementCounter.accept("increment");
        incrementCounter.accept("increment");
        System.out.println("  Counter after 2 increments: " + counter.get());

        // Workaround 3: Array (mutable container)
        System.out.println("\n6e. Workaround 3: Array (mutable container)");
        int[] arr = {0};
        Consumer<String> arrayIncrement = x -> arr[0]++;
        arrayIncrement.accept("increment");
        arrayIncrement.accept("increment");
        System.out.println("  Array element after 2 increments: " + arr[0]);
        System.out.println();

        // ============================================================
        // 7. METHOD REFERENCES: 4 Types
        // ============================================================
        System.out.println("7. METHOD REFERENCES: 4 Types\n");

        // Type 1: Class::staticMethod
        System.out.println("7a. Class::staticMethod");
        Function<String, Integer> parseInt = Integer::parseInt;
        System.out.println("  Integer::parseInt(\"42\") = " + parseInt.apply("42"));

        // Type 2: instance::instanceMethod
        System.out.println("\n7b. instance::instanceMethod");
        Student alice = new Student(1, "Alice", 85);
        Supplier<Integer> getAliceGrade = alice::getGrade;
        System.out.println("  alice::getGrade() = " + getAliceGrade.get());

        // Type 3: Class::instanceMethod (parameter becomes this)
        System.out.println("\n7c. Class::instanceMethod");
        Function<Student, Integer> getStudentGrade = Student::getGrade;
        System.out.println("  aut.isp.ppt2026.Student::getGrade(alice) = " + getStudentGrade.apply(alice));

        // Type 4: Class::new (constructor reference)
        System.out.println("\n7d. Class::new (constructor reference)");
        Supplier<Student> newStudent = Student::new;
        Student created = newStudent.get();
        System.out.println("  aut.isp.ppt2026.Student::new() created: ID=" + created.getId() + ", Name=" + created.getName() + ", Grade=" + created.getGrade());
        System.out.println();

        // ============================================================
        // 8. LAMBDA vs ANONYMOUS CLASS: When to use each
        // ============================================================
        System.out.println("8. LAMBDA vs ANONYMOUS CLASS: When to use each\n");

        System.out.println("LAMBDA EXPRESSION - Use when:");
        System.out.println("  - Functional interface (single abstract method)");
        System.out.println("  - Brief, simple implementation");
        System.out.println("  - Logic fits in one line or a few lines");
        System.out.println("  - Better readability for short operations");
        System.out.println();

        System.out.println("ANONYMOUS CLASS - Use when:");
        System.out.println("  - Multiple methods to override");
        System.out.println("  - Complex logic with many statements");
        System.out.println("  - Need instance variables/state");
        System.out.println("  - Implementing non-functional interfaces");
        System.out.println();

        // Example: Lambda for simple operation
        System.out.println("Lambda example (simple):");
        List<Student> passing = new ArrayList<>();
        students.forEach(s -> {
            if (s.getGrade() >= 70) passing.add(s);
        });
        System.out.println("  Passing students: " + passing.size());

        // Example: Anonymous class for complex logic
        System.out.println("\nAnonymous class example (complex - multiple methods):");
        StudentProcessor processor = new StudentProcessor() {
            @Override
            public void process(Student student) {
                System.out.println("  Processing: " + student.getName());
            }

            @Override
            public String getReport() {
                return "aut.isp.ppt2026.Student processing complete";
            }
        };
        processor.process(s1);
        System.out.println("  " + processor.getReport());
        System.out.println();

        System.out.println("=== END OF DEMO ===");
    }
}

/**
 * CUSTOM FUNCTIONAL INTERFACE
 * Single abstract method (SAM) - key requirement for @FunctionalInterface
 */
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

/**
 * STUDENT CLASS - Used throughout the demo
 */
class Student {
    private int id;
    private String name;
    private int grade;

    // Constructors
    public Student() {
        this.id = -1;
        this.name = "Unknown";
        this.grade = 0;
    }

    public Student(int id, String name, int grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }

    // Setters
    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Student{" + "id=" + id + ", name='" + name + '\'' + ", grade=" + grade + '}';
    }
}

/**
 * EXAMPLE INTERFACE FOR ANONYMOUS CLASS DEMO
 * Shows when NOT to use lambda (multiple methods)
 */
interface StudentProcessor {
    void process(Student student);
    String getReport();
}
