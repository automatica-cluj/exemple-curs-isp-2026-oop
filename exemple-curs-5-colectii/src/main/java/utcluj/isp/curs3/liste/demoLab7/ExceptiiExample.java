package utcluj.isp.curs3.liste.demoLab7;

class DemoException extends Exception{
    public DemoException(String msg) {
        super(msg);
    }
}

class DemoThrowException{
    public void doSometign(boolean t) throws DemoException{
        if(t)
            throw new DemoException("Some error!");
    }
}

public class ExceptiiExample {
    public static void main(String[] args) {
        DemoThrowException dt = new DemoThrowException();
        try {
            dt.doSometign(false);
        } catch (DemoException e) {
            System.out.println("Print some message !");
        }
    }
}
