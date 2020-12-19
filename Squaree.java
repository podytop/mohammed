/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.media.opengl.*;
import static javax.media.opengl.GL.GL_QUAD_STRIP;
import javax.swing.*;

/**
 *
 * @author Mohamed
 */
public class Squaree extends JFrame {

    static GLCanvas glcanvas = null;

    public static void main(String[] args) {
   Squaree app = new Squaree();
// show what we've done
        SwingUtilities.invokeLater(
                new Runnable() {
                    public void run() {
                        app.setVisible(true);
                        glcanvas.requestFocusInWindow();

                    }
                }
        );
    }

    public Squaree() {
//set the JFrame title
        super("Square example");
//kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MKdisplay mkd = new MKdisplay();
//        TheMotion tm = new TheMotion();

//only three JOGL lines of code ... and here they are
        glcanvas = new GLCanvas();

        glcanvas.addGLEventListener(mkd);
        
        glcanvas.addMouseMotionListener(mkd);
        glcanvas.addMouseListener(mkd);
       
        glcanvas.addKeyListener(mkd);
        
//we'll want this for our repaint requests
        mkd.setGLCanvas(glcanvas);
        mkd.setGLCanvas(glcanvas);

//add the GLCanvas just like we would any Component
        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(500, 300);
//center the JFrame on the screen
        centerWindow(this);
    }

    public void centerWindow(Component frame) {
        Dimension screenSize
                = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }
}

/**
 * For our purposes only two of the GLEventListeners matter. Those would be
 * init() and display().
 */
class MKdisplay implements GLEventListener,KeyListener, MouseListener , MouseMotionListener  {
int xPosition ;
    int yPosition;
    GLCanvas glc;
    float red=0.0f;
    float green=0.0f;
    float blue=1.0f;
    double x = 100 ,y = 0, angle = 0;

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }

    /**
     * Take care of initialization here.
     */
    public void init(GLAutoDrawable gld) {
    
 GL gl = gld.getGL();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
       gl.glLineWidth(2);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
gl.glOrtho(-250.0, 250.0, -150.0, 150.0,-1.0,1.0);
        
}

    /**
     * Take care of drawing here.
     */
    public void display(GLAutoDrawable drawable) {
         GL gl = drawable.getGL();
   		
    gl.glClear(GL.GL_COLOR_BUFFER_BIT);
    gl.glPushMatrix();
    gl.glRotated(angle, 0,0 , 50);
    drawSquere(gl,red,green,blue);
    gl.glPopMatrix(); 
     gl.glColor3f(1.0f, 0.0f, 0.0f);
    gl.glBegin(GL.GL_LINES);
  
gl.glVertex2d(xPosition-10, yPosition);
gl.glVertex2d(xPosition+10, yPosition);
     
gl.glVertex2d(xPosition, yPosition-10);
gl.glVertex2d(xPosition, yPosition+10);

    gl.glEnd();
    
//        GL gl = drawable.getGL();		
    
  }
public void drawSquere(GL gl ,float r,float g,float b){
    
    gl.glColor3f(r, b, g);
    gl.glBegin(GL.GL_TRIANGLES);
    
    gl.glVertex2d(0, 100);
gl.glVertex2d(4, 0);
gl.glVertex2d(0, 0);
    gl.glEnd();
}
 public void keyPressed(KeyEvent e) {
         if (e.getKeyCode() == KeyEvent.VK_UP) {
            angle -= 1;
                    glc.repaint();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
             angle += 1;
                    glc.repaint();
        }
    }
  public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
        xPosition = (int) ((250-x)*(250 / width) );
        yPosition = ((int) ((150-y)*(150 / height) ));
//reversing direction of y axis
        yPosition = 150- yPosition;
        glc.repaint();
    }
   public void mouseClicked(MouseEvent me) {
red = (float) Math.random();
green = (float) Math.random();
blue= (float) Math.random();
        glc.repaint();

    }
 
    public void reshape(GLAutoDrawable drawable,int x,int y,int width,int height) {
    }
    public void displayChanged(GLAutoDrawable drawable,boolean modeChanged,boolean deviceChanged) {
    }
    @Override
    public void keyTyped(KeyEvent ke) {
    }
    @Override
    public void keyReleased(KeyEvent ke) {
    }
   
    @Override
    public void mousePressed(MouseEvent e) {
        
              
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }
    
    }