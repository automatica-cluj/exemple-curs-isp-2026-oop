package aut.isp.ppt2026;

import java.io.Serializable;
import java.util.*;

/**
 * MINIMAL GENERICS DEMO FOR STUDENTS
 *
 * Topics:
 * 1. Raw types vs Generics (casting dangers)
 * 2. Generic classes (Container<T>, Pair<K,V>)
 * 3. Generic methods (printArray<T>)
 * 4. Type parameter conventions (T, E, K, V, N)
 * 5. Wildcard types (?, ? extends, ? super)
 * 6. PECS principle and copy method
 * 7. Type erasure
 * 8. Bounded type parameters (<T extends>)
 * 9. Multiple bounds (<T extends X & Y>)
 */
public class GenericsDemo {

    // ========== TOPIC 1: RAW TYPES vs GENERICS ==========

    /**
     * Before Generics: Raw types require casting and are unsafe
     */
    public static void demonstrateRawTypes() {
        System.out.println("\n=== TOPIC 1: Raw Types vs Generics ===");

        // ❌ OLD WAY (pre-generics): Raw types with casting dangers
        List rawList = new ArrayList();
        rawList.add("Hello");
        rawList.add(42);  // No type checking! Can add anything

        // Danger: Need to cast, and ClassCastException risk
        String value = (String) rawList.get(0);  // Works
        System.out.println("Raw type get(0): " + value);

        // This will throw ClassCastException at runtime!
        try {
            String wrongCast = (String) rawList.get(1);  // 42 is not a String
        } catch (ClassCastException e) {
            System.out.println("Raw type danger: " + e.getClass().getSimpleName());
        }

        // ✅ NEW WAY: Generic types are type-safe
        List<String> genericList = new ArrayList<>();
        genericList.add("Hello");
        // genericList.add(42);  // Compile error! Type-safe

        String safeValue = genericList.get(0);  // No casting needed
        System.out.println("Generic type get(0): " + safeValue);
    }

    // ========== TOPIC 2: GENERIC CLASSES ==========

    /**
     * Simple generic container with one type parameter
     */
    public static class Container<T> {
        private T value;

        public void set(T value) {
            this.value = value;
        }

        public T get() {
            return value;
        }
    }

    /**
     * Generic interface with two type parameters: Key and Value
     */
    public interface Pair<K, V> {
        K getKey();
        V getValue();
    }

    /**
     * Implementation of Pair interface (generic class implementing generic interface)
     */
    public static class OrderedPair<K, V> implements Pair<K, V> {
        private K key;
        private V value;

        public OrderedPair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() { return key; }

        @Override
        public V getValue() { return value; }
    }

    public static void demonstrateGenericClasses() {
        System.out.println("\n=== TOPIC 2: Generic Classes ===");

        // Container<T> with custom class
        Container<Dog> dogBox = new Container<>();
        dogBox.set(new Dog("Buddy"));
        Dog dog = dogBox.get();
        System.out.println("Container<Dog>: " + dog);

        // Pair<K, V> implementation
        Pair<String, Integer> pair = new OrderedPair<>("Age", 25);
        System.out.println("Pair<String, Integer>: " + pair.getKey() + " = " + pair.getValue());
    }

    // ========== TOPIC 3: GENERIC METHODS & TYPE INFERENCE ==========

    /**
     * Generic method: parameter T is independent from class generics
     * Works with any type T
     */
    public static <T> void printArray(T[] array) {
        System.out.println("Array contents:");
        for (T element : array) {
            System.out.println("  " + element);
        }
    }

    /**
     * Generic method with upper bounded type parameter
     * Can only use methods/properties of Number class
     */
    public static <T extends Number> double sum(T[] numbers) {
        double total = 0;
        for (T num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }

    public static void demonstrateGenericMethods() {
        System.out.println("\n=== TOPIC 3: Generic Methods & Type Inference ===");

        // Type inference: compiler deduces <Dog> from argument
        Dog[] dogs = { new Dog("Max"), new Dog("Bella") };
        printArray(dogs);

        // Explicit type argument (usually not needed)
        Integer[] numbers = { 1, 2, 3 };
        double total = GenericsDemo.<Integer>sum(numbers);
        System.out.println("Sum of array: " + total);
    }

    // ========== TOPIC 4: TYPE PARAMETER CONVENTIONS ==========

    /**
     * Type parameter naming conventions (IMPORTANT FOR STUDENTS):
     * T = Type (generic type parameter)
     * E = Element (in collections)
     * K = Key (in maps)
     * V = Value (in maps)
     * N = Number
     * R = Result
     *
     * Example: Map<K, V>, List<E>, Comparable<T>
     */
    public static class Example_TypeParamConventions<T, E, K, V, N extends Number> {
        // Just showing naming; not all used
    }

    // ========== TOPIC 5 & 6: WILDCARD TYPES & PECS PRINCIPLE ==========

    /**
     * Container holding boxes of different types
     * Used to demonstrate wildcards
     */
    public static class Cutie {
        private String item;

        public Cutie(String item) {
            this.item = item;
        }

        @Override
        public String toString() { return item; }
    }

    /**
     * UNBOUNDED WILDCARD: ?
     * Can read objects, type unknown, can't write (except null)
     */
    public static void printList(List<?> list) {
        System.out.println("Unbounded wildcard list: " + list);
    }

    /**
     * UPPER BOUNDED WILDCARD: ? extends T
     * Producer Extends: Read-only, can work with T and its subtypes
     * Cannot add new elements (except null)
     */
    public static double sum(List<? extends Number> numbers) {
        double total = 0;
        for (Number num : numbers) {
            total += num.doubleValue();
        }
        return total;
    }

    /**
     * LOWER BOUNDED WILDCARD: ? super T
     * Consumer Super: Write-only, can accept T and its supertypes
     * Can add elements of type T or subtypes
     */
    public static void addIntegers(List<? super Integer> list) {
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("Added integers to List<? super Integer>: " + list);
    }

    /**
     * PECS PRINCIPLE (Producer Extends, Consumer Super)
     *
     * Example: copy(source, destination)
     * - source is a PRODUCER (read-only): use ? extends T
     * - destination is a CONSUMER (write-only): use ? super T
     */
    public static <T> void copy(List<? extends T> source, List<? super T> destination) {
        for (T item : source) {
            destination.add(item);
        }
    }

    public static void demonstrateWildcardsAndPECS() {
        System.out.println("\n=== TOPIC 5 & 6: Wildcards & PECS Principle ===");

        // Unbounded wildcard
        List<String> stringList = new ArrayList<>();
        stringList.add("A");
        stringList.add("B");
        printList(stringList);

        // Upper bounded wildcard
        List<Double> doubleList = Arrays.asList(1.5, 2.5, 3.5);
        System.out.println("Sum with ? extends Number: " + sum(doubleList));

        // Lower bounded wildcard
        List<Number> numericList = new ArrayList<>();
        addIntegers(numericList);

        // PECS: copy using both extends and super
        List<Dog> dogs = Arrays.asList(new Dog("Rex"), new Dog("Spot"));
        List<Animal> animals = new ArrayList<>();
        copy(dogs, animals);
        System.out.println("Copied " + animals.size() + " dogs to List<Animal>");
    }

    // ========== TOPIC 7: TYPE ERASURE ==========

    /**
     * IMPORTANT: Generics are COMPILE-TIME ONLY
     *
     * At runtime (type erasure):
     * - List<String> becomes List
     * - List<Integer> becomes List
     * - Both are erased to the same List class
     *
     * Cannot use: new T(), T.class, instanceof List<String>
     */
    public static void demonstrateTypeErasure() {
        System.out.println("\n=== TOPIC 7: Type Erasure ===");

        List<String> stringList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        // At runtime, both are just List (erased)
        System.out.println("stringList.getClass(): " + stringList.getClass().getName());
        System.out.println("intList.getClass(): " + intList.getClass().getName());
        System.out.println("Are they the same? " +
            stringList.getClass().equals(intList.getClass()));

        System.out.println("NOTE: Generic info is lost at runtime (type erasure)");
        System.out.println("So: instanceof List<String> is NOT allowed");
    }

    // ========== TOPIC 8: BOUNDED TYPE PARAMETERS ==========

    /**
     * Animal hierarchy for demonstrating bounded types
     */
    public static class Animal {
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        @Override
        public String toString() { return this.name; }
    }

    public static class Dog extends Animal {
        public Dog(String name) { super(name); }
    }

    public static class Cat extends Animal {
        public Cat(String name) { super(name); }
    }

    /**
     * Bounded Type Parameter: <T extends Comparable<T>>
     * T must implement Comparable, allowing compareTo() calls
     * Enables type-safe sorting
     */
    public static <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Comparable implementation for sorting
     */
    public static class Dog_Comparable extends Animal implements Comparable<Dog_Comparable> {
        private int age;

        public Dog_Comparable(String name, int age) {
            super(name);
            this.age = age;
        }

        @Override
        public int compareTo(Dog_Comparable other) {
            return Integer.compare(this.age, other.age);
        }

        @Override
        public String toString() { return name + " (age " + age + ")"; }
    }

    public static void demonstrateBoundedTypes() {
        System.out.println("\n=== TOPIC 8: Bounded Type Parameters ===");

        Dog_Comparable[] dogs = {
            new Dog_Comparable("Rex", 5),
            new Dog_Comparable("Max", 8),
            new Dog_Comparable("Bella", 3)
        };

        Dog_Comparable oldest = findMax(dogs);
        System.out.println("Oldest dog: " + oldest);
    }

    // ========== TOPIC 9: MULTIPLE BOUNDS ==========

    /**
     * Type parameter with multiple bounds: <T extends A & B & C>
     * T must satisfy ALL bounds (first can be class, rest must be interfaces)
     */
    public static class ComparableSerializable extends Animal
        implements Comparable<ComparableSerializable>, Serializable {

        private static final long serialVersionUID = 1L;

        public ComparableSerializable(String name) { super(name); }

        @Override
        public int compareTo(ComparableSerializable other) {
            return this.name.compareTo(other.name);
        }
    }

    /**
     * Multiple bounds: T must be Comparable AND Serializable
     */
    public static <T extends Comparable<T> & Serializable> void sortAndSerialize(T[] array) {
        Arrays.sort(array);
        System.out.println("Sorted with multiple bounds: " + Arrays.toString(array));
        System.out.println("(Also guaranteed to be Serializable)");
    }

    public static void demonstrateMultipleBounds() {
        System.out.println("\n=== TOPIC 9: Multiple Bounds ===");

        ComparableSerializable[] items = {
            new ComparableSerializable("Zebra"),
            new ComparableSerializable("Apple"),
            new ComparableSerializable("Monkey")
        };

        sortAndSerialize(items);
    }

    // ========== MAIN ==========

    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  JAVA GENERICS DEMO FOR STUDENTS      ║");
        System.out.println("║  Tipuri Generice in Java              ║");
        System.out.println("╚════════════════════════════════════════╝");

        demonstrateRawTypes();
        demonstrateGenericClasses();
        demonstrateGenericMethods();
        demonstrateWildcardsAndPECS();
        demonstrateTypeErasure();
        demonstrateBoundedTypes();
        demonstrateMultipleBounds();

        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║  Demo Complete!                         ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
