//
//Fix the compiler errors.  The program should display the value 105.
//
package debug3;
public class debug3 {


    public static void main(String[] args) {
        int val = method(2,5);
        System.out.println(val);  //print the value of val.
    }



    public static int method(int num1, int num2)
    {
        if (num1 == 2);  // if num1 is equal to 2, then set num1 equal to 100.
        {
            num1 = 100;
        }

        num2 = num1 + num2;
        return(num2);
    }
}



