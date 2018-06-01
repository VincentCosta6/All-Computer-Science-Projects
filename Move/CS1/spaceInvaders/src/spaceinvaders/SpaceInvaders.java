/* Vincent Costa
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spaceinvaders;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import static spaceinvaders.SpaceInvaders.WINDOW_BORDER;

public class SpaceInvaders extends JFrame implements Runnable {
    static final int XBORDER = 20;
    static final int YBORDER = 20;
    static final int YTITLE = 30;
    static final int WINDOW_BORDER = 8;
    static final int WINDOW_WIDTH = 2*(WINDOW_BORDER + XBORDER) + 500;
    static final int WINDOW_HEIGHT = YTITLE + WINDOW_BORDER + 2 * 
                                     YBORDER + 500;
    
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
//Variables related to the cannon.
    int cannonXPos;
    int cannonYPos;
    int alienReset;
    int key;
    int up;
    int down;
    int change;
    
    
//Variables related to the aliens.
    int numAliens = 10;
    int alienDir;
    int alienXPos[] = new int[numAliens];
    int alienYPos[] = new int[numAliens];
    boolean alienActive[] = new boolean[numAliens];
    int alienValue[] = new int[numAliens];
    
//Variables related to the explosions.
    int explosionXPos[] = new int[numAliens];
    int explosionYPos[] = new int[numAliens];
    boolean explosionActive[] = new boolean[numAliens];
    double explosionScale[] = new double[numAliens];
    
//Variables related to the cannonballs.    
    int numCannonBalls = 50000;
    int currentCannonBallIndex;
    int cannonBall1XPos[] = new int[numCannonBalls];
    int cannonBall1YPos[] = new int[numCannonBalls];
    boolean cannonBallActive[] = new boolean[numCannonBalls];
    
    int cannonBall2XPos[] = new int[numCannonBalls];
    int cannonBall2YPos[] = new int[numCannonBalls];
    boolean cannonBallActive2[] = new boolean[numCannonBalls];
    int cannonBallCount;
    boolean superCannon;
    boolean alienDrop[]= new boolean[numAliens];

    int highScore;
    int score;
    boolean gameOver;
    int timeCount;
    
//add or modify.  Below already added for you.
    boolean alienFast;   //Determines what speed the aliens should move down.
    boolean AlienFast;                     //When alienFast is false, the aliens move down at their normal speed.
                         //When alienFast is true, the aliens move down 3 times their normal speed.
    
    static SpaceInvaders frame;
    public static void main(String[] args) {
        frame = new SpaceInvaders();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public SpaceInvaders() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();
                    if (gameOver)
                        return;
 //Fire the next cannonball.
                    if(!superCannon)
                    {
                        cannonBallCount++;
                        cannonBall1XPos[currentCannonBallIndex] = cannonXPos;
                        cannonBall1YPos[currentCannonBallIndex] = cannonYPos;
                        cannonBallActive[currentCannonBallIndex] = true;                 
                        currentCannonBallIndex++;
                    
                    if (currentCannonBallIndex >= numCannonBalls)
                        currentCannonBallIndex = 0;
                    }
                    else if (superCannon)
                    {
                        cannonBallCount+=2;
                        cannonBall1XPos[currentCannonBallIndex] = cannonXPos-15;
                        cannonBall1YPos[currentCannonBallIndex] = cannonYPos;
                        cannonBallActive[currentCannonBallIndex] = true;    
                        cannonBall2XPos[currentCannonBallIndex] = cannonXPos+15;
                        cannonBall2YPos[currentCannonBallIndex] = cannonYPos;
                        cannonBallActive2[currentCannonBallIndex] =true;
                        currentCannonBallIndex++;
                    }

                        if (currentCannonBallIndex >= numCannonBalls)
                            currentCannonBallIndex = 0;
                }
                if (e.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                if(e.BUTTON2 ==e.getButton())
                {
                    
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
        if (gameOver)
            return;
//Make the cannon x position match the cursor x position. 
          cannonXPos = e.getX() - getX(0);
        repaint();
      }
    });

    
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_UP == e.getKeyCode()) 
                {
                    if(gameOver)
                    return;
 //Fire the next cannonball.

                    if(!superCannon)
                    {
                        cannonBallCount++;
                        for(int index=0;index<numAliens;index++)
                        {
                        cannonBall1XPos[currentCannonBallIndex] = alienXPos[index];
                        cannonBall1YPos[currentCannonBallIndex] = alienYPos[index];
                        cannonBallActive[currentCannonBallIndex] = true;           
                        currentCannonBallIndex++;
                        if (currentCannonBallIndex >= numCannonBalls)
                            currentCannonBallIndex = 0;
                        }
                    }
                    else if(superCannon)
                    {
                        cannonBallCount+=2;
                        cannonBall1XPos[currentCannonBallIndex] = cannonXPos-15;
                        cannonBall1YPos[currentCannonBallIndex] = cannonYPos;
                        cannonBallActive[currentCannonBallIndex] = true;    
                        cannonBall2XPos[currentCannonBallIndex] = cannonXPos+15;
                        cannonBall2YPos[currentCannonBallIndex] = cannonYPos;
                        cannonBallActive2[currentCannonBallIndex] =true;
                        currentCannonBallIndex++;
                    }
                    up++;
                    System.out.println("Up:"+up);
                    
                } 
                else if (e.VK_DOWN == e.getKeyCode()) 
                {
                    String change1;

                    
                    change1 = read();
                    if (change1.charAt(0) == 'k')
                    {
                        score+=numAliens;
                        numAliens=0;
                        
                    }
                    else if (change1.charAt(0) == 'r')
                    {
                        System.out.println("Please enter a Value: ");
                        int change2=readInt();
                        reset();
                            numAliens=change2;
                    }
                    else if (change1.charAt(0) == 's')
                    {
                        alienDir=0;
                    }   
                    
 
                    
                }
                else if (e.VK_LEFT == e.getKeyCode()) {
                    
                    key++;
                    if (key==1)
                        superCannon=true;
                    else if(key>=2)
                    {
                        superCannon=false;
                        key=0;
                    }
                    System.out.println("Left");
                    
                } else if (e.VK_RIGHT == e.getKeyCode()) {
                    
                    key++;
                    change = readInt();
                    for(int index=0;index<numAliens;index++)
                    {
                    if(key==1)
                    {
                        alienValue[index]=change;
                        
                    }
                    if(key==2)
                    {
                        alienValue[index] = (int)(Math.random()*5+2); 
                        
                    }
                   
                    }
                    key=0;
                    System.out.println("Right");
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
//Draw the cannon.
        g.setColor((Color.red));
        
        drawCannon(getX(cannonXPos),
        getYNormal(cannonYPos),0,1,1);
        

//Draw the aliens.
        for (int index=0;index<numAliens;index++)
        {
            if (alienActive[index])
            {
//add or modify.  Draw aliens either green or cyan depending on the value of alienFast.
                g.setColor((Color.green));
                if(alienFast)
                g.setColor(Color.cyan);
                drawAlien(getX(alienXPos[index]),getYNormal(alienYPos[index]),0,1,1,alienValue[index]);
                
                
            }
        }
//Draw the cannonballs.
        for (int index=0;index<numCannonBalls;index++)
        {
            
            if (cannonBallActive[index])
            {
                g.setColor((Color.black));
                drawCannonBall(getX(cannonBall1XPos[index]),
                getYNormal(cannonBall1YPos[index]),0,1,1);
                
            }
            
            if(superCannon)
            {
                if(cannonBallActive2[index])
                {
                    g.setColor((Color.black));
                    drawCannonBall(getX(cannonBall2XPos[index]),
                    getYNormal(cannonBall2YPos[index]),0,1,1);
                }
            }
        }
//Draw the explosions.        
        for (int index=0;index<numAliens;index++)
        {
            if (explosionActive[index])
            {
                g.setColor((Color.yellow));
                drawCannonBall(getX(explosionXPos[index]),
                getYNormal(explosionYPos[index]),0,
                explosionScale[index],explosionScale[index]);
            }
        }        
//Display the score.
        g.setFont(new Font("Impact",Font.ITALIC,20));
        g.drawString("Score: " + score, 20, 50);         

//Display the high score.
        g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.ITALIC,20));
        g.drawString("High Score: "+highScore , 350, 50);    
        g.setFont(new Font("Impact",Font.ITALIC,20));
        g.drawString("Shots Fired: " + cannonBallCount, 225, 50);   
 
//If game over then display game over.
        if (gameOver)
        {
            g.setColor(Color.black);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("Game Over", 150, 350);             
        }
   
        gOld.drawImage(image, 0, 0, null);
    }

////////////////////////////////////////////////////////////////////////////
    public void drawAlien(int xpos,int ypos,double rot,
    double xscale,double yscale,int value)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        int xval[] = {10,-10,-10,-5,-10,0,10,5,10};
        int yval[] = {-20,-20,-5,-5,10,0,10,-5,-5};
        g.fillPolygon(xval,yval,xval.length);
        g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.ITALIC,12));
        g.drawString(""+value,-3,-7); 

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }          
////////////////////////////////////////////////////////////////////////////
    public void drawCannonBall(int xpos,int ypos,double rot,
            double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        g.fillOval(-5, -5, 10, 10);
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawCannon(int xpos,int ypos,double rot,
            double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        if(!superCannon)
        {
        int xval[] = {  0, 10, 10,-10,-10, 0};
        int yval[] = { -15, -10,10,10, -10,-15};
        g.fillPolygon(xval,yval,xval.length);
        }
        else
        {
            int xval[] = {0,15,20,20,-20,-20,-15,0};
            int yval[] = {0,-15,-15,10,10,-15,-15,0};
            g.fillPolygon(xval,yval,xval.length);
        }
       
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
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
        score = 0;
        timeCount = 0;
        gameOver = false;
//Init values for the cannon.
        alienReset=0;
        cannonBallCount=0;
        superCannon=false;
        key=0;
        change=1;
        

        cannonYPos = 0;
//Init values for the cannonball.
        currentCannonBallIndex = 0;
        for (int index=0;index<numCannonBalls;index++)
            cannonBallActive[index] = false;
//Init values for the aliens and the explosions.
        alienDir = 2;
//add or modify.  Set the inital value for alienFast.

        for (int index=0;index<numAliens;index++)
        {
//add or modify.  Have alienValue now be random values from 2 to 5.
            alienFast=false;
            AlienFast=false;
            alienDrop[index]=false;

            alienValue[index]=(int)(Math.random()*4+2);
            alienActive[index] = true;
            explosionActive[index] = false;       
            alienXPos[index] = (int)(Math.random()*(getWidth2()*3/4)+getWidth2()/8);
//add or modify.  Have the aliens initially appear randomly in the upper quarter of the game area.
            alienYPos[index] = (int)(Math.random()*getHeight2()/4+getHeight2()/1.4 );

        }
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) 
            {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            reset();
            cannonXPos = getWidth2()/2;
            highScore = 0;
        }
        
        if (gameOver)
        {
            if (highScore < score)
                highScore = score;
            return;
        }
        
//Check to see if a cannonball has hit an alien.        
        for (int index=0;index<numCannonBalls;index++)
        {
            for (int index2=0;index2<numAliens;index2++)
            {
                if (cannonBall1XPos[index]>alienXPos[index2]-10 &&
                    cannonBall1XPos[index]<alienXPos[index2]+10 &&  
                    cannonBall1YPos[index]>alienYPos[index2]-10 &&
                    cannonBall1YPos[index]<alienYPos[index2]+10 &&
                    alienActive[index2] && cannonBallActive[index]  )
                {                   
                    cannonBallActive[index] = false;
                    alienValue[index2]-=1;
                    if(alienValue[index2]==0)
                    {
                        alienDrop[index2]=false;
                        alienXPos[index2] = (int)(Math.random()*(getWidth2()*3/4)+getWidth2()/8);
                        alienYPos[index2] = (int)(getHeight2() );
                        alienValue[index2] = (int)(Math.random()*5+2); 
                        explosionActive[index2] = true;
                        explosionXPos[index2] = cannonBall1XPos[index]; 
                        explosionYPos[index2] = cannonBall1YPos[index]; 
                        explosionScale[index2] = 1; 
                        score++;
                    }
 
                    }
                    if (cannonBall2XPos[index]>alienXPos[index2]-10 &&
                    cannonBall2XPos[index]<alienXPos[index2]+10 &&  
                    cannonBall2YPos[index]>alienYPos[index2]-10 &&
                    cannonBall2YPos[index]<alienYPos[index2]+10 &&
                    alienActive[index2] && cannonBallActive2[index]  )
                {                   
                    cannonBallActive2[index] = false;
                    alienValue[index2]-=1;
                    if(alienValue[index2]==0)
                    {
                        alienDrop[index2]=false;
                        alienXPos[index2] = (int)(Math.random()*(getWidth2()*3/4)+getWidth2()/8);
                        alienYPos[index2] = (int)(getHeight2() );
                        alienValue[index2] = (int)(Math.random()*5+2); 
                        explosionActive[index2] = true;
                        explosionXPos[index2] = cannonBall2XPos[index]; 
                        explosionYPos[index2] = cannonBall2YPos[index]; 
                        explosionScale[index2] = 1; 
                        score++;
 
                    }
                }
              
            }
        }

        for (int index=0;index<numAliens;index++)
        {
            if (explosionActive[index])
            {
                explosionScale[index] +=.5;
                if (explosionScale[index] > 8)
                    explosionActive[index] = false;
            }
        }

        for (int index=0;index<numCannonBalls;index++)
        {
            if (cannonBallActive[index])
            {
                
                cannonBall1YPos[index]+=7;
  

            }
            if(superCannon)
            {
            if(cannonBallActive2[index])
                 cannonBall2YPos[index]+=7;
            }
        } 

        for (int index=0;index<numAliens;index++)
        {
            if (alienActive[index])
            {
                if(!alienDrop[index])
                {
                    alienXPos[index] += alienDir;
                    if (alienXPos[index] <= 0)
                        alienDir = 2;
                    if (alienXPos[index] >= getWidth2())
                        alienDir = -2;
                }
                else if(alienDrop[index])
                {
                    alienYPos[index]-=6;
                }
            }
        }        
//Make the aliens move down at a slow rate.
        if (timeCount % 5 == 4)
        {
            for (int index=0;index<numAliens;index++)
            {
                if(alienYPos[index]<=(int)getHeight2()/2)
                        alienFast=true;
                if (alienActive[index])
                {

                    if(!alienFast)
                        alienYPos[index]-=2;
                    else if(alienFast)
                        alienYPos[index]-=6;
                    if(cannonXPos>alienXPos[index]-10 &&
                    cannonXPos<alienXPos[index]+10 &&  
                    cannonYPos>alienYPos[index]-10 &&
                    cannonYPos<alienYPos[index]+10 
                      )
                        gameOver=true;
                
                    if (alienYPos[index] <= 0)
                    {

                        alienReset++;
                        alienXPos[index] = (int)(Math.random()*(getWidth2()*3/4)+getWidth2()/8);
                        alienYPos[index] = (int)(getHeight2() );  
                        alienDrop[index] = false;
                
                        if(alienReset==numAliens)
                            {
                                alienFast=false;
                                alienReset=0;
                            }
                    }
                }
            }
            

          

        }
 
        timeCount++;
        for (int index=0;index<numCannonBalls;index++)
            {
            if(cannonBall1YPos[index]>=getHeight2())
                cannonBallActive[index] = false;
            if(cannonBall2YPos[index]>=getHeight2())
                cannonBallActive2[index] = false;
            }
  
                if(timeCount%200==199)
                {
                    int random= (int)(Math.random()*numAliens);
                    alienDrop[random]=true;
                }
            for(int index=0;index<numAliens;index++)
            {
                if(alienValue[index]<=0)
                    {
                        alienDrop[index]=false;
                        alienXPos[index] = (int)(Math.random()*(getWidth2()*3/4)+getWidth2()/8);
                        alienYPos[index] = (int)(getHeight2() );
                        alienValue[index] = 1;//(int)(Math.random()*5+2); 
                        score++;
                    }
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
    public static int readInt()
    {
        String str = read();
        int num = Integer.parseInt(str.trim());
        return (num);
    }
   public static String read()
        {
        byte [] buffer = new byte[100];
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
