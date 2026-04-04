package aut.isp.ppt2026;

import java.util.HashMap;
import java.util.Objects;

/**
 * Demo: The java.lang.Object Class and Its Fundamental Methods
 *
 * Key Topics:
 * 1. Every class implicitly extends Object
 * 2. toString() - readable output
 * 3. equals(Object) - correct implementation with contract
 * 4. hashCode() - relationship with equals
 * 5. getClass() - runtime type information
 * 6. clone() - implementing Cloneable
 * 7. Common mistakes in equals()
 */
public class ClasaObjectDemo {

    public static void main(String[] args) {
        System.out.println("=== JAVA OBJECT CLASS DEMO ===\n");

        // Topic 1: Every class extends Object implicitly
        demonstrateInheritance();

        // Topic 2 & 3: toString() and equals()
        demonstrateToStringAndEquals();

        // Topic 4 & 5: hashCode() and the equals-hashCode contract
        demonstrateHashCodeContract();

        // Topic 6: getClass()
        demonstrateGetClass();

        // Topic 7: clone()
        demonstrateClone();

        // Topic 8: Common mistakes
        demonstrateCommonMistakes();
    }

    private static void demonstrateInheritance() {
        System.out.println("--- Topic 1: Every class extends Object implicitly ---");
        Student s = new Student(1, "Alice");
        // aut.isp.ppt2026.Student class didn't explicitly write "extends Object", but it does!
        System.out.println("aut.isp.ppt2026.Student is instance of Object: " + (s instanceof Object));
        System.out.println("Object class: " + s.getClass().getSuperclass().getName());
        System.out.println();
    }

    private static void demonstrateToStringAndEquals() {
        System.out.println("--- Topic 2 & 3: toString() and equals() ---");

        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(1, "Alice");
        Student s3 = s1;

        // Default toString() - inherited from Object, shows class@hashcode
        // We override it to show readable output
        System.out.println("s1.toString(): " + s1);  // Uses overridden toString()
        System.out.println("s2.toString(): " + s2);  // Different student, same data
        System.out.println("s3.toString(): " + s3);  // Same reference as s1

        System.out.println("\nEquals comparison:");
        System.out.println("s1 == s3 (reference equality): " + (s1 == s3));
        System.out.println("s1.equals(s3) (value equality): " + s1.equals(s3));
        System.out.println("s1 == s2 (different objects): " + (s1 == s2));
        System.out.println("s1.equals(s2) (same values): " + s1.equals(s2));
        System.out.println("s1.equals(null): " + s1.equals(null));
        System.out.println("s1.equals(\"Alice\") (wrong type): " + s1.equals("Alice"));

        System.out.println("\nEquals contract verification:");
        System.out.println("Reflexive (x.equals(x)): " + s1.equals(s1));
        System.out.println("Symmetric (if x.equals(y) then y.equals(x)): "
                + (s1.equals(s2) && s2.equals(s1)));
        System.out.println("Transitive (x.equals(y) && y.equals(z) => x.equals(z)): "
                + demonstrateTransitive());
        System.out.println();
    }

    private static boolean demonstrateTransitive() {
        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(1, "Alice");
        Student s3 = new Student(1, "Alice");
        return (s1.equals(s2) && s2.equals(s3)) ? s1.equals(s3) : false;
    }

    private static void demonstrateHashCodeContract() {
        System.out.println("--- Topic 4 & 5: hashCode() and equals-hashCode contract ---");

        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(1, "Alice");

        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s2.hashCode(): " + s2.hashCode());
        System.out.println("s1.equals(s2): " + s1.equals(s2));
        System.out.println("Same hashCode for equal objects: " + (s1.hashCode() == s2.hashCode()));

        System.out.println("\nHashCode-Equals Contract:");
        System.out.println("If equals() returns true, hashCode() MUST return same value: "
                + (s1.equals(s2) ? (s1.hashCode() == s2.hashCode()) : "N/A"));

        // Demonstrate the importance with HashMap
        System.out.println("\nHashMap behavior (correct vs broken):");
        HashMap<Student, String> map = new HashMap<>();
        map.put(s1, "Alice's data");
        System.out.println("Stored s1 with key -> value: Alice's data");
        System.out.println("Retrieve with s1: " + map.get(s1));
        System.out.println("Retrieve with s2 (equal object): " + map.get(s2));
        System.out.println("Both s1 and s2 find the same entry because hashCode is overridden!");

        System.out.println();
    }

    private static void demonstrateGetClass() {
        System.out.println("--- Topic 6: getClass() - Runtime Type Information ---");

        Student s = new Student(1, "Alice");
        Punct p = new Punct(3, 4);

        System.out.println("s.getClass(): " + s.getClass());
        System.out.println("s.getClass().getName(): " + s.getClass().getName());
        System.out.println("s.getClass().getSimpleName(): " + s.getClass().getSimpleName());

        System.out.println("\nRuntime type checking:");
        System.out.println("s.getClass() == aut.isp.ppt2026.Student.class: " + (s.getClass() == Student.class));
        System.out.println("p.getClass() == aut.isp.ppt2026.Punct.class: " + (p.getClass() == Punct.class));
        System.out.println("s.getClass() == p.getClass(): " + (s.getClass() == p.getClass()));

        System.out.println();
    }

    private static void demonstrateClone() {
        System.out.println("--- Topic 7: clone() - Shallow Copy ---");

        try {
            Punct original = new Punct(5, 10);
            Punct cloned = original.clone();

            System.out.println("Original: " + original);
            System.out.println("Cloned: " + cloned);
            System.out.println("Original == Cloned (reference): " + (original == cloned));
            System.out.println("Original.equals(Cloned) (value): " + original.equals(cloned));

            // Modify clone, original unchanged
            cloned.setX(100);
            System.out.println("\nAfter modifying cloned.x to 100:");
            System.out.println("Original: " + original);
            System.out.println("Cloned: " + cloned);
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone failed: " + e.getMessage());
        }

        System.out.println();
    }

    private static void demonstrateCommonMistakes() {
        System.out.println("--- Topic 8: Common Mistakes in equals() ---");

        System.out.println("MISTAKE: Writing equals(aut.isp.ppt2026.Student) instead of equals(Object)");
        System.out.println("Correct signature: equals(Object) with @Override annotation");
        System.out.println();

        Student s1 = new Student(1, "Bob");
        StudentBroken broken = new StudentBroken(1, "Bob");

        System.out.println("Correct aut.isp.ppt2026.Student class:");
        System.out.println("  s1.equals(s1): " + s1.equals(s1));
        System.out.println("  Uses correct equals(Object) method");

        System.out.println("\nBroken aut.isp.ppt2026.StudentBroken class (equals(aut.isp.ppt2026.StudentBroken) signature):");
        System.out.println("  When calling equals(Object), it uses inherited Object.equals()");
        System.out.println("  Only reference equality works: broken.equals(broken) = "
                + broken.equals(broken));
        System.out.println("  The overloaded equals(aut.isp.ppt2026.StudentBroken) is NEVER called");
        System.out.println();
    }
}

/**
 * aut.isp.ppt2026.Student class - demonstrates proper toString() and equals() implementation
 */
class Student {
    private int id;
    private String nume;

    public Student(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    /**
     * Overridden toString() - makes output readable
     * Default Object.toString() shows: aut.isp.ppt2026.Student@hexHashCode
     */
    @Override
    public String toString() {
        return "aut.isp.ppt2026.Student{" + "id=" + id + ", nume='" + nume + '\'' + '}';
    }

    /**
     * Proper equals() implementation following the contract:
     * - Reflexive: x.equals(x) is true
     * - Symmetric: x.equals(y) iff y.equals(x)
     * - Transitive: x.equals(y) && y.equals(z) => x.equals(z)
     * - Consistent: multiple equals() calls return same result
     * - Non-null: x.equals(null) is false
     */
    @Override
    public boolean equals(Object obj) {
        // 1. Reference check (reflexive optimization)
        if (this == obj) {
            return true;
        }

        // 2. Null check
        if (obj == null) {
            return false;
        }

        // 3. Type check using getClass()
        if (this.getClass() != obj.getClass()) {
            return false;
        }

        // 4. Cast to our type
        Student other = (Student) obj;

        // 5. Compare fields
        return this.id == other.id && this.nume.equals(other.nume);
    }

    /**
     * hashCode() implementation - must be consistent with equals()
     * If two objects are equal, they MUST have the same hashCode
     */
    @Override
    public int hashCode() {
        // Using Objects.hash() utility method
        return Objects.hash(id, nume);
    }

    // Getters for testing
    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }
}

/**
 * aut.isp.ppt2026.Punct (Point) class - demonstrates clone() implementation
 */
class Punct implements Cloneable {
    private int x;
    private int y;

    public Punct(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.Punct(" + x + ", " + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Punct other = (Punct) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    /**
     * Implementing Cloneable with clone() method
     * Creates a shallow copy of the object
     */
    @Override
    public Punct clone() throws CloneNotSupportedException {
        return (Punct) super.clone();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

/**
 * aut.isp.ppt2026.StudentBroken - demonstrates the WRONG way to override equals()
 * Using equals(aut.isp.ppt2026.StudentBroken) instead of equals(Object)
 * This is NOT an override - it's an overload!
 */
class StudentBroken {
    private int id;
    private String nume;

    public StudentBroken(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "aut.isp.ppt2026.StudentBroken{" + "id=" + id + ", nume='" + nume + '\'' + '}';
    }

    // WRONG: This is NOT an @Override - this is an OVERLOAD
    // The correct signature must be equals(Object)
    // This method will never be called when using Object polymorphism
    public boolean equals(StudentBroken obj) {
        if (obj == null) {
            return false;
        }
        return this.id == obj.id && this.nume.equals(obj.nume);
    }
}
