//Vincent Costa



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package staticscene;

import java.io.*;
import java.awt.*;
import java.awt.geom.*; 
import java.awt.event.*;
import javax.swing.*;

public class StaticScene extends JFrame implements Runnable {
    static final int WINDOW_WIDTH = 1200;
    static final int WINDOW_HEIGHT = 800;
    final int XBORDER = 20;
    final int YBORDER = 20;
    final int YTITLE = 25;
    boolean animateFirstTime = true;
    int xsize = -1;
    int ysize = -1;
    Image image;
    Graphics2D g;
    
    int ArrowSpeed;
    int ArrowDrop;
    int ArrowSpeed2;
    int ArrowDrop2;
    int Bow;
    int House;
    int Tree;
    double Time;
    int TreeX;
    int TreeY;
    int TreeR;
    int Person;
    int Archer;
    int Cloud;
    
    double ScaleDir;
    double ScaleValue;
    
    boolean Person2;
    boolean Archer2;
 //   boolean Picture;
    boolean ArrowB;
    boolean Motion;
    boolean End;
    
    

    static StaticScene frame;
    public static void main(String[] args) {
        frame = new StaticScene();
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public StaticScene() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (e.BUTTON1 == e.getButton()) {
                    //left button

// location of the cursor.
                    int xpos = e.getX();
                    int ypos = e.getY();

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
                if (e.VK_UP == e.getKeyCode()) {
                } else if (e.VK_DOWN == e.getKeyCode()) {
                } else if (e.VK_LEFT == e.getKeyCode()) {
                } else if (e.VK_RIGHT == e.getKeyCode()) {
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
        g.setColor(Color.white);
        g.fillRect(0, 0, xsize, ysize);

        
        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }
        
        
       // if(!Picture)
        //{
            
        
        
        Color newCol1 = new Color(41,28,215);
        g.setColor(newCol1);
        g.fillRect(0, 0, 1200, 525);
        
        g.setColor(Color.white);
        g.fillRect(200+Cloud, 100, 500, 100);
        g.fillRect(400+Cloud, 50, 750, 100);
        
        g.fillRect(-200+Cloud, 100, 100, 100);
        g.fillRect(0+Cloud, 50, 350, 100);
      
        Color newCol = new Color(9,85,0);
        g.setColor(newCol);
        g.fillRect(0, 525, 1200, 500);
        drawHouse(100,500,0,15,15);
        g.fillArc(40, 420, 15, 15, 0, 180);
        drawTree(250,500-Tree,0,15,15);
        drawTree(400,510-Tree,0,15,15);
        drawBanner(460,575,0,15,15);
        
        drawPerson(475,520,0,7,7);
        drawPerson(515,520,0,7,7);
        
        if(Person2)
        drawPerson(547+Person,500,0,7,7);
        drawPerson(650,520,0,7,7);
        
        drawStand(650,530,0,7,7);
        
        drawTree( 1150,505+Tree,0+TreeR,15,15);
        drawTarget(1100,525,0,7,7);
        drawTree(400,708+Tree,0+TreeR,15,15);
        drawTree(700,725+Tree,0+TreeR,15,15);
        drawTree(150,625+Tree,0+TreeR,15,15);
        drawTree(850+TreeX,625-TreeY,0+TreeR,15,15);
        
        
        if(!ArrowB)
        {
            drawArrow(725+ArrowSpeed2,520+ArrowDrop2,0,6,6);
            drawArcher(725,500,0,7,7);
        }
        
        drawBow(725,520,0,6,6);
        drawArrow(725+ArrowSpeed,520+ArrowDrop,0,6,6);
        drawArcher(725+Archer,500,0,7,7);
        
       // }
        if (End)
        {
        g.setColor(Color.RED);
        g.setFont(new Font("Palatino Linotype",Font.BOLD,50));
        g.drawString("THE END", 450, 350);
        }
        
        
        gOld.drawImage(image, 0, 0, null);
    }

    public void drawHouse(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        
        
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol3 = new Color(173,65,18);
        g.setColor(newCol3);//Chimney
        int xval5[] = { -4,-3,-3,-4,-4};
        int yval5[] = { -5,-5,-3,-3,-5};
        g.fillPolygon(xval5,yval5,xval5.length);
        
        Color newCol = new Color(206,118,53);
        g.setColor(newCol);//House
        int xval1[] = { -4,-4,4,4,0,-4};
        int yval1[] = { -3,5,5,-3,-7,-3};
        g.fillPolygon(xval1,yval1,xval1.length);
        
        g.setColor(Color.white);//Window
        int xval2[] = { -2,0,2,2,2,0,-2,-2,-2};
        int yval2[] = { -1,-1,-1,0,1,1,1,0,-1};
        g.fillPolygon(xval2,yval2,xval2.length);

        g.drawLine(0, -1, 0, 1);
        g.drawLine(-2, 0, 2, 0);
        Color newCol1 = new Color(137,78,33);
        g.setColor(newCol1);//door
        int xval4[] = { -2,0,0,0,0,-2,-2};
        int yval4[] = { 1,1,3,4,5,5,1};
        g.fillPolygon(xval4,yval4,xval4.length);
        
        Color newCol2 = new Color(191,191,0);
        g.setColor(newCol2);//doorknob
        int xval6[] = { -1,0,0,-1,-1};
        int yval6[] = { 3,3,4,4,3};
        g.fillPolygon(xval6,yval6,xval6.length);
        
        g.drawArc(-4, -5, -3, -5, 0, 180);
        
        
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawTree(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol = new Color(174,99,23);
        g.setColor(newCol);//Trunk
        int xval[] = { -1,-1,-1,-1,1,1,1,1,-1};
        int yval[] = { -7,-4,0,5,5,0,-4,-7,-7};
        g.fillPolygon(xval,yval,xval.length);
        
        Color newCol1 = new Color(22,147,19);
        g.setColor(newCol1);//Leaves
        int xval1[] = { -5,5,0,-5};
        int yval1[] = { -4,-4,-10,-4};
        g.fillPolygon(xval1,yval1,xval1.length);
        
        int xval2[] = { -1,-5,-1,1,5,1,-1};
        int yval2[] = { -4,0,0,0,0,-4,-4};
        g.fillPolygon(xval2,yval2,xval2.length);
        
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawBanner(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        g.setColor(Color.BLACK);//BannerFrame
        int xval[] = { 0,0,1,8,9,9,8,8,1,1,0};
        int yval[] = { 0,-8,-8,-8,-8,0,0,-6,-6,0,0};
        g.fillPolygon(xval,yval,xval.length);
        
        g.setColor(Color.YELLOW);//textbehind
        int xval1[] = { 1,1,8,8,1};
        int yval1[] = { -8,-6,-6,-8,-8};
        g.fillPolygon(xval1,yval1,xval1.length);
        
        
        g.setFont(new Font("Palatino Linotype",Font.BOLD,1));
        Color newCol = new Color(213,30,34);
        g.setColor(newCol);
        g.drawString("ArcheryContest", 1, -7);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawPerson(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol1 = new Color(235,227,188);
        g.setColor(newCol1);
        g.drawLine(0, 2, 0, 7);
        g.drawLine(0, 3, 2, 5);
        g.drawLine(0, 3, -2, 5);
        g.drawLine(0, 7, 1, 8);
        g.drawLine(0, 7, -1, 8);
        g.fillOval(-2, -2,4,4);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawTarget(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        g.setColor(Color.RED);
        g.fillOval(-3, -3, 7, 7);
        
        g.setColor(Color.white);
        g.fillOval(-2, -2, 5, 5);
        
        g.setColor(Color.RED);
        g.fillOval(0, 0, 1, 1);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }
    public void drawArcher(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol1 = new Color(235,227,188);
        g.setColor(newCol1);
        
        g.fillOval(-2, -2,4,4);
        g.drawLine(0, 2, 0, 7);
        g.drawLine(0, 3, 3, 3);
        g.drawLine(0, 3, -2, 4);
        g.drawLine(-2, 4, 0, 4);
        g.drawLine(0, 7, 2, 9);
        g.drawLine(0, 7, -1, 9);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawBow(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol1 = new Color(129,133,46);
        g.setColor(newCol1);
        
        
        g.drawLine( -1, -5, -1, -4);
        g.drawLine( -1, -4,  1, -4);
        g.drawLine(  1, -4,  1, -3);
        g.drawLine( -1, -3,  2, -3);
        g.drawLine(  2, -3,  2, -2);
        g.drawLine(  2, -2,  3, -2);
        g.drawLine(  3, -2,  3, -1);
        g.drawLine(  3, -1,  4, -1);
        g.drawLine(  4, -1,  4,  1);
        g.drawLine(  4,  1,  3,  1);
        g.drawLine(  3,  1,  3,  2);
        g.drawLine(  3,  2,  2,  2);
        g.drawLine(  2,  2,  2,  3);
        g.drawLine(  2,  3,  1,  3);
        g.drawLine(  1,  3,  1,  4);
        g.drawLine(  1,  4, -1,  4);
        g.drawLine( -1,  4, -1,  5);
        g.setColor(Color.DARK_GRAY);
        g.drawLine(  0, -4,  0,  4);
        
        if(Motion)
        {
            
        }
        else if(!Motion)
        {
        g.setColor(Color.DARK_GRAY);
        g.drawLine(  0, -4,  0,  4);
        }
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    } 
    public void drawArrow(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol1 = new Color(146,118,33);
        g.setColor(newCol1);
        g.drawLine(8,0,-2,0);
        g.drawLine(8,-1, 8, 1);
        g.drawLine(8, 1, 9, 0);
        g.drawLine(9, 0, 8, -1);
        g.drawLine(-2, 0, -3, -1);
        g.drawLine(-2, 0, -3, 1);
        
        g.scale( 1.0/xscale,1.0/yscale );
        g.rotate(-rot  * Math.PI/180.0);
        g.translate(-xpos,-ypos);
    }  
    public void drawStand(int xpos,int ypos,double rot,double xscale,double yscale)
    {
        g.translate(xpos,ypos);
        g.rotate(rot  * Math.PI/180.0);
        g.scale( xscale , yscale );
        
        Color newCol1 = new Color(70,13,69);
        g.setColor(newCol1);
        g.fillRect(-4,4,8,4);
        g.drawLine(4, 4, 4, -4);
        g.drawLine(4, -4, -4, -4);
        g.drawLine(-4, -4, -4, 4);
        
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
            double seconds = 0.016666;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }
/////////////////////////////////////////////////////////////////////////
    public void reset() {
        ArrowSpeed=0;
        ArrowDrop=0;
        
        ArrowSpeed2=0;
        ArrowDrop2=0;
        
        Bow=0;
        House=0;
        Time=0;
        Tree=0;
        TreeX=0;
        TreeY=0;
        TreeR=0;
        Person=0;
        Archer=0;
        Cloud=-300;
        
        ArrowB=true;
        Person2=true;
        Motion=false;
        End=false;
       // Picture= true;
   
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

        TreeR++;
        if(TreeR>=1)
            TreeR-=1;
        
        if(ArrowSpeed<=325)
        {
        ArrowSpeed+=7;
        Bow+=1;
        }
        else if(ArrowSpeed>=325)
        {
        ArrowSpeed+=0;
        }
        if(Time>=300)
            Motion=true;
        else if(Time<=325)
            Motion=false;

       // if(Time==100)
       // {
       //     Picture = false;
       // }
       if(Time>=150&&Time<=200)
       {
           Person+=3;
           Archer-=3;
       }
       
       if(Cloud>=1500)
           Cloud=-500;
       else
           Cloud+=2;

       if(Time>=250)
       {
           ArrowB= false;
           Person2=false;
       }
       else if(Time>=350)
        {
            Person2=true;
            Person-=3;
        }
       if(Time>=300&&Time<=335)
       {
           ArrowSpeed2+=9;
           ArrowDrop2+=1;
       }
       
       if(ArrowSpeed2>=10||ArrowDrop2>=25)
       {
           ArrowSpeed2+=0;
           ArrowDrop2+=0;
       }
       
        House++;
        Time++;
        //if(Time % 10 ==9)
        //{
        //
        //
        //
        //
        //}
        //
        //
        //
        //
        if(Time>=350)
        {
            Person2=true;
            Person-=3;
        }
        if(Time>=400)
        {
            Person+=0;
            End=true;
        }
        if (End)
        {
        g.setColor(Color.RED);
        g.setFont(new Font("Palatino Linotype",Font.BOLD,50));
        g.drawString("THE END", 450, 350);
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
}
