/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inputData;


import javax.swing.JOptionPane;


public class inputData {


    
    
    static double num1 = (Integer.parseInt(JOptionPane.showInputDialog("Enter Number 1: ")));
    static double num2 = (Integer.parseInt(JOptionPane.showInputDialog("Enter Number 2: ")));
    static String num3 = ((JOptionPane.showInputDialog("Enter Op: ")));
    
    static public double getInput1()
    {
        return(num1);
    }
    static public double getInput2()
    {
        return(num2);
    }
    static public String getInput3()
    {
        return(num3);
    }
    static public double Calculate()
    {
        double answer=0;
        if (num3.charAt(0) == '+')
        {
           answer= num1+num2;
        }
        if (num3.charAt(0) == '-')
        {
           answer= num1-num2;
        }
        if (num3.charAt(0) == '*')
        {
           answer= num1*num2;
        }
        if (num3.charAt(0) == '/')
        {
           answer= num1/num2;
        }
        if (num3.charAt(0) == 'A')
        {
           answer= (num1*num1)*Math.PI;
        }
        if (num3.charAt(0) == 'A')
        {
           answer= (num1*num1)*Math.PI;
        }
        
        
        return(answer);
    }
    
    
    
    
}
