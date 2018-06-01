
package polymorphism;

public class Polymorphism {

    public static void main(String[] args) {
        
        Class1 obj1 = new Class1();
        Class1 obj2 = new Class2();
        obj1.doStuff1();
        obj2.doStuff1();
        
//        obj2.doStuff3();
//        ((Class2)obj2).doStuff3();
    }
}

class Class1
{
    public void doStuff1()
    {
        System.out.println("apple");
        doStuff2();
    }
    public void doStuff2()
    {
        System.out.println("banana");        
    }
}
class Class2 extends Class1
{
    public void doStuff2()
    {
        System.out.println("coconut");
        doStuff3();
    }
    public void doStuff3()
    {
        System.out.println("date");
    }
}