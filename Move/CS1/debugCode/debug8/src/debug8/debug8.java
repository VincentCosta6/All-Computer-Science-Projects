//
// Fix the compiler errors.  The program should display the value 9.
//
package debug8;

public class debug8 {

    public static void main(String[] args){

        boolean keepGoing;
        int list[] = new int[3];
        list[0] = 1;
        list[1] = 2;
        list[2] = 2;
        
        int value = list[0] + list[1] + list[2];   //store the sum of the values of the array in value.
        keepGoing = true;
        while (keepGoing)
        {
            value++;
            if (value == 9)
                keepGoing = false;
        }
        System.out.println(value);
    }
}