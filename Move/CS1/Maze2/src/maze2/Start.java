/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze2;

import javax.swing.JOptionPane;

public class Start {
    
    static private int numNPC= (int)(Double.parseDouble(JOptionPane.showInputDialog("Number of NPC: (Max10)")));
    static private int numCoins= (int)(Double.parseDouble(JOptionPane.showInputDialog("Number of Coins:")));
    static private int highScore=(int)(Double.parseDouble(JOptionPane.showInputDialog("set HighScore")));
    static private int seizure=(int)(Double.parseDouble(JOptionPane.showInputDialog("set Seizure")));
    static private int num;
    static private String cheat;

    
    static public int getnumNPC()
    {
        return(numNPC);
    }
    static public int getnumCoins()
    {
        return(numCoins);
    }
    static public int getHighScore()
    {
        return(numCoins);
    }
    static public String getCheats()
    {
        return(cheat);
    }
    static public int getSeizure()
    {
        return(seizure);
    }
    static public void setCheats(String _cheat)
    {
        cheat=((JOptionPane.showInputDialog("Enter Command")));
    }
}
