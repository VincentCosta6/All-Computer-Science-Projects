
package guesstheanimal;
import java.io.*;
public class GuessTheAnimal {
    public static void main(String[] args) {
        System.out.println("Choose your animal:");
        System.out.println("Shark");
        System.out.println("Dolphin");
        System.out.println("Fresh Water Urchin");
        System.out.println("Goldfish");
        System.out.println("Lion");
        System.out.println("Snake");
        System.out.println("Dog");
        System.out.println("Wolf");
        System.out.print("do you live in the water? (y for yes, n for no).\n");
	String str = read();
	if (str.charAt(0) == 'y')
	{ 
          System.out.print("do you live in the ocean? (y for yes, n for no).\n");
          str = read();
          if (str.charAt(0) == 'y')
          {
                System.out.print("Are you agressive? (y for yes, n for no).\n");
                str = read();
                    if (str.charAt(0) == 'y')
                    {
                   System.out.println("you are a shark \n");
                    }
                    else
                    {
                   System.out.print("you are a dolphin \n");
                    }
          }
          else
          {
                System.out.print("Do you have spikes? (y for yes, n for no).\n");
                str = read();
                    if (str.charAt(0) == 'y')
                    {
                    System.out.print("you are a Fresh Water Urchin \n");
                    }
                    else
                    {
                    System.out.print("you are a Goldfish \n");
                    }
          }
        }
        else
        {
            System.out.print("do you live in a savannah/desert? (y for yes, n for no).\n");
          str = read();
          if (str.charAt(0) == 'y')
          {
                System.out.print("Are you the king? (y for yes, n for no).\n");
                str = read();
                    if (str.charAt(0) == 'y')
                    {
                   System.out.println("you are a Lion \n");
                    }
                    else
                    {
                   System.out.print("you are a Snake \n");
                    }
          }
          else
          {
                System.out.print("are you a tamed? (y for yes, n for no).\n");
                str = read();
                    if (str.charAt(0) == 'y')
                    {
                    System.out.print("you are a Dog \n");
                    }
                    else
                    {
                    System.out.print("you are a Wolf \n");
                    }
          }
        }
        
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
}