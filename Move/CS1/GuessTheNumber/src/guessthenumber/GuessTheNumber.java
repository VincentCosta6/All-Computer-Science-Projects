
package guessthenumber;
import java.io.*;
public class GuessTheNumber {
    public static void main(String[] args) {
            int userNum = -1;
            int startNum = 0;
            int range = 101;
            int endNum = 100;
            int Rstart = 1;
            while(Rstart==1)
            {
            int computerNum = (int)(Math.random()*range +startNum);
            System.out.println("The computer # is " + computerNum);
            System.out.println("Choose a number between " +startNum+ " and "+ endNum);
            for(int index = 0;index <=100;index++)
            {
            while(userNum != computerNum)
            {
                userNum = readInt();
                if (userNum == computerNum)
                {
                    System.out.println("Good job, you must have worked hard, You are Correct!");
                    index+=1;
                    if(index<=1)
                    {
                    System.out.println("It took you " +index +" try");
                    }
                    else
                    {
                        System.out.println("It took you " +index +" Tries");
                    }
                }
                else if(userNum<computerNum)
                {
                    System.out.println("You are Incorrect, guess HIGHER");
                    index+=1;
                    if(computerNum>50)
                    {
                        System.out.println("Hint: Number is higher than 50");
                    }
                    else if(computerNum<50)
                    {
                     System.out.println("Hint: Number is lower than 50");
                    }
                }
                else
                {
                    System.out.println("You are a Incorrect, guess LOWER");
                    index+=1;
                    if (computerNum>50)
                    {
                        System.out.println("Number is higher than 50");
                    }
                    else if (computerNum<50)
                    {
                        System.out.println("Hint: Number is lower than 50");
                    }
                }
            }
            }
            
            System.out.println("Would you like to play again?");
            System.out.println("1 for YES, 2 for NO");
            Rstart = readInt();
            if(Rstart==1)
            {
                Rstart = 1;
            }
            else
            {
                Rstart = 2;
            }
            }
        
    }
 ///////////////////////////////////   
    public static int readInt()
    {
        String str = read();
        int num = Integer.parseInt(str.trim());
        return (num);
    }
   public static String read()
        {
        byte [] buffer = new byte[10];
        try
            {
            int numBytes = System.in.read(buffer);
            }
        catch(IOException e)
            {
            System.out.print("Error: " + e);
            System.exit(1);
            }
        String str = new String(buffer);
        int ball = 5;
        return (str);
        }
    public static void write(String str)
    {
        System.out.print(str);
    }
//////////////////////////////////////////////////
}
