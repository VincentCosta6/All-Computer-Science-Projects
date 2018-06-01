//
// Fix the compiler errors.  The program should display the value 6.
//
package debug5;

public class debug5 {

    public static void main(String[] args) {
        int val = 0;  // initialize val to 0.
        int array[] = new int[3];  // create an array of 3 integers.
        array[0] = 1;
        array[1] = 2;
        array[2] = 3;


        // add up the values in the array.
        for (int zx = 0;zx < array.length;zx++)
        {
            val += array[zx];
        }

        System.out.println(val);
    }

}
