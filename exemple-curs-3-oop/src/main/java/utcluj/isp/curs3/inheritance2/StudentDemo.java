package utcluj.isp.curs3.inheritance2;

import java.util.Objects;

class Person{
    private String name;
    private String address;

    public void showAddress(){
        //....
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}

class Student extends Person {
    private double[] grades;

    public void showAvarageGrade(){
        //...
    }
}

public class StudentDemo{
    public static void main(String[] args) {
        Person p1 = new Student();
        p1.showAddress();
        //p1.showAvarageGrade(); THIS DOES NOT WORK!
        ((Student)p1).showAvarageGrade();
        Student s2 = (Student)p1;
        s2.showAvarageGrade();
    }
}
