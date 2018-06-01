//
// Fix the compiler errors.  The program should display the value 6.
//
package debug7;

public class debug7 {

    public static void main(String[] args) {
        int num = 3;
        boolean val = false;
        if (num == 3)
        {
            num++;
            if (val == false)
            {
                num++;
                if (num == 3)
                {
                    num = 23;
                }
                else if (num == 4)
                {
                    num = 10;
                }
                
                else
                {
                    num+=1;
                }
            }
            else if(num == 10)
            {
                num = 16;
            }
        }
        System.out.println(num);

    }
}