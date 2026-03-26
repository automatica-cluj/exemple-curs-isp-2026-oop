package utcluj.isp.curs3.liste.demoLab6;

import java.util.ArrayList;

public class ExempluEx {
}

class B{
}

class C{
}

class A{
    private ArrayList<B> list1;
    private ArrayList<C> list2 = new ArrayList<>();

    public A(ArrayList<B> list1) {
        this.list1 = list1;
        list2.add(new C());
        list2.add(new C());
    }
}

interface X{
    public String method1();
}

class Y implements X{

    @Override
    public String method1() {
        return "Some string";
    }
}

class P{

}

class Q{
    void doSometing(Q arg){

    }
}