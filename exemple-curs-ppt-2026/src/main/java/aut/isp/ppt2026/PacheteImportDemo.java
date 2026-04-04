package aut.isp.ppt2026;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

/**
 * PACHETE, IMPORT SI CLASSPATH - Minimal Demo for Students
 *
 * This file demonstrates:
 * 1. Import statements - explicitly importing classes from packages
 * 2. Fully qualified names - alternative to imports
 * 3. java.lang auto-import - classes auto-available without import
 * 4. Static imports - importing static methods and constants
 * 5. Class metadata via reflection - Class.forName(), getClass(), etc.
 * 6. Package organization concepts - how Java uses packages
 *
 * File structure: [package] -> [imports] -> [classes]
 */

public class PacheteImportDemo {

    public static void main(String[] args) {
        System.out.println("=== PACHETE, IMPORT SI CLASSPATH DEMO ===\n");

        // 1. Demonstrate imported classes from java.util
        demoImportedUtilClasses();

        // 2. Demonstrate fully qualified names (alternative to imports)
        demoFullyQualifiedNames();

        // 3. Demonstrate java.lang auto-import (no import needed)
        demoJavaLangAutoImport();

        // 4. Demonstrate static imports
        demoStaticImports();

        // 5. Demonstrate Class.forName() and class loading
        demoClassLoading();

        // 6. Demonstrate reflection with custom class
        demoReflection();

        // 7. Demonstrate packages and class metadata
        demoPackageInfo();
    }

    /**
     * 1. IMPORTED CLASSES - Using java.util (ArrayList, HashMap, List, Map)
     */
    private static void demoImportedUtilClasses() {
        System.out.println("1. IMPORTED CLASSES - java.util package");
        System.out.println("   (Used 'import java.util.ArrayList;' etc at top of file)\n");

        // Using ArrayList - imported class
        List<Produs> products = new ArrayList<>();
        products.add(new Produs("Laptop", 1500.0, 5));
        products.add(new Produs("Mouse", 25.0, 50));
        products.add(new Produs("Keyboard", 80.0, 20));

        System.out.println("ArrayList<aut.isp.ppt2026.Produs> created using IMPORTED class:");
        for (Produs p : products) {
            System.out.println("  - " + p);
        }

        // Using HashMap - imported class
        Map<String, Produs> productMap = new HashMap<>();
        for (Produs p : products) {
            productMap.put(p.getName(), p);
        }

        System.out.println("\nHashMap<String, aut.isp.ppt2026.Produs> created using IMPORTED class:");
        System.out.println("  Keys: " + productMap.keySet());
        System.out.println();
    }

    /**
     * 2. FULLY QUALIFIED NAMES - Alternative to imports
     */
    private static void demoFullyQualifiedNames() {
        System.out.println("2. FULLY QUALIFIED NAMES - No import needed, use full path");
        System.out.println("   (Example: java.util.Scanner instead of importing Scanner)\n");

        // Using fully qualified name instead of import
        java.util.Scanner scanner = new java.util.Scanner("192.168.1.1");
        System.out.println("Scanner created with fully qualified name: java.util.Scanner");
        System.out.println("  Input: 192.168.1.1");

        String part1 = scanner.next();
        System.out.println("  Read: " + part1);
        scanner.close();

        // Another example with Random
        java.util.Random random = new java.util.Random();
        System.out.println("\nRandom created with fully qualified name: java.util.Random");
        System.out.println("  Random number: " + random.nextInt(100));
        System.out.println();
    }

    /**
     * 3. JAVA.LANG AUTO-IMPORT - These classes don't need import
     * String, System, Object, Integer, Math, etc. are always available
     */
    private static void demoJavaLangAutoImport() {
        System.out.println("3. JAVA.LANG AUTO-IMPORT - No 'import' needed for:");
        System.out.println("   String, System, Object, Math, Integer, etc.\n");

        // String - from java.lang, no import needed
        String message = "All classes in java.lang are AUTO-IMPORTED";
        System.out.println("String: " + message);

        // Integer - from java.lang
        Integer num = Integer.parseInt("42");
        System.out.println("Integer.parseInt(\"42\"): " + num);

        // Math - from java.lang
        System.out.println("Math.max(15, 20): " + Math.max(15, 20));
        System.out.println("Math.min(15, 20): " + Math.min(15, 20));

        // Object - from java.lang (superclass of all classes)
        Object obj = new Produs("Test", 10.0, 1);
        System.out.println("Object.getClass(): " + obj.getClass().getSimpleName());
        System.out.println();
    }

    /**
     * 4. STATIC IMPORTS - Import static constants and methods
     * (Used: import static java.lang.Math.PI; import static java.lang.Math.sqrt;)
     */
    private static void demoStaticImports() {
        System.out.println("4. STATIC IMPORTS - Import constants and static methods");
        System.out.println("   (Used 'import static java.lang.Math.PI;' and '.sqrt' at top)\n");

        // Using PI constant directly (imported with static import)
        System.out.println("PI constant (static import): " + PI);
        double radius = 5.0;
        double circumference = 2 * PI * radius;
        System.out.println("Circumference of circle (r=" + radius + "): " + circumference);

        // Using sqrt method directly (imported with static import)
        double sqrtResult = sqrt(16.0);
        System.out.println("\nsqrt(16.0) using static import: " + sqrtResult);

        // You could also use Math.sqrt (non-static)
        System.out.println("Math.sqrt(16.0) without static import: " + Math.sqrt(16.0));
        System.out.println();
    }

    /**
     * 5. CLASS LOADING - Using Class.forName() and getClass()
     * Shows how Java locates and loads classes
     */
    private static void demoClassLoading() {
        System.out.println("5. CLASS LOADING - Class.forName() and getClass()");
        System.out.println("   (Shows how classes are located in the CLASSPATH)\n");

        try {
            // Class.forName() - dynamically load a class by name
            Class<?> stringClass = Class.forName("java.lang.String");
            System.out.println("Class.forName(\"java.lang.String\"):");
            System.out.println("  Loaded: " + stringClass.getName());
            System.out.println("  Simple name: " + stringClass.getSimpleName());

            // getClass() - get the class of an object
            Produs product = new Produs("Headphones", 120.0, 30);
            Class<?> produktClass = product.getClass();
            System.out.println("\nproduct.getClass() for aut.isp.ppt2026.Produs object:");
            System.out.println("  Loaded: " + produktClass.getName());
            System.out.println("  Simple name: " + produktClass.getSimpleName());

            // getClassLoader() - shows which ClassLoader loaded the class
            System.out.println("\nClassLoader information:");
            System.out.println("  String ClassLoader: " + stringClass.getClassLoader());
            System.out.println("  aut.isp.ppt2026.Produs ClassLoader: " + produktClass.getClassLoader());

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + e.getMessage());
        }
        System.out.println();
    }

    /**
     * 6. REFLECTION - Inspect class metadata at runtime
     */
    private static void demoReflection() {
        System.out.println("6. REFLECTION - Inspect class structure at runtime\n");

        Produs product = new Produs("Monitor", 300.0, 10);
        Class<?> produktClass = product.getClass();

        System.out.println("Class: " + produktClass.getName());
        System.out.println("Simple Name: " + produktClass.getSimpleName());

        // Get superclass
        Class<?> superclass = produktClass.getSuperclass();
        System.out.println("Superclass: " + (superclass != null ? superclass.getSimpleName() : "none"));

        // Get implemented interfaces
        Class<?>[] interfaces = produktClass.getInterfaces();
        System.out.println("Interfaces: " + (interfaces.length > 0 ? interfaces.length : "none"));

        // Get declared fields (if any)
        java.lang.reflect.Field[] fields = produktClass.getDeclaredFields();
        System.out.println("Fields declared: " + fields.length);
        for (java.lang.reflect.Field field : fields) {
            System.out.println("  - " + field.getType().getSimpleName() + " " + field.getName());
        }

        // Get declared methods (if any)
        java.lang.reflect.Method[] methods = produktClass.getDeclaredMethods();
        System.out.println("Methods declared: " + methods.length);
        for (java.lang.reflect.Method method : methods) {
            System.out.println("  - " + method.getName() + "()");
        }
        System.out.println();
    }

    /**
     * 7. PACKAGE INFO - Show how different packages provide utilities
     */
    private static void demoPackageInfo() {
        System.out.println("7. DIFFERENT PACKAGES, DIFFERENT UTILITIES\n");

        // java.util - Collections, random, scanning
        System.out.println("java.util package: Collections, Date utilities, Random, Scanner");
        List<String> list = new ArrayList<>();
        list.add("ArrayList");
        System.out.println("  Example: List/ArrayList for storing collections");

        // java.time - Modern date/time (since Java 8)
        System.out.println("\njava.time package: Modern date and time API");
        LocalDate today = LocalDate.now();
        System.out.println("  Today: " + today);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        System.out.println("  Now: " + now.format(formatter));

        // java.io - File input/output
        System.out.println("\njava.io package: File operations, streams");
        File file = new File("test.txt");
        System.out.println("  File path: " + file.getAbsolutePath());
        System.out.println("  Exists: " + file.exists());

        // java.lang - Core language classes (always available, no import needed)
        System.out.println("\njava.lang package: Core classes (String, System, Math, Object)");
        System.out.println("  Auto-imported - no 'import' statement needed");
        System.out.println("  Available: String, System, Math, Object, Integer, etc.");

        System.out.println();
    }
}

/**
 * CUSTOM CLASS: aut.isp.ppt2026.Produs (aut.isp.ppt2026.Product)
 * Used to demonstrate class metadata and reflection in the demo.
 * Simple class with name, price, and quantity.
 */
class Produs {
    private String name;
    private double price;
    private int quantity;

    public Produs(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalValue() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.2f", price) + " (qty: " + quantity + ")";
    }
}
