/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.game;

import static ninja.game.NinjaGame.frame1;
//import java.io.*;
import java.awt.*;
//import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class NinjaGame extends JFrame implements Runnable {

    static Window w = new Window();
    boolean animateFirstTime = true;
    Image image;
    Graphics2D g;
    
    
    
    
    
    Image ninjaImage;
    Ninja ninja;
    boolean jump;
    int timeCount;
    
    int numFloor=5;
    int floorX[]=new int[numFloor];
    int floorY[]=new int[numFloor];
    int floorHeight[]=new int[numFloor];
    int floorWidth[]=new int[numFloor];
    int floorSpeed=3;
    int floorL;
    int floorR;
    int floorT;
    int floorB;
    
    
 
    static NinjaGame frame1;
    static public JFrame jframe;
    static public Dimension dim;

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame1 = new NinjaGame();
        frame1.setSize(w.WINDOW_WIDTH, w.WINDOW_HEIGHT);
        frame1.setLocation(dim.width / 2 - frame1.getWidth() / 2, dim.height / 2 - frame1.getHeight() / 2);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setVisible(true);
    }

    public NinjaGame() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (MouseEvent.BUTTON1 == e.getButton()) {
                    //left button
                }
                if (MouseEvent.BUTTON2 == e.getButton()) {
                    reset();
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
                repaint();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {



                if (KeyEvent.VK_RIGHT == e.getKeyCode()) 
                {
                    ninja.setXPos(ninja.getXPos()+ninja.getSpeed());
                    
                }
                if (KeyEvent.VK_LEFT == e.getKeyCode()) 
                {
                    ninja.setXPos(ninja.getXPos()-ninja.getSpeed());
                    
                }
                if (KeyEvent.VK_UP == e.getKeyCode()) 
                {
                    ninja.setYPos(ninja.getYPos()+ninja.getSpeed());
                    
                } 
                if (KeyEvent.VK_DOWN == e.getKeyCode()) 
                {
                    ninja.setYPos(ninja.getYPos()-ninja.getSpeed());
                    
                }
                if (KeyEvent.VK_SPACE == e.getKeyCode()) 
                {
                    jump=true;
                    
                }


                repaint();
            }
        });

        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////

    public final void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////

    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////

    @Override
    public void paint(Graphics gOld) {
        if (image == null || w.xsize != getSize().width || w.ysize != getSize().height) {
            w.xsize = getSize().width;
            w.ysize = getSize().height;
            image = createImage(w.xsize, w.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }

//fill background
        g.setColor(Color.cyan);

        g.fillRect(0, 0, w.xsize, w.ysize);

        int x[] = {w.getX(0), w.getX(w.getWidth2()), w.getX(w.getWidth2()), w.getX(0), w.getX(0)};
        int y[] = {w.getY(0), w.getY(0), w.getY(w.getHeight2()), w.getY(w.getHeight2()), w.getY(0)};
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
        
        
        ninja.draw(g, w, ninjaImage, this);
        for(int index=0;index<numFloor;index++)
        {
            g.fillRect(floorX[0],floorY[0],floorWidth[0],floorHeight[0]);
        }
        
        
        
        


        

        

        gOld.drawImage(image, 0, 0, null);
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

    public void reset() 
    {
        ninja = new Ninja();
        ninja.setXPos(w.getWidth2()/2);
        ninja.setYPos(75);
        timeCount=0;
        jump=false;
        
        
        
        floorX[0]=w.getWidth2()/2;
        floorY[0]=w.getYNormal(75-ninja.height);
        floorHeight[0]=5;
        floorWidth[0]=(int)(Math.random()*500+50);
        floorL=floorX[0]-floorWidth[0];
        floorR=floorX[0]+floorWidth[0];
        floorT=floorY[0]+floorHeight[0];
        floorB=floorY[0]-floorHeight[0];
        
        
        
    }
/////////////////////////////////////////////////////////////////////////
    public void animate() 
    {
        if (animateFirstTime) {
            animateFirstTime = false;
            if (w.xsize != getSize().width || w.ysize != getSize().height) {
                w.xsize = getSize().width;
                w.ysize = getSize().height;
            }
            ninjaImage = Toolkit.getDefaultToolkit().getImage("ninja.png");

            reset();
        }
        if(jump)
        {
            ninja.setYPos(ninja.getSpeed()/5+ninja.getYPos());
            if(timeCount%100==99)
                jump=false;
        }
        if(timeCount%5==4)
        {
            boolean blocked = doesIntersect(ninja);
            if (!jump&&!blocked)
            {
                ninja.setYPos(ninja.getYPos()-ninja.getSpeed());
            }
        }
        
        
        
        

        timeCount++;
    }
    public boolean doesIntersect(Ninja ninja) {
        boolean horizontal = false;
        boolean vertical = false;

        if (ninja.getLeftSide() > floorL
                && ninja.getLeftSide() < floorR) {
            horizontal = true;
        }
        if (ninja.getRightSide() > floorL
                && ninja.getRightSide() < floorR) {
            horizontal = true;
        }
        if (ninja.getBottomSide() > floorB
                && ninja.getBottomSide() < floorT) {
            vertical = true;
        }
        if (ninja.getTopSide() > floorB
                && ninja.getTopSide() < floorT) {
            vertical = true;
        }
        if (vertical && horizontal) {
            return (true);
        }

        horizontal = false;
        vertical = false;

        if (floorL > ninja.getLeftSide()
                && floorL < ninja.getRightSide()) {
            horizontal = true;
        }
        if (floorR > ninja.getLeftSide()
                && floorR < ninja.getRightSide()) {
            horizontal = true;
        }
        if (floorB > ninja.getBottomSide()
                && floorB < ninja.getTopSide()) {
            vertical = true;
        }
        if (floorT > ninja.getBottomSide()
                && floorT < ninja.getTopSide()) {
            vertical = true;
        }
        if (vertical && horizontal) {
            return (true);
        }

        return (false);
    }


////////////////////////////////////////////////////////////////////////////

    public final void start() {
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
}

/////////////////////////////////////////////////////////////////////////
class Window {

    static final int WINDOW_WIDTH = 412;
    static final int WINDOW_HEIGHT = 600;
    final int TOP_BORDER = 40;
    final int SIDE_BORDER = 8;
    final int BOTTOM_BORDER = 8;
    final int YTITLE = 25;
    int xsize = -1;
    int ysize = -1;

    public int getX(int x) {
        return (x + SIDE_BORDER);
    }

    public int getY(int y) {
        return (y + TOP_BORDER + YTITLE);
    }

    public int getYNormal(int y) {
        return (-y + TOP_BORDER + YTITLE + getHeight2());
    }

    public int getWidth2() {
        return (xsize - SIDE_BORDER * 2);
    }

    public int getHeight2() {
        return (ysize - (TOP_BORDER + YTITLE) - BOTTOM_BORDER);
    }
}

class Ninja {
    
    int xpos;
    int ypos;
    int speed;
    int scale;
    int rotation;
    int width;
    int height;
    

    Ninja() 
    {
        speed=5;
        width=50;
        height=50;
        rotation=0;
        scale=2;
        
        

    }
    
    public void setXPos(int _xpos)
    {
        xpos=_xpos;
    }
    public void setYPos(int _ypos)
    {
        ypos=_ypos;
    }
    public int getXPos()
    {
        return(xpos);
    }
    public int getYPos()
    {
        return(ypos);
    }
    public int getSpeed()
    {
        return(speed);
    }
    public int getLeftSide() {
        return (xpos - width / 2);
    }
    public int getRightSide() {
        return (xpos + width / 2);
    }
    public int getTopSide() {
        return (ypos + height / 2);
    }
    public int getBottomSide() {
        return (ypos - height / 2);
    }
    
    
  //This is draw method for picture Blank Method
    public void draw(Graphics2D g, Window w, Image image, NinjaGame obj) {
        double xscale = scale;
        double yscale = scale;

        g.translate(w.getX(xpos), w.getYNormal(ypos));
        g.scale(xscale, yscale);


        g.drawImage(image, -width / 2, -height / 2, width, height, obj);

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.translate(-w.getX(xpos), -w.getYNormal(ypos));
    }

}

class BlankObject2 {

    BlankObject2() {

    }

    /*This is draw method for picture Blank Method2
    public void draw(Graphics2D g, Window w, Image image, BlankTemplate obj) {
        double xscale = 1.0;
        double yscale = 1.0;

        g.translate(w.getX(xpos), w.getYNormal(ypos));
        g.scale(xscale, yscale);

        g.drawImage(image, -width / 2, -height / 2, width, height, obj);

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.translate(-w.getX(xpos), -w.getYNormal(ypos));
    }*/

}