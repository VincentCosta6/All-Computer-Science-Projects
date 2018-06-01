//
// Fix the compiler errors.  The program should display the value 12.
//
package debug1;
public class debug1 {
    public static void main(String[] args) {
        int val1 = 2;
        int val2 = val1 + 4;
        int val3 = val1 + val2;
        val3 = method1(val3);
        val3 = val1 + val3;   // store into val3 the sum of val1 and val3.
        System.out.println(val3);
    }
    public static int method1(int num1) {
        int num2 = num1 + 2;
        return(num2);   // return num2.
    }

}
