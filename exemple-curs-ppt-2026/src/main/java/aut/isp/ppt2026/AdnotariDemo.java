package aut.isp.ppt2026;

import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;

/**
 * MINIMAL DEMO: Java Annotations (Adnotari)
 *
 * What are annotations?
 * - Metadata about code that doesn't directly affect code execution
 * - Three targets: compiler, build tools, runtime
 *
 * Topics covered:
 * 1. Built-in annotations (@Override, @Deprecated, @SuppressWarnings, @FunctionalInterface)
 * 2. Custom annotations with meta-annotations (@Retention, @Target, @Documented, @Inherited, @Repeatable)
 * 3. Annotation elements (types, default values)
 * 4. Reading annotations at runtime (reflection)
 * 5. Practical example: simple validation framework
 */
public class AdnotariDemo {

    public static void main(String[] args) {
        System.out.println("=== JAVA ANNOTATIONS DEMO ===\n");

        // Demo 1: Built-in annotations
        System.out.println("1. BUILT-IN ANNOTATIONS");
        demonstrateBuiltInAnnotations();

        // Demo 2: Custom annotations
        System.out.println("\n2. CUSTOM ANNOTATIONS & REFLECTION");
        demonstrateCustomAnnotations();

        // Demo 3: Practical validation framework
        System.out.println("\n3. PRACTICAL: VALIDATION FRAMEWORK");
        demonstrateValidationFramework();

        // Demo 4: Repeatable annotations
        System.out.println("\n4. REPEATABLE ANNOTATIONS");
        demonstrateRepeatableAnnotations();
    }

    // ======================== DEMO 1: BUILT-IN ANNOTATIONS ========================

    private static void demonstrateBuiltInAnnotations() {
        System.out.println("a) @Override: Compiler ensures method overrides parent");
        OldAPI oldAPI = new NewAPI();
        oldAPI.process();  // Uses overridden method

        System.out.println("\nb) @Deprecated: Marks old methods as obsolete");
        System.out.println("   aut.isp.ppt2026.OldAPI.oldMethod() is marked @Deprecated");
        System.out.println("   Compiler generates warning if you use it");

        System.out.println("\nc) @SuppressWarnings: Suppress compiler warnings");
        System.out.println("   Used when you know warning is safe to ignore");

        System.out.println("\nd) @FunctionalInterface: Ensures single abstract method");
        MyFunctionalInterface func = x -> x * 2;
        System.out.println("   Calculated: 5 * 2 = " + func.calculate(5));
    }

    // ======================== DEMO 2: CUSTOM ANNOTATIONS & REFLECTION ========================

    private static void demonstrateCustomAnnotations() {
        Class<?> clazz = Course.class;

        System.out.println("Reading @aut.isp.ppt2026.Info annotation from aut.isp.ppt2026.Course class:");
        if (clazz.isAnnotationPresent(Info.class)) {
            Info info = clazz.getAnnotation(Info.class);
            System.out.println("  Author: " + info.author());
            System.out.println("  Version: " + info.version());
            System.out.println("  Description: " + info.description());
        }

        System.out.println("\nReading @aut.isp.ppt2026.Autor annotations from aut.isp.ppt2026.Course methods:");
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Autor.class)) {
                Autor autor = method.getAnnotation(Autor.class);
                System.out.println("  Method " + method.getName() + " -> written by: " + autor.value());
            }
        }
    }

    // ======================== DEMO 3: VALIDATION FRAMEWORK ========================

    private static void demonstrateValidationFramework() {
        System.out.println("Validating aut.isp.ppt2026.Student object using reflection:");

        // Create valid student
        Student validStudent = new Student("Alice", 20, "alice@school.edu");
        System.out.println("\nValid aut.isp.ppt2026.Student: " + validStudent);
        System.out.println("Validation result: " + ValidationFramework.validate(validStudent));

        // Create invalid student (empty email)
        Student invalidStudent = new Student("Bob", 25, "");
        System.out.println("\nInvalid aut.isp.ppt2026.Student: " + invalidStudent);
        System.out.println("Validation result: " + ValidationFramework.validate(invalidStudent));

        // Create invalid student (out of range age)
        Student invalidAge = new Student("Charlie", -5, "charlie@school.edu");
        System.out.println("\nInvalid aut.isp.ppt2026.Student (negative age): " + invalidAge);
        System.out.println("Validation result: " + ValidationFramework.validate(invalidAge));
    }

    // ======================== DEMO 4: REPEATABLE ANNOTATIONS ========================

    private static void demonstrateRepeatableAnnotations() {
        System.out.println("Reading repeatable @aut.isp.ppt2026.License annotations from aut.isp.ppt2026.Book class:");

        Class<?> clazz = Book.class;
        Licenses licenses = clazz.getAnnotation(Licenses.class);

        if (licenses != null) {
            for (License license : licenses.value()) {
                System.out.println("  aut.isp.ppt2026.License: " + license.name() + " - " + license.year());
            }
        }
    }
}

// ======================== BUILT-IN ANNOTATIONS DEMO ========================

/**
 * Example of @Override - shows correct inheritance pattern
 */
class OldAPI {
    public void process() {
        System.out.println("   aut.isp.ppt2026.OldAPI.process()");
    }

    @Deprecated(since = "1.0", forRemoval = true)
    public void oldMethod() {
        System.out.println("   This method is deprecated!");
    }
}

class NewAPI extends OldAPI {
    @Override  // Compiler verifies this actually overrides
    public void process() {
        System.out.println("   aut.isp.ppt2026.NewAPI.process() - OVERRIDDEN");
    }
}

/**
 * Example of @FunctionalInterface - exactly one abstract method
 */
@FunctionalInterface
interface MyFunctionalInterface {
    int calculate(int x);
}

// ======================== CUSTOM ANNOTATIONS ========================

/**
 * META-ANNOTATION: @Retention - specifies where annotation is retained
 * - SOURCE: only in source code, discarded by compiler
 * - CLASS: in compiled .class files (default)
 * - RUNTIME: available to reflection at runtime
 *
 * META-ANNOTATION: @Target - specifies what elements can be annotated
 * - TYPE: classes, interfaces, enums
 * - FIELD: class fields
 * - METHOD: methods
 * - PARAMETER: method parameters
 * - CONSTRUCTOR: constructors
 * - LOCAL_VARIABLE: local variables
 *
 * META-ANNOTATION: @Documented - included in Javadoc
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Info {
    String author();
    String version();
    String description();
}

/**
 * Simple custom annotation for marking author
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Autor {
    String value();
}

/**
 * Validation annotation - marks fields that need validation
 * Uses meta-annotations:
 * - @Retention(RUNTIME) so reflection can read it
 * - @Target(FIELD) so it only applies to fields
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Validare {
    String pattern() default "";      // regex pattern
    boolean required() default false;  // must not be empty
    int minValue() default 0;          // minimum numeric value
    int maxValue() default Integer.MAX_VALUE;  // maximum numeric value
}

/**
 * Single repeatable annotation
 * This is the individual annotation
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface License {
    String name();
    int year();
}

/**
 * Container for repeatable annotations
 * Holds array of @aut.isp.ppt2026.License annotations
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Licenses {
    License[] value();
}

// ======================== EXAMPLE CLASSES WITH ANNOTATIONS ========================

/**
 * aut.isp.ppt2026.Course class with @aut.isp.ppt2026.Info annotation
 * Demonstrates: reading custom annotations via reflection
 */
@Info(
    author = "Professor Smith",
    version = "1.0",
    description = "Introduction to Java Programming"
)
class Course {
    private String name;
    private int credits;

    public Course(String name, int credits) {
        this.name = name;
        this.credits = credits;
    }

    @Autor("John Developer")
    public void lecture() {
        System.out.println("Teaching: " + name);
    }

    @Autor("Jane Instructor")
    public void assignHomework() {
        System.out.println("Homework assigned for: " + name);
    }
}

/**
 * aut.isp.ppt2026.Student class with @aut.isp.ppt2026.Validare annotations
 * Demonstrates: using annotations for validation
 *
 * Each field can have validation rules via @aut.isp.ppt2026.Validare
 */
class Student {
    @Validare(required = true)
    private String name;

    @Validare(minValue = 16, maxValue = 100)
    private int age;

    @Validare(required = true, pattern = ".*@.*\\..*")  // simple email check
    private String email;

    public Student(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Student{" + "name='" + name + '\'' + ", age=" + age + ", email='" + email + '\'' + '}';
    }
}

/**
 * aut.isp.ppt2026.Book class demonstrating repeatable annotations
 */
@License(name = "MIT", year = 2024)
@License(name = "Apache 2.0", year = 2024)
@License(name = "GPL", year = 2023)
class Book {
    private String title;

    public Book(String title) {
        this.title = title;
    }
}

// ======================== VALIDATION FRAMEWORK (Reflection-based) ========================

/**
 * Simple validation framework using reflection
 * Reads @aut.isp.ppt2026.Validare annotations at runtime and validates fields
 *
 * Demonstrates:
 * - Class.getDeclaredFields() - get all fields
 * - field.isAnnotationPresent() - check if annotated
 * - field.getAnnotation() - get annotation details
 * - field.get() - read field value via reflection
 */
class ValidationFramework {

    public static String validate(Student student) {
        Class<?> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();
        List<String> errors = new ArrayList<>();

        for (Field field : fields) {
            if (!field.isAnnotationPresent(Validare.class)) {
                continue;  // Skip fields without @aut.isp.ppt2026.Validare
            }

            Validare rule = field.getAnnotation(Validare.class);
            field.setAccessible(true);  // Access private fields

            try {
                Object value = field.get(student);

                // Check required
                if (rule.required()) {
                    if (value == null || value.toString().isEmpty()) {
                        errors.add(field.getName() + " is required");
                    }
                }

                // Check pattern (string matching)
                if (!rule.pattern().isEmpty() && value != null) {
                    if (!value.toString().matches(rule.pattern())) {
                        errors.add(field.getName() + " does not match pattern: " + rule.pattern());
                    }
                }

                // Check numeric range
                if (value instanceof Integer) {
                    int num = (Integer) value;
                    if (num < rule.minValue() || num > rule.maxValue()) {
                        errors.add(field.getName() + " must be between " + rule.minValue() +
                                   " and " + rule.maxValue() + ", got " + num);
                    }
                }

            } catch (IllegalAccessException e) {
                errors.add("Error accessing field: " + field.getName());
            }
        }

        if (errors.isEmpty()) {
            return "VALID - All checks passed!";
        } else {
            return "INVALID:\n    - " + String.join("\n    - ", errors);
        }
    }
}
