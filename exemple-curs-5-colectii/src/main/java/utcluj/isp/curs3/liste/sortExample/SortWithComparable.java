package utcluj.isp.curs3.liste.sortExample;

import java.util.ArrayList;

public class SortWithComparable {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("John", 10));
        students.add(new Student("Alice", 9));
        students.add(new Student("Bob", 8));


        System.out.println("Before sorting:");
        for (Student student : students) {
            System.out.println(student);
        }

        java.util.Collections.sort(students);
        System.out.println("After sorting:");
        for (Student student : students) {
            System.out.println(student);
        }
    }
}

class Student implements Comparable<Student> {
    private String name;
    private int grade;

    public Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }


    /**
     * Compares this object with the specified object for order.
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Student o) {
        return this.grade - o.grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
