
package calculator;
import java.io.*;

public class Calculator {
	static final int NOOP = 0;
	static final int ADD = 1;
        static final int Subtract = 2;
        static final int Multiply = 3;
        static final int Divide = 4;
        static final int Exponent = 5;
        static final int Factorial = 6;


    public static void main(String[] args) {
		boolean foundTheNum = false;
		write("Enter a number.\n");
		double num1 = readInt();
		write("Enter an operation. (+ - * / ^ !"+ ")\n");
		int zoperation = readOperation();

		String result;
		if (zoperation == ADD)
		{
        		write("Enter another number.\n");
        		double num2 = readInt();
			result = add(num1,num2);
		}
                else if (zoperation == Subtract)
                {
                    write("Enter another number.\n");
        		double num2 = readInt();
			result = subtract(num1,num2);
                }
                else if (zoperation == Multiply)
                {
                    write("Enter another number.\n");
        		double num2 = readInt();
			result = multiply(num1,num2);
                }
                else if (zoperation ==Divide)
                {
                    write("Enter another number.\n");
                    double num2 =readInt();
                    result = divide(num1,num2);
                }
                else if (zoperation == Exponent)
                {
                    write("Enter another number.\n");
                    double num2 =readInt();
                    result = exponent(num1,num2);
                }
                else if (zoperation == Factorial)
                {
                    result = factorial(num1);
                }
                
                    
                else
		{
			result = "Ivalid operation\n";
		}
		write(result);
    }
	public static String add(double num1,double num2)
	{
		String result = String.valueOf(num1) + " + " + String.valueOf(num2) +
		" = " + String.valueOf(num1 + num2) + "\n";
		return(result);
	}
        public static String subtract(double num1, double num2)
        {
            String result = String.valueOf(num1) + " - " + String.valueOf(num2) +
		" = " + String.valueOf(num1 - num2) + "\n";
            return(result);
        }
        public static String multiply(double num1, double num2)
        {
            String result = String.valueOf(num1) + " * " + String.valueOf(num2) +
		" = " + String.valueOf(num1 * num2) + "\n";
            return(result);
        }
        public static String divide(double num1, double num2)
        {
            String result = String.valueOf(num1) + " / " + String.valueOf(num2) +
		" = " + String.valueOf(num1 / num2) + "\n";
            return(result);
        }
        public static String exponent(double num1, double num2)
        {
        int answer1=1;
        for (int index=0;index<num2;index++)
        {
            answer1*=num1;
        }
            String answer = String.valueOf(num1) + " ^ " + String.valueOf(num2) +
		" = " + String.valueOf(answer1) + "\n";
            return(answer);
        }
        public static String factorial(double num1)
        {
            double answer=num1;
            for(double index = num1;index>0;index--)
            {

                answer=num1*index;

            }
            String result = String.valueOf(num1) + " ! " +
		" = " + String.valueOf(answer) + "\n";
            return(result);
        }

        public static int readOperation()
	{
		String str = read();
		if (str.charAt(0) == '+')
		{
			return(ADD);
		}
                else if (str.charAt(0) == '-')
                {
                    return(Subtract);
                }
                else if (str.charAt(0) == '*')
                {
                    return(Multiply);
                }
                else if (str.charAt(0)== '/')
                {
                    return(Divide);
                }
                else if(str.charAt(0)== '^')
                {
                    return (Exponent);
                }
                else if(str.charAt(0)== '!')
                {
                    return (Factorial);
                }
		return(NOOP);
	}

///////////////////////////////////////////////////
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
            return (str);
	}

	public static void write(String str)
	{
		System.out.print(str);
	}
}
