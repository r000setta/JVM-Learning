package chap7;

class SuperClass{
    static {
        System.out.println("Superclass init");
    }
    public static int value=1;
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init");
    }
}

public class NotInit {
    public static void main(String[] args) {
        SuperClass[] sca=new SuperClass[10];
    }
}