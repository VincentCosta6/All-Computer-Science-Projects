//
// Fix the compiler errors.  The program should display the value 5.
//
package debug2;
public class debug2 {
    public static void main(String[] args) {
        int val = function1(3);
        System.out.println(val);  // print the value of val.
    }

    public static int function1(int num)
    {
        num = 3;  // initialize num to 3.
        num += 2;
        return(num);
    }
    }

