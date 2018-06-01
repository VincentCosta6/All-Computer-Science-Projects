
package spaceship;

import java.io.*;
import javax.sound.sampled.*;
import java.awt.*;
//import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;

public final class Spaceship extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 1920;
    static final int WINDOW_HEIGHT = 1080;
    final int XBORDER = 20;
    final int YBORDER = 20;
    final int YTITLE = 25;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;

    sound zsound = null;
    sound bgSound = null;
    Image outerSpaceImage;
    Image stars;
    Image explosion;

//variables for rocket.
    Image rocketImage;
    int rocketXPos;
    int rocketYPos;
    int rocketRPos;
    int rocketXSpeed;
    int rocketRight;
    int rocketLeft;
    int rocketTop;
    int rocketBottom;
    int score;
    int whichStarHit;
    boolean gameOver;
    int numLives;
    
    int rocketLength;
    int rocketHeight;
    
    double forceScale;
    boolean forceField;
    
    
    
    int checkpoint;
    
    Missile missiles[]=new Missile[Missile.numMissiles];
    
    int numStars= WINDOW_HEIGHT/8;
    int starXPos[]=new int [numStars];
    int starYPos[]=new int [numStars];
    int starRight;
    int starLeft;
    int starTop;
    int starBottom;
    int key;
    int timeCount;
    
    

    static Spaceship frame;
    public static void main(String[] args) {
        frame = new Spaceship();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Spaceship() {
        addMouseListener(new MouseAdapter() {
            
            @Override
            public void mousePressed(MouseEvent e) {
                if (MouseEvent.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();

                }
                if(MouseEvent.BUTTON2 ==e.getButton())
                {
                    for(int index=0;index<missiles.length;index++)
                    {
                        Missile.missileCurrent++;
                        missiles[index].missileActive=true;
                        missiles[index].missileYPos=rocketYPos;
                        missiles[index].missileXPos=rocketXPos;
                    }
                    
                }
                if (MouseEvent.BUTTON3 == e.getButton()) {
                    //right button
                    reset();
                }
                repaint();
            }
        });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseDragged(MouseEvent e) {
        repaint();
      }
    });

    addMouseMotionListener(new MouseMotionAdapter() {
      @Override
      public void mouseMoved(MouseEvent e) {
          
          
          rocketYPos = e.getY();
          
          
        repaint();
      }
    });

        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                checkpoint=0;
                if (KeyEvent.VK_UP == e.getKeyCode()) 
                {
                   rocketYPos+=10;
                   
                }
                else if (KeyEvent.VK_DOWN == e.getKeyCode()) 
                {
                    rocketYPos-=10;
                    
                } 
                else if (KeyEvent.VK_LEFT == e.getKeyCode()) 
                {
                    
                    rocketXSpeed++;
                    if(rocketXSpeed>20)
                        rocketXSpeed--;
                    if(rocketXSpeed>0)
                    {
                        for(int index=0;index<missiles.length;index++)
                        {
                            missiles[index].Right=false;
                        }
                    }
                } 
                else if (KeyEvent.VK_RIGHT == e.getKeyCode()) 
                {
                    rocketXSpeed--;
                    if(rocketXSpeed<-20)
                        rocketXSpeed++;
                    if(rocketXSpeed<0)
                    {
                        for(int index=0;index<missiles.length;index++)
                        {
                            missiles[index].Right=true;
                        }
                    }

                }
                
                else if (KeyEvent.VK_NUMPAD0 == e.getKeyCode()) 
                {
                    for(int index=0;index<missiles.length;index++)
                    {
                            Missile.missileCurrent++;
                            missiles[index].missileActive=true;
                            missiles[index].missileYPos=rocketYPos;
                            missiles[index].missileXPos=rocketXPos;
                    }
                   
                    

                }
                else if (KeyEvent.VK_E == e.getKeyCode()) 
                {
                    rocketRPos+=45;
                }
                else if (KeyEvent.VK_SPACE == e.getKeyCode()) 
                {
                    
                    forceField=true;
                    
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
    @Override
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
        g.setColor(Color.black);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);
        
        checkpoint=0;

        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        g.drawImage(outerSpaceImage,getX(0),getY(0),
                getWidth2(),getHeight2(),this);
        g.setColor(Color.red);
        for(int index=0;index<missiles.length;index++)
        {
            if(missiles[index].missileActive)
            {
                drawCircle(missiles[index].missileXPos,getYNormal(missiles[index].missileYPos),0,1,1);
            }
        }
        g.setColor(Color.red);
        if(forceField)
            drawExplosion(explosion,rocketXPos,getYNormal(rocketYPos),0,forceScale,forceScale);
        for(int index=0;index<missiles.length;index++)
        {
        if(missiles[index].Right)
            drawRocket(rocketImage,getX(rocketXPos),getYNormal(rocketYPos),rocketRPos,2.0,2.0 );
        else
            drawRocket(rocketImage,getX(rocketXPos),getYNormal(rocketYPos),rocketRPos,-2.0,2.0 );
        }
        
        g.setColor(Color.white);
        for(int index=0;index<numStars;index++)
            drawStar(stars,getX(starXPos[index]),getYNormal(starYPos[index]),0,1,1);
        g.setColor(Color.black);
        g.setFont(new Font("Impact",Font.ITALIC,20));
        g.drawString("Score: "+score , 475, 50); 
        g.drawString("Lives: "+numLives , 350, 50); 
        
        
        if (gameOver)
        {
            g.setColor(Color.red);
            g.setFont(new Font("Impact",Font.ITALIC,50));
            g.drawString("Game Over", 1080/2, getWidth2()/2);             
        }

        gOld.drawImage(image, 0, 0, null);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawStar(Image image,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        int width = stars.getWidth(this);
        int height = stars.getHeight(this);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        
        g.drawImage(image,-width/2,-height/2,
        width,height,this);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void drawExplosion(Image image,int xpos,int ypos,double rot,double xscale,double yscale)
    {
        int width = explosion.getWidth(this);
        int height = explosion.getHeight(this);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        
        g.drawImage(image,-width/2,-height/2,
        width,height,this);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void drawCircle(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );

        
        g.fillOval(-10,-10,20,20);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
    public void drawRocket(Image image,int xpos,int ypos,double rot,double xscale,
            double yscale) {
        int width = rocketImage.getWidth(this);
        int height = rocketImage.getHeight(this);
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        rocketHeight=height;
        rocketLength=width;

        g.drawImage(image,-width/2,-height/2,
        width,height,this);

        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable
    @Override
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

//init the location of the rocket to the center.
        checkpoint=0;
        rocketXPos = getWidth2()/2;
        rocketYPos = getHeight2()/2;
        score=0;
        
        
        for(int index=0;index<numStars;index++)
        {
            starXPos[index]=(int)(Math.random()*getWidth2()+20);
            starYPos[index]=(int)(Math.random()*getHeight2());
        }
        gameOver=false;
        timeCount=0;
        rocketXSpeed=0;
        numLives=5;
        for(int index=0;index<missiles.length;index++)
        {
            missiles[index]=new Missile();
            
            
        }
        forceScale=.1;
        forceField=false;
        //starTop
        

    }
/////////////////////////////////////////////////////////////////////////
    public void animate() {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (xsize != getSize().width || ysize != getSize().height) {
                xsize = getSize().width;
                ysize = getSize().height;
            }
            outerSpaceImage = Toolkit.getDefaultToolkit().getImage("./outerSpace.jpg");
            rocketImage = Toolkit.getDefaultToolkit().getImage("./rocket.GIF");
            stars = Toolkit.getDefaultToolkit().getImage("./starAnim.GIF");
            explosion = Toolkit.getDefaultToolkit().getImage("./explosion.GIF");
            reset();
            bgSound = new sound("./starwars.wav"); 
        }
        if(gameOver)
            return;
         
        if(bgSound.donePlaying)
        {
            bgSound = new sound("./starwars.wav"); 
        }
        for(int index=0;index<numStars;index++)
        {
            if(starXPos[index]>=getWidth2())
            {
                starXPos[index]=getX();
                starYPos[index]=(int)(Math.random()*getHeight2());
            }
            if(starXPos[index]<-50)
            {
                starXPos[index]=getWidth2()+20;
                starYPos[index]=(int)(Math.random()*getHeight2());
            }
        }
        
        //if(timeCount%25==24)
        
            for(int index=0;index<numStars;index++)
            {
                starXPos[index]+=5*rocketXSpeed;
            }
            
            
            
            if(rocketYPos<0)
                rocketYPos+=10;
            if(rocketYPos>getHeight2())
                rocketYPos-=10;
            
            for(int index=0;index<numStars;index++)
            {
                if(rocketXPos<starRight&&rocketXPos>starLeft)
                {
                    
                }
            }
            for(int index=0;index<missiles.length;index++)
            {
                if(missiles[index].missileActive)
                {
                    if(missiles[index].Right)
                        missiles[index].missileXPos+=5+10;
                    else
                        missiles[index].missileXPos-=5+10;
                }
            }
            for(int index=0;index<missiles.length;index++)
            {
                if(missiles[index].missileXPos<20)
                {
                    missiles[index].missileActive=false;
                }
                else if(missiles[index].missileXPos>getWidth2()+20)
                {
                    missiles[index].missileActive=false;
                }
            }
            for(int index=0;index<missiles.length;index++)
            {
                if(Missile.missileCurrent>=missiles.length-1)
                {
                    Missile.missileCurrent=0;
                }
            }
        boolean hit = false;
        for (int index=0;index<numStars;index++)
        {
            if (!hit && rocketXPos + 20 > starXPos[index] && 
            rocketXPos - 20 < starXPos[index] &&
            rocketYPos + 20 > starYPos[index] &&
            rocketYPos - 20 < starYPos[index])
            {
                hit = true;
                if (index != whichStarHit)
                {
                    whichStarHit = index;
                    zsound = new sound("./ouch.wav"); 
                    numLives--;
                }
            }
        }
        if (!hit)
            whichStarHit = -1;
        
        
        if(timeCount%25==24&&rocketXSpeed!=0)
        {
            score+=rocketXSpeed;
        }
        if(numLives==0)
        {
            gameOver=true;
        }
        if(rocketXSpeed<=0)
        {
            rocketImage = Toolkit.getDefaultToolkit().getImage("./animrocket.GIF");
        }
        //if(timeCount%10==9)
        //{
            if(forceField)
            {
                forceScale+=.1;
            }
            if(forceScale>=3)
                forceField=false;
        //}
        if(!forceField)
        {
            forceScale=.1;
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
        return (x + XBORDER);
    }

    public int getY(int y) {
        return (y + YBORDER + YTITLE);
    }

    public int getYNormal(int y) {
        return (-y + YBORDER + YTITLE + getHeight2());
    }
    
    
    public int getWidth2() {
        return (xsize - getX(0) - XBORDER);
    }

    public int getHeight2() {
        return (ysize - getY(0) - YBORDER);
    }
    public void readFile() {
        try {
            String inputfile = "info.txt";
            try (BufferedReader in = new BufferedReader(new FileReader(inputfile))) {
                String line = in.readLine();
                while (line != null) {
                    String newLine = line.toLowerCase();
                    if (newLine.startsWith("numstars"))
                    {
                        String numStarsString = newLine.substring(9);
                        numStars = Integer.parseInt(numStarsString.trim());
                    }
                    line = in.readLine();
                }
            }
        } catch (IOException ioe) {
        }
        checkpoint=0;
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

class Missile
{
    public static final int numMissiles=50;
    public static int missileCurrent=0;
    
    public int missileXPos;
    public int missileYPos;
    public boolean missileActive;
    public boolean Right;
    
    Missile()
    {
        missileActive=false;
    }
    
}