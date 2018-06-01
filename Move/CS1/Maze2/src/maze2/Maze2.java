package maze2;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
 
public class Maze2 extends JFrame implements Runnable {
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
    
    sound bgSound = null;

    

    final int numRows = 21;
    final int numColumns = 21;

    final int PATH = 0;
    final int WALL = 1;
    final int SECR = 2;
    final int PACM = 3;
    final int PACN = 4;
    final int ENDG = 5;
    
  
    int board[][];
    int board1[][] = {
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,PATH,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,PATH,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,SECR,SECR,SECR,PATH,PATH,PATH,PATH,WALL,PATH,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,PATH,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,PATH,WALL,PATH,WALL,SECR,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{PACM,PATH,PATH,PATH,WALL,PATH,WALL,SECR,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PACN },
{WALL,PATH,WALL,SECR,WALL,PATH,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,SECR,WALL,PATH,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,WALL,SECR,WALL,PATH,WALL,PATH,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,PATH,WALL,PATH,WALL,PATH,PATH,PATH,PATH,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,WALL,PATH,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,PATH,WALL,ENDG,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL }

    };
    int board2[][] = {
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,WALL,PATH,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL },
{WALL,PATH,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL },
{WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL },
{WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL,WALL },
{WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
{WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
{PACM,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PACN },
{WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL },
{WALL,WALL,PATH,PATH,PATH,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,PATH,WALL,WALL },
{WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,WALL,WALL,PATH,PATH,PATH,PATH,PATH,WALL,WALL,PATH,PATH,WALL },
{WALL,WALL,WALL,PATH,PATH,PATH,WALL,WALL,PATH,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,PATH,WALL,ENDG,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL }
    };
    int board3[][] = {
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,PATH,WALL,PATH,PATH,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
{WALL,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,WALL,WALL,WALL },
{WALL,PATH,PATH,PATH,PATH,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,PATH,PATH,WALL },
{WALL,PATH,WALL,WALL,WALL,PATH,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL },
{WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL,WALL }

    };
   
//    int currentRow;
//    int currentCol;
//    int rowDir;
//    int colDir;
    Character player;
    Coin coin[];
    
    
    int numNPC = Start.getnumNPC();
    int numCoin=Start.getnumCoins();
    
    Character npc[];
    int timeCount;
    int count;
    int highScore=Start.getHighScore();
    int key;
    int colMove;
    int preVal;
    int iPortalX;
    int iPortalY;
    int oPortalX;
    int oPortalY;
    
    boolean gameOver;
    boolean keepLooping;
    boolean colCoin;
    boolean pacMan;
    boolean portalI;
    boolean portalO;
    boolean Easy;
    boolean Medium;
    boolean Hard;
    boolean gameOn;
    boolean secretPassage;
    int seizure=Start.getSeizure();
    boolean seiz =false;
    
       
    
    static public JFrame jframe;
    static public Dimension dim;
    
        
    
    static Maze2 frame1;

    public static void main(String[] args) {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame1 = new Maze2();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setLocation(dim.width / 2 - frame1.getWidth() / 2, dim.height / 2 - frame1.getHeight() / 2);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public Maze2() {

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
                    player.setRowDir(0);
                    player.setColDir(1);
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    player.setRowDir(0);
                    player.setColDir(-1);
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    player.setRowDir(-1);
                    player.setColDir(0);
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    player.setRowDir(1);
                    player.setColDir(0);
                }
                if (e.VK_F1 == e.getKeyCode())
                {
                    
                    Start.setCheats("");
                    if (Start.getCheats().charAt(0) == 'k'&&Start.getCheats().charAt(1) == 'i')
                    {
                        numNPC=0;  
                    }
                    if (Start.getCheats().charAt(0) == 'r'&&Start.getCheats().charAt(1) == 'e')
                    {
                        numNPC=Start.getnumNPC();
                    }
                    if (Start.getCheats().charAt(0) == 'c'&&Start.getCheats().charAt(1) == 'i')
                    {
                        numCoin=Start.getnumCoins();
                    }
                    
                }
                if (e.VK_SPACE == e.getKeyCode())
                {
                    key++;
                    if(key%2==0)
                    {
                        pacMan = true;
                    }
                    else if (key%2==1)
                    {
                        pacMan = false;
                    }
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
                    
                    g.setColor(Color.LIGHT_GRAY);
                    if(seiz)
                    {
                        int random1=(int)(Math.random()*255);
                        int random2=(int)(Math.random()*255);
                        int random3=(int)(Math.random()*255);
                        Color newCol = new Color(random1,random2,random3);
                        g.setColor(newCol);
                    }
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                if (board[zrow][zcolumn] == SECR)
                {
                    g.setColor(Color.LIGHT_GRAY);
                    if(secretPassage)
                        g.setColor(Color.pink);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                if (board[zrow][zcolumn] == PACM&&board[zrow][zcolumn] == PACN)
                {
                    g.setColor(Color.GREEN);

                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                if (board[zrow][zcolumn] == ENDG)
                {
                    g.setColor(Color.BLACK);

                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
             
            }
        }
        

        for (int index=0;index<numNPC;index++)
        {
            g.setColor(npc[index].getColor());
            g.fillRect(getX(0)+npc[index].getCurrentCol()*getWidth2()/numColumns,
            getY(0)+npc[index].getCurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            g.setFont(new Font("Impact",Font.PLAIN,12));
            g.drawString(npc[index].getName()+npc[index].getcoinVal(),getX(0)+npc[index].getCurrentCol()*getWidth2()/numColumns,
            getY(0)+npc[index].getCurrentRow()*getHeight2()/numRows);
        }        
        
        
        for (int index=0;index<numCoin;index++)
        {
            g.setColor(coin[index].getColor());
            g.fillRect(getX(0)+coin[index].getCurrentCol()*getWidth2()/numColumns,
            getY(0)+coin[index].getCurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Impact",Font.PLAIN,12));
            g.drawString(""+coin[index].getCoinVal(),getX(0)+coin[index].getCurrentCol()*getWidth2()/numColumns+15,
            getY(0)+coin[index].getCurrentRow()*getHeight2()/numRows);
        }
        {
            g.setColor(Color.red);
            g.fillRect(getX(0)+iPortalX*getWidth2()/numColumns,
            getY(0)+iPortalY*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Impact",Font.PLAIN,12));
            g.drawString("IN",getX(0)+iPortalX*getWidth2()/numColumns,
            getY(0)+iPortalY*getHeight2()/numRows+15);
        }
        {
            g.setColor(Color.red);
            g.fillRect(getX(0)+oPortalX*getWidth2()/numColumns,
            getY(0)+oPortalY*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Impact",Font.PLAIN,12));
            g.drawString("IN",getX(0)+oPortalX*getWidth2()/numColumns,
            getY(0)+oPortalY*getHeight2()/numRows+15);
        }
        {
            g.setColor(player.getColor());
            g.fillRect(getX(0)+player.getCurrentCol()*getWidth2()/numColumns,
            getY(0)+player.getCurrentRow()*getHeight2()/numRows,
            getWidth2()/numColumns,
            getHeight2()/numRows);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Impact",Font.PLAIN,12));
            g.drawString(player.getName()+player.getcoinVal(),getX(0)+player.getCurrentCol()*getWidth2()/numColumns,
            getY(0)+player.getCurrentRow()*getHeight2()/numRows);


        }

        if(gameOver)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("Game Over", 150, 350);  
        }
        if(colCoin&&!gameOver)
        {
            g.setColor(Color.RED);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("+ "+preVal, 150, 350+colMove);  
        }
        g.setFont(new Font("Impact",Font.PLAIN,15));
        g.drawString("Score: " + player.getcoinVal(), 20, 50);             
        g.drawString("High Score: " + highScore, 120, 50);    
        if(pacMan)
        {
            g.setColor(Color.BLACK);
            g.drawString("PACMAN", 220, 50); 
        }
        //g.drawString("PowerUps: " + powerupcount, 250, 50); 
            
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
        
        
        key++;
        if(key==1)
            board=board1;
        else if(key==2)
            board=board2;
        else if(key==3)
        {
            board=board3;
            key=0;
        }
        
//        if(board==board1)
//            board=board2;
//        else if(board==board2)
//            board=board1;
        player=new Character();
        
        player.setColor(Color.BLUE);
        player.setName("Player");//(JOptionPane.showInputDialog("Enter Name")));
                keepLooping = true;
                while (keepLooping)
                {
                    player.setCurrentRow((int)(Math.random()*numRows));
                    player.setCurrentCol((int)(Math.random()*numColumns/2));
                    if (board[player.getCurrentRow()][player.getCurrentCol()] == PATH)
                    {       
                        keepLooping = false;
                    }
                }
        player.setRowDir(0);
        player.setColDir(0);
        
        coin = new Coin[numCoin];
        for (int index=0;index<numCoin;index++)
        {
            coin[index]=new Coin();
            keepLooping = true;
            while (keepLooping)
            {
                coin[index].setCurrentRow((int)(Math.random()*numRows));
                coin[index].setCurrentCol((int)(Math.random()*numColumns/2));
                if (board[coin[index].getCurrentRow()][coin[index].getCurrentCol()] == PATH)
                {       
                    keepLooping = false;
                }
            }
        }
        npc = new Character[numNPC];
        for (int index=0;index<numNPC;index++)
        {
            npc[index] = new Character();
            
            
                keepLooping = true;
                while (keepLooping)
                {
                    npc[index].setCurrentRow((int)(Math.random()*numRows));
                    npc[index].setCurrentCol((int)(Math.random()*numColumns/2+numColumns/2));
                    if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] == PATH)
                    {       
                        keepLooping = false;
                    }
                }            
        }

        
            
        
        for(int index=0;index<numNPC;index++)
        {
            int col1=(int)(Math.random()*255);
            int col2=(int)(Math.random()*255);
            int col3=(int)(Math.random()*255);
            Color npcColor = new Color(col1,col2,col3);
            npc[index].setColor(npcColor);
            int random=(int)(Math.random()*40+20);
            npc[index].setSpeed(random);
        }
        for(int index=0;index<numCoin;index++)
        {
            //coin[index].setColor(Color.yellow);
            coin[index].setCoinVal((int)(Math.random()*4+1));
            coin[index].setCoinName(""+coin[index].getCoinVal());
            
        }
        for(int index=0;index<numNPC;index++)
        {
            if(index==0)
                npc[index].setName("Bob ");
            if(index==1)
                npc[index].setName("Joe ");
            if(index==2)
                npc[index].setName("Bill ");
            if(index==3)
                npc[index].setName("Wacko ");
            if(index==4)
                npc[index].setName("Mike ");
            if(index==5)
                npc[index].setName("Trev ");
            if(index==6)
                npc[index].setName("Sam ");
            if(index==7)
                npc[index].setName("Justin ");
            if(index==8)
                npc[index].setName("Vince ");
            if(index==9)
                npc[index].setName("Anna ");
        }
        timeCount=0;
        count=0;
        gameOver=false;
        colCoin=false;
        pacMan=false;
        secretPassage=false;
        preVal=0;
        colMove=0;
       keepLooping = true;
       while (keepLooping)
       {
           iPortalY=(int)(Math.random()*numRows);
           iPortalX=(int)(Math.random()*numColumns/4);
           if (board[iPortalY][iPortalX] == PATH)
           {       
               keepLooping = false;
               /*if(oPortalX+20<iPortalX)
               {
                   keepLooping=true;
               }*/
           }
       }
       keepLooping=true;
       while (keepLooping)
       {
           oPortalY=(int)(Math.random()*numRows);
           oPortalX=(int)(Math.random()*numColumns/2+numColumns/2);
           if (board[oPortalY][oPortalX] == PATH)
           {       
               
               keepLooping = false;
               /*if(oPortalX>iPortalX+20)
               {
                   keepLooping=true;
               }*/
           }
       }
       portalI=true;
       portalO=true;
       if(seizure==1)
         seiz =true;
       
       gameOn=false;
       

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
            bgSound = new sound("./darude.wav"); 
            highScore=0;
        }
        //if(gameOn)
        {
            if(player.getcoinVal()>highScore)
                highScore=player.getcoinVal();
            if(gameOver)
                return;
        if(bgSound.donePlaying)
        {
            bgSound = new sound("./darude.wav"); 
        }
            
            if(board[player.getCurrentRow()][player.getCurrentCol()]==PATH||board[player.getCurrentRow()][player.getCurrentCol()]==SECR)
            {
                player.setCurrentCol(player.getCurrentCol()+player.getColDir());
                player.setCurrentRow(player.getCurrentRow()+player.getRowDir());
            }
            if (board[player.getCurrentRow()][player.getCurrentCol()] == WALL||(board[player.getCurrentRow()][player.getCurrentCol()] == SECR&&!secretPassage))
            {
                player.setCurrentCol(player.getCurrentCol()-player.getColDir());
                player.setCurrentRow(player.getCurrentRow()-player.getRowDir());
            }
            if(!pacMan)
            {
                player.setRowDir(0);
                player.setColDir(0);
            }



            for (int index=0;index<numNPC-1;index++)
            {
                if(timeCount%npc[index].getSpeed()==npc[index].getSpeed()-1)
                {
                    if(npc[index].getCurrentRow()>player.getCurrentRow())
                    {
                        //npc[index].setColDir(0);
                        npc[index].setRowDir(-1);
                    }

                    if(npc[index].getCurrentRow()<player.getCurrentRow())
                    {
                        //npc[index].setColDir(0);
                        npc[index].setRowDir(1);
                    }


                    npc[index].Move();
                    if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] != PATH)
                    {
                        npc[index].MoveB();
                    }
                    npc[index].setColDir(0);
                    npc[index].setRowDir(0);
                }
            }
            for (int index=0;index<numNPC;index++)
            {
                if(timeCount%npc[index].getSpeed()==npc[index].getSpeed()-1)
                {
                    if(npc[index].getCurrentCol()>player.getCurrentCol())
                    {
                        //npc[index].setRowDir(0);
                        npc[index].setColDir(-1);
                    }
                    if(npc[index].getCurrentCol()<player.getCurrentCol())
                    {
                        //npc[index].setRowDir(0);
                        npc[index].setColDir(1);
                    }
                    
                    npc[index].Move();
                    //for(int index2=0;index2<numCoin;index2++)
                    {

                            if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] != PATH/*||(npc[index].getCurrentCol()==npc[index2].getCurrentCol()&&npc[index].getCurrentRow()==npc[index2].getCurrentRow())*/)
                            {
                                npc[index].MoveB();

                            }

                    }
                    npc[index].setColDir(0);
                    npc[index].setRowDir(0);
                }
            }

            /*for (int index=numNPC/2;index<numNPC;index++)
            {
                for(int index2=0;index2<numCoin;index2++)
                {
                    if(timeCount%npc[index].getSpeed()==npc[index].getSpeed()-1)
                    {
                        if(npc[index].getCurrentRow()>coin[index2].getCurrentRow())
                        {
                            //npc[index].setColDir(0);
                            npc[index].setRowDir(-1);
                        }

                        if(npc[index].getCurrentRow()<coin[index2].getCurrentRow())
                        {
                            //npc[index].setColDir(0);
                            npc[index].setRowDir(1);
                        }


                        npc[index].Move();
                        if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] != PATH)
                        {
                            npc[index].MoveB();
                        }
                        npc[index].setColDir(0);
                        npc[index].setRowDir(0);
                    }
                }
            }
            for (int index=numNPC/2;index<numCoin;index++)
            {
                for(int index2=0;index2<numCoin;index2++)
                {
                    if(timeCount%npc[index].getSpeed()==npc[index].getSpeed()-1)
                    {
                        if(npc[index].getCurrentCol()>coin[index2].getCurrentCol())
                        {
                            //npc[index].setRowDir(0);
                            npc[index].setColDir(-1);
                        }
                        if(npc[index].getCurrentCol()<coin[index2].getCurrentCol())
                        {
                            //npc[index].setRowDir(0);
                            npc[index].setColDir(1);
                        }
                        npc[index].Move();
                        if (board[npc[index].getCurrentRow()][npc[index].getCurrentCol()] != PATH)
                        {
                            npc[index].MoveB();
                        }
                        npc[index].setColDir(0);
                        npc[index].setRowDir(0);
                    }
                }
            }*/


            for (int index=0;index<numNPC;index++)
            {
                if(npc[index].getCurrentCol()==player.getCurrentCol()&&npc[index].getCurrentRow()==player.getCurrentRow())
                {
                    gameOver=true;
                }
            }
            for (int index=0;index<numCoin;index++)
            {
                if(player.getCurrentCol()==coin[index].getCurrentCol()&&player.getCurrentRow()==coin[index].getCurrentRow())
                {
                   colCoin=true;
                   count=0;
                   preVal=coin[index].getCoinVal();
                   player.setCoinVal(player.getcoinVal()+coin[index].getCoinVal());
                   int random=(int)(Math.random()*5+1);
                   coin[index].setCoinVal(random);
                   keepLooping = true;

                   while (keepLooping)
                   {
                       coin[index].setCurrentRow((int)(Math.random()*numRows));
                       coin[index].setCurrentCol((int)(Math.random()*numColumns/2));
                       if (board[coin[index].getCurrentRow()][coin[index].getCurrentCol()] == PATH)
                       {       
                           keepLooping = false;
                       }
                   }

                }

            }
            for (int index=0;index<numNPC;index++)
            {
                for(int index2=0;index2<numCoin;index2++)
                {
                    if(npc[index].getCurrentCol()==coin[index2].getCurrentCol()&&npc[index].getCurrentRow()==coin[index2].getCurrentRow())
                    {
                       npc[index].setCoinVal(npc[index].getcoinVal()+coin[index2].getCoinVal());
                       coin[index2].setCoinVal((int)(Math.random())*5+1);
                       keepLooping = true;
                       while (keepLooping)
                       {
                           coin[index2].setCurrentRow((int)(Math.random()*numRows));
                           coin[index2].setCurrentCol((int)(Math.random()*numColumns/2));
                           if (board[coin[index2].getCurrentRow()][coin[index2].getCurrentCol()] == PATH)
                           {       
                               keepLooping = false;
                           }
                       }
                    }
                }

            }
            if(colCoin)
            {
                count++;
                colMove-=2;

            }
            if(count%25==24)
            {
                colCoin=false;
                colMove=0;
                preVal=0;
            }

            if(player.getCurrentCol()==iPortalX&&player.getCurrentRow()==iPortalY&&portalI)
            {
                player.setCurrentCol(oPortalX);
                player.setCurrentRow(oPortalY);
                portalO=false;
            }
            if(player.getCurrentCol()==oPortalX&&player.getCurrentRow()==oPortalY&&portalO)
            {
                player.setCurrentCol(iPortalX);
                player.setCurrentRow(iPortalY);
                portalI=false;
            }
            if(player.getCurrentCol()!=oPortalX&&player.getCurrentRow()!=oPortalY)
            {
                portalO=true;
            }
            if(player.getCurrentCol()!=iPortalX&&player.getCurrentRow()!=iPortalY)
            {
                portalI=true;
            }
            if(player.getcoinVal()>=5&&player.getcoinVal()<=20)
                secretPassage=true;
            else
                secretPassage=false;
            
            if(board[player.getCurrentRow()][player.getCurrentCol()]==ENDG)
            {
                reset();
            }
            else if(board2[player.getCurrentRow()][player.getCurrentCol()]==ENDG)
            {
                reset();
            }
            if(player.getCurrentCol()==0)
            {
                player.setCurrentCol(numColumns-2);
            }
            else if(player.getCurrentCol()==numColumns-1)
            {
                player.setCurrentCol(1);
            }
            





            timeCount++;
        }
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
    private int speed;
    private int coinVal;
    private Color color;
    private String name;

    
    public String getName()
    {
        return(name);
    }
    public int getSpeed()
    {
        return(speed);
    }
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
    public int getcoinVal()
    {
        return(coinVal);
    }
    public Color getColor()
    {
        return(color);
    } 
    public int Move()
    {
        currentRow+=rowDir;
        currentCol+=colDir;
        return(0);
    } 
    public int MoveB()
    {
        currentRow-=rowDir;
        currentCol-=colDir;
        return(0);
    } 
    public void setName(String _name)
    {
        name = _name;
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
    public void setColor(Color _color)
    {
        color = _color;
    }
    public void setSpeed(int _speed)
    {
        speed = _speed;
    }
    public void setCoinVal(int _coinVal)
    {
        coinVal = _coinVal;
    }
}
class Coin
{
    
    private int currentRow;
    private int currentCol; 
    private int coinVal;
    static private Color color = Color.yellow;
    private String coin;
    
    
    
    public int getCurrentRow()
    {
        return(currentRow);
    }   
    public int getCurrentCol()
    {
        return(currentCol);
    } 
    public int getCoinVal()
    {
        return(coinVal);
    } 
    public Color getColor()
    {
        return(color);
    } 
    public String getCoinName()
    {
        return(coin);
    }
    
    public void setCurrentRow(int _row)
    {
        currentRow = _row;
    }
    public void setCurrentCol(int _col)
    {
        currentCol = _col;
    }
    public void setCoinVal(int _coinVal)
    {
        coinVal = _coinVal;
    }
    public void setCoinName(String _coin)
    {
        coin = _coin;
    }
}
class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    @Override
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
    //    System.out.println("Format: " + format);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            try (SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info)) {
                source.open(format);
                source.start();
                int read = 0;
                byte[] audioData = new byte[16384];
                while (read > -1){
                    read = ais.read(audioData,0,audioData.length);
                    if (read >= 0) {
                        source.write(audioData,0,read);
                    }
                }
                donePlaying = true;

                source.drain();
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException exc) {
            System.out.println("error: " + exc.getMessage());
        }
    }

}
