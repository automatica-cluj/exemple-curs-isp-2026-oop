package utcluj.isp.curs3.liste.demoLab6;

import java.util.HashMap;
import java.util.HashSet;

class Student {
    private String name;
    private String id;
    private HashMap<String,Double> subjects = new HashMap<>();

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void addGrade(String subject, Double grade){
        subjects.put(subject, grade);
    }
}

public class Test{
    public static void main(String[] args) {
        HashSet<Student> list = new HashSet<>();
        Student s1 = new Student("John","123");

        s1.addGrade("OOP", 10.0);
        s1.addGrade("Math", 9.0);
        list.add(s1);

    }
}


