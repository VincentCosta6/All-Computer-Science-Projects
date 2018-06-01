/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import static maze.Maze.WINDOW_BORDER;
 
public class Maze extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 441;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 420;
     boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;

    

    final int numRows = 21;
    final int numColumns = 21;

    final int PATH = 0;
    final int WALL = 1;
  
    
    int board[][] = {
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,PATH,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL }

    };
   
    int currentRow;
    int currentCol;
    int rowDir;
    int colDir;
    
    int numNPC = 4;
    Character npc[];
    int timeCount;
        
    
    static Maze frame1;

    public static void main(String[] args) {
        frame1 = new Maze();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Maze() {

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_RIGHT == e.getKeyCode())
                {
                    rowDir = 0;
                    colDir = 1;  
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    rowDir = 0;
                    colDir = -1;
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    rowDir = -1;
                    colDir = 0;
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    rowDir = 1;
                    colDir = 0;
                }

                repaint();
            }
        });
        init();
        start();
    }




    Thread relaxer;
////////////////////////////////////////////////////////////////////////////
    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////
    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////
    public void paint(Graphics gOld) {
        if (image == null || xsize != getSize().width || ysize != getSize().height) {
            xsize = getSize().width;
            ysize = getSize().height;
            image = createImage(xsize, ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(Color.cyan);

        g.fillRect(0, 0, xsize, ysize);

        int x[] = {getX(0), getX(getWidth2()), getX(getWidth2()), getX(0), getX(0)};
        int y[] = {getY(0), getY(0), getY(getHeight2()), getY(getHeight2()), getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.setColor(Color.red);
//horizontal lines
        for (int zi=1;zi<numRows;zi++)
        {
            g.drawLine(getX(0) ,getY(0)+zi*getHeight2()/numRows ,
            getX(getWidth2()) ,getY(0)+zi*getHeight2()/numRows );
        }
//vertical lines
        for (int zi=1;zi<numColumns;zi++)
        {
            g.drawLine(getX(0)+zi*getWidth2()/numColumns ,getY(0) ,
            getX(0)+zi*getWidth2()/numColumns,getY(getHeight2())  );
        }

        
//Display the walls and the secret passage.
        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] == WALL)
                {
                    g.setColor(Color.gray);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
             
            }
        }
        
        for (int index=0;index<numNPC;index++)
        {
            g.setColor(Color.green);
            g.fillRect(getX(0)+npc[index].getCurrentCol()*getWidth2()/numColumns,
            getY(0)+npc[index].getCurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
        }        
        
                {
                    g.setColor(Color.black);
                    g.fillRect(getX(0)+currentCol*getWidth2()/numColumns,
                    getY(0)+currentRow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.04;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {  
        
                boolean keepLooping = true;
                while (keepLooping)
                {
                    currentRow = (int)(Math.random()*numRows);
                    currentCol = (int)(Math.random()*numColumns);
                    if (board[currentRow][currentCol] == PATH)
                    {       
                        keepLooping = false;
                    }
                }
        rowDir = 0;
        colDir = 0;
                
        npc = new Character[numNPC];
        for (int index=0;index<numNPC;index++)
        {
            npc[index] = new Character();
            
                keepLooping = true;
                while (keepLooping)
                {
                    npc[index].setCurrentRow((int)(Math.random()*numRows));
                    npc[index].setCurrentCol((int)(Math.random()*numColumns));
                    if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] == PATH)
                    {       
                        keepLooping = false;
                    }
                }            
        }

        timeCount=0;

    }
    
    
/////////////////////////////////////////////////////////////////////////
    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            reset();
        }
        currentCol += colDir;
        currentRow += rowDir;
        if (board[currentRow][currentCol] != PATH)
        {
            currentCol -= colDir;
            currentRow -= rowDir;
        }
        colDir = 0;
        rowDir = 0;

        
            if(timeCount%25==24&&!safe)
        {
            for (int index=0;index<numNPC;index++)
            {
                if(npc[index].getCurrentRow()>currentRow)
                {
                    npc[index].setCurrentRow(-1);
                    if(board[npc[index].getCurrentRow()][npc[index].getCurrentCol()]==WALL)
                    {
                        npc[index].setRowDir(-1);
                        npc[index].setColDir(0);
                    }
                }
                if(npc[index].getCurrentRow()<currentRow)
                {
                    npc[index].setRowDir(1);
                    if(board[npc[index].getCurrentRow()][npc[index].getCurrentCol()]==WALL)
                    {
                        npc[index].setRowDir(-1);
                        npc[index].setColDir(0);
                    }
                }
                
                if(npc[index].getCurrentCol()>currentCol)
                {
                    npc[index].setCurrentCol(-1);
                    if(board[npc[index].getCurrentRow()][npc[index].getCurrentCol()]==WALL)
                    {
                        npc[index].setColDir(1);
                        npc[index].setRowDir(0);
                        
                    }
                }
                if(npc[index].getCurrentCol()<currentCol)
                {
                    npc[index].setCurrentCol(1);
                    if(board[npc[index].getCurrentRow()][npc[index].getCurrentCol()]==WALL)
                    {
                        npc[index].setCurrentCol(-1);
                        npc[index].setRowDir(0);
                    }
                }
                npc[index].setCurrentCol(npc[index].getCurrentCol()+npc[index].getColDir());
                npc[index].setCurrentRow(npc[index].getCurrentRow()+npc[index].getRowDir());
        
                
            }
        }
            
            if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] != PATH)
            {
                npc[index].setCurrentCol(npc[index].getCurrentCol() - npc[index].getColDir());
                npc[index].setCurrentRow(npc[index].getCurrentRow() - npc[index].getRowDir());
            }
            npc[index].setColDir(0);
            npc[index].setRowDir(0);           
        }            
 
        timeCount++;
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////
    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }
/////////////////////////////////////////////////////////////////////////
    public int getX(int x) {
        return (x + XBORDER + WINDOW_BORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE );
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    public int getWidth2() {
        return (xsize - 2 * (XBORDER + WINDOW_BORDER));
    }

    public int getHeight2() {
        return (ysize - 2 * YBORDER - WINDOW_BORDER - YTITLE);
    }
}

class Character
{
    private int currentRow;
    private int currentCol;
    private int rowDir;
    private int colDir;
    private Color color;
    
    public int getCurrentRow()
    {
        return(currentRow);
    }   
    public int getCurrentCol()
    {
        return(currentCol);
    }   
    public int getRowDir()
    {
        return(rowDir);
    }   
    public int getColDir()
    {
        return(colDir);
    }
        
    public void setCurrentRow(int _row)
    {
        currentRow = _row;
    }
    public void setCurrentCol(int _col)
    {
        currentCol = _col;
    }
    public void setColDir(int _colDir)
    {
        colDir = _colDir;
    }
    public void setRowDir(int _rowDir)
    {
        rowDir = _rowDir;
    }
    
}
