// Fix the compiler errors.  The program should display the value 14.
package debug4;
public class debug4 {
    public static void main(String[] args) {
        int num = func(1);
        System.out.println(num);    // display the value of num.
    }
   public static int func(int value)
    {
        int num = 10;
        //loop while value is less then 3.
        while (value < 3)
        {
            num += 2;
            value++;
        }
        return(num);
    }    
}