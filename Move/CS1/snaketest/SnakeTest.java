/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package snaketest;
 
import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class SnakeTest extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 500;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * YBORDER + 420;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
//The number or rows and columns for the board.
    final int numRows = 21;
    final int numColumns = 25;
//The possible values for the board.    
    final int EMPTY = 0;
    final int SNAKE = 1;
    final int BAD_BLOCK = 2;
    
    int board[][];
//The location of the head of the snake.
    int currentRow;
    int currentColumn;
//The direction that the snake is moving.    
    int rowDir;
    int columnDir;
    
    boolean gameOver;
    int timeCount;
    int score;
    int highScore;
    
//add or modify.  Variables already added for you.
    int numBadBlocks;   // The number of bad blocks to be placed on the board.
    int goThroughSelfCount;  // Keeps track of the number of moves the snake has made while in
                             // go through itself state.
    boolean goThroughSelf;  // Determines if the snake can go through itself or not.
//The location of the jump snake block.
    int jumpSnakeRow;
    int jumpSnakeCol;
    boolean jumpSnakeOn;    // Determines if the snake can jump or not.
    
    static SnakeTest frame1;
    public static void main(String[] args) {
        frame1 = new SnakeTest();
        frame1.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public SnakeTest() {

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
//Change the diretion of the snake depending on which arrow key has been pressed.                
                if (e.VK_RIGHT == e.getKeyCode())
                {
                    rowDir = 0;
                    columnDir = 1;
                }
                if (e.VK_LEFT == e.getKeyCode())
                {
                    rowDir = 0;
                    columnDir = -1;
                }
                if (e.VK_UP == e.getKeyCode())
                {
                    rowDir = -1;
                    columnDir = 0;
                }
                if (e.VK_DOWN == e.getKeyCode())
                {
                    rowDir = 1;
                    columnDir = 0;
                }
                
//add or modify.  Code that allows diagonal movement of the snake.  Use the key codes
// e.VK_HOME    e.VK_PAGE_UP     e.VK_PAGE_DOWN       e.VK_END

                
//add or modify.  Code that allows the head of the snake to jump to a random location.
//After doing the snake jump, disable the snake jump ability.  Use the key code   e.VK_SPACE

                
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
//add or modify.  Determine whether to draw the snake gray or green depending on the
//                state of the go through self variable.
                    g.setColor(Color.gray);
                    
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }         
                if (board[zrow][zcolumn] == BAD_BLOCK)
                {
                    g.setColor(Color.red);
                    g.fillRect(getX(0)+zcolumn*getWidth2()/numColumns,
                    getY(0)+zrow*getHeight2()/numRows,
                    getWidth2()/numColumns,
                    getHeight2()/numRows);
                }                 
            }    
        }

//add or modify.  Display the jump snake block in the color pink.

        
        if (gameOver)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("Game Over", 150, 350);             
        }        
        
        g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.PLAIN,12));
        g.drawString("Score: " + score, 20, 43);             
        g.drawString("High Score: " + highScore, 450, 43);   
//add or modify.  Display "Jump Snake On" when jump snake is turned on.

        
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
//Allocate memory for the 2D array that represents the board.
        board = new int[numRows][numColumns];
//Start the snake in the middle of the board.
        currentRow = numRows/2;
        currentColumn = numColumns/2;
        board[currentRow][currentColumn] = SNAKE;
        score = 1;
//Have the snake start moving to the right.
        rowDir = 0;
        columnDir = 1;
        
        gameOver = false;
        timeCount = 0;
//add or modify.  Code already added for you.
        numBadBlocks = 10;   //Place 10 bad red blocks on the board.
        goThroughSelf = false;    //When game starts, don't allow the snake to go through itself.
        goThroughSelfCount = 0;   //Set go through self count to 0.
//Find a random location for the jump snake block.
        jumpSnakeRow = (int)(Math.random()*numRows);
        jumpSnakeCol = (int)(Math.random()*numColumns);
        jumpSnakeOn = false;  //Set jump snake to off.

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
        
//add or modify.  When the head of the snake runs into the jump snake block, 
//                set the jump snake variable to on and move the jump snake block
//                to a new random location.

        
        
//Add a new bad red block at a random location on the board.        
        if(timeCount % 100 == 99)
        {    
//add or modify.  Remove all the old bad blocks and add the new random
//                bad blocks to the board.
                boolean keepLooping = true;
                while (keepLooping)
                {
                    int tempRow = (int)(Math.random()*numRows);
                    int tempCol = (int)(Math.random()*numColumns);
                    if (board[tempRow][tempCol] == EMPTY)
                    {
                        board[tempRow][tempCol] = BAD_BLOCK;       
                        keepLooping = false;
                    }
                }

                
        }
//Move the snake to the next location on the board.
        if (timeCount % 5 == 4)
        {
            currentRow += rowDir;
            currentColumn += columnDir;
//Game over if the snake runs into one of the boarders.            
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
                gameOver = true;
            }
//Game over if the snake runs into itself.    
//add or modify.  Don't do game over if snake is allowed to go through itself.
            else if (board[currentRow][currentColumn] == SNAKE)
                gameOver = true; 
//Game over if the snake runs into a bad red block.            
            else if (board[currentRow][currentColumn] == BAD_BLOCK)
                gameOver = true; 

//If it is not game over, then actually move the snake to its next location on the board.
            if (!gameOver)
            {
                board[currentRow][currentColumn] = SNAKE;
                score++;
//add or modify.  Starting at the multiples of 50 and for the next 10 snake moves, the
//                snake is allowed to go through itself.

                
                
            }
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
