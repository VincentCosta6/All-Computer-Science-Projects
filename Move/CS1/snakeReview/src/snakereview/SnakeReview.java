
package snakereview;
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeReview extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 800;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 600;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
    
    final int numRows = 57;
    final int numColumns = 53;
    final int EMPTY = 0;
    final int SNAKE = 1;
    final int BAD_BOX = 2;
    final int POWER_UP=3;
    
    int board[][];
    int currentRow;
    int currentColumn;
    int rowDir;
    int columnDir;
    int timeCount;
    int score;
    int highScore;
    int randomX;
    int randomY;
    int time;
    int numBadBoxToAdd;
    int randomC;
    int powerupcount;
    
    boolean gameOver;
    boolean powerUpI;
    boolean keepLooping;
    boolean powerUpT;
    boolean powerUpR;
    boolean powerUpC;
    
    static SnakeReview frame1;
    public static void main(String[] args) {
        frame1 = new SnakeReview();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public SnakeReview() {

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
                if (e.VK_D == e.getKeyCode()&&columnDir!=-1)
                {
                    rowDir = 0;
                    columnDir = 1;
                }
                if (e.VK_A == e.getKeyCode()&&columnDir!=1)
                {
                    rowDir = 0;
                    columnDir = -1;
                }
                if (e.VK_W == e.getKeyCode()&&rowDir!=1)
                {
                    rowDir = -1;
                    columnDir = 0;
                }
                if (e.VK_S == e.getKeyCode()&&rowDir!=-1)
                {
                    rowDir = 1;
                    columnDir = 0;

                }
                if (e.VK_SPACE == e.getKeyCode())
                {
                    if(powerupcount>=1)
                    {
                        time=0;
                        powerUpI=true;
                        powerupcount--;
                    }
                }
                if (e.VK_E == e.getKeyCode())
                {
                    if(powerupcount>=1)
                    {
                        powerUpT=true;
                        powerupcount--;
                    }
                }
                if (e.VK_Q == e.getKeyCode())
                {
                    if(powerupcount>=5)
                    {
                        powerUpR=true;
                        powerupcount-=5;
                    }
                }
                if (e.VK_R == e.getKeyCode())
                {
                    if(powerupcount>=3)
                    {
                        powerUpC=true;
                        powerupcount-=3;
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

//Display the objects of the board
        

        for (int zrow=0;zrow<numRows;zrow++)
        {
            for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
            {
                if (board[zrow][zcolumn] == SNAKE)
                {
                    if(powerUpI)
                    {
                    if(randomC==0)
                        g.setColor(Color.RED);
                    else if(randomC==1)
                        g.setColor(Color.BLUE);
                    else if(randomC==2)
                        g.setColor(Color.GREEN);
                    else if(randomC==3)
                        g.setColor(Color.ORANGE);
                    else if(randomC==4)
                        g.setColor(Color.YELLOW);
                    }
                    else
                    g.setColor(Color.gray);
                    g.fillOval(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }         
                if (board[zrow][zcolumn] == BAD_BOX)
                {
                    g.setColor(Color.red);
                    g.fillOval(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }   
                if(zrow==currentRow&&zcolumn==currentColumn&&powerUpI)
                {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }
                if (board[zrow][zcolumn] == POWER_UP)
                {
                    g.setColor(Color.YELLOW);
                    g.fillOval(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }  
            }    
        }
        
        if (gameOver)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("Game Over", 150, 350);             
        }        
        
        g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.PLAIN,20));
        g.drawString("Score: " + score, 20, 50);             
        g.drawString("High Score: " + highScore, 450, 50);             
        g.drawString("PowerUps: " + powerupcount, 250, 50); 
        gOld.drawImage(image, 0, 0, null);
    }


////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = 0.1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
//Allocate memory for the 2D array that represents the board.
        board = new int[numRows][numColumns];
        if(!powerUpR)
        {
            currentRow = numRows/2;
            currentColumn = numColumns/2;
            rowDir = 0;
            columnDir = 1;
            time=0;
            numBadBoxToAdd=0;
            score = 1;
            powerupcount=3;
        }
        board[currentRow][currentColumn] = SNAKE;
        gameOver = false;
        timeCount = 0;
        randomX=(int)(Math.random()*numColumns);
        randomY=(int)(Math.random()*numRows);
        board[randomY][randomX]=POWER_UP;
        powerUpI=false;
        powerUpT=false;
        powerUpR=false;
        powerUpC=false;
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
            highScore = 0;
        }

        if (gameOver)
        {
            if (score > highScore)
                highScore = score;
            return;
        }
        
        
        if(timeCount % 20 == 19)
        {    
            numBadBoxToAdd++;
            boolean keepLooping = true; 
                for(int start=0;start<numBadBoxToAdd;start++)
                {
                    int tempRow = (int)(Math.random()*numRows);
                    int tempCol = (int)(Math.random()*numColumns);
                    if (board[tempRow][tempCol] == EMPTY)
                    {

                        board[tempRow][tempCol] = BAD_BOX;       
                        keepLooping = false;
                    }
                }
            
                
        }
     
        //snake speed
        //if (timeCount % 5 == 4)
        {
            currentRow += rowDir;
            currentColumn += columnDir;
            if (currentColumn < 0)
            {
                gameOver = true;
            }
            else if (currentRow < 0)
            {
                gameOver = true;
            }
            else if (currentRow > numRows-1)
            {
                gameOver = true;
            }
            else if (currentColumn > numColumns-1)
            {
                currentColumn--;
                if(currentRow<=0)
                {
                    columnDir=0;
                    rowDir=1;
                }
                else if(currentRow>=numRows-1)
                {
                    columnDir=0;
                    rowDir=-1;
                }
                else if(Math.random()>0.50)
                {
                    columnDir=0;
                    rowDir=1;
                }
                else
                {
                    columnDir=0;
                    rowDir=-1;
                }
            }
            else if (board[currentRow][currentColumn] == SNAKE&&!powerUpI)     // run into yourself
                gameOver = true; 
            else if (board[currentRow][currentColumn] == BAD_BOX&&!powerUpI)
                gameOver = true; 
            else if(board[currentRow][currentColumn] == POWER_UP)
            {
                randomX=(int)(Math.random()*numColumns);
                randomY=(int)(Math.random()*numRows);
                board[randomY][randomX]=POWER_UP;
                board[currentRow][currentColumn]=EMPTY;
                powerupcount++;
            }
            if(powerUpI)
            {
                time++;
            }
            if(time>=32)
            {
                powerUpI=false;
                time=0;
            }
           
            if (!gameOver)
            {
                board[currentRow][currentColumn] = SNAKE;
                score++;
            }
        }
        if(timeCount%2==1&&powerUpI)
        {
            randomC =(int)(Math.random()*4);
        }
        if(timeCount%60==59)
        {
            keepLooping=true;
            board[randomY][randomX]=EMPTY;
            while(keepLooping)
            {
               // randomX=(int)(Math.random()*numColumns);
                //randomY=(int)(Math.random()*numRows);
                if(board[randomY][randomX]==EMPTY)
                {
                    board[randomY][randomX]=POWER_UP;
                    keepLooping=false;
                }
            }
        }
        if(powerUpT)
        {
            currentRow=(int)(Math.random()*numRows);
            currentColumn=(int)(Math.random()*numColumns);
            powerUpT=false;
        } 
        if(score%100==99)
            powerupcount++;
        if (score > highScore)
                highScore = score;
        if(powerUpR)
        {
            reset();
            powerUpR=false;
        }
        if(powerUpC)
        {
            for (int zrow=0;zrow<numRows;zrow++)
            {
                for (int zcolumn=0;zcolumn<numColumns;zcolumn++)
                {
                    if(board[zrow][zcolumn]==EMPTY)
                        board[zrow][zcolumn]=EMPTY;
                }
            }
            numBadBoxToAdd=1;
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
