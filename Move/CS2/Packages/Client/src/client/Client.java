
package client;

//import mathstuff.Geom;
import inputData.inputData;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;

public class Client {

    public static void main(String[] args) {
        
        
        while(true)
        {
            double num1=inputData.getInput1();
            double num2=inputData.getInput2();
            String Operation=inputData.getInput3();
            double answer=(Double.parseDouble(JOptionPane.showInputDialog(inputData.Calculate())));
        }
        
        

        
        

        
        
        
        
        
    }
}
