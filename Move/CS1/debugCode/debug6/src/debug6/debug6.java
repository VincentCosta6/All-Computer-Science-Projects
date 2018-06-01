//
// Fix the compiler errors.  The program should display the value 3.
//
package debug6;

public class debug6 {

    public static void main(String[] args) {
//program should loop through each of the values in the array list
//and add them all up and store the sum in variable num.
        int list[] = {3,5,1,-6};
        int num = 0;
        for (int index=0;index<=3;index++)
        {
            num+=list[index];
        }
        System.out.println(num);
    }
}