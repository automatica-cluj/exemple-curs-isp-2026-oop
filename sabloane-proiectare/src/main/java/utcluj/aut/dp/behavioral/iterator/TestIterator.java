package utcluj.aut.dp.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;

class Person {
    String name;
    Person(String name) {
        this.name = name;
    }
    public String toString() {
        return "Person:" + name;
    }
}

public class TestIterator {
    public static void main(String[] args) {
        ArrayList<Person> al = new ArrayList<>();
        al.add(new Person("Alex"));
        al.add(new Person("Dan"));
        al.add(new Person("Alin"));
        for (Iterator<Person> iter = al.iterator(); iter.hasNext(); ) {
            System.out.println(iter.next());
        }
        for (Person o : al) {
            System.out.println(o);
        }
    }
}
