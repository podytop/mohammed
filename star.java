/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ahmed
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;

public class star extends JFrame {
    public static void main(String[] args) {
final star app = new star();
// show what we've done
SwingUtilities.invokeLater (
new Runnable() {
public void run() 
{app.setVisible(true);}});
}
public star() {
//set the JFrame title
super("First Circle Using Sine and Cosine");
//kill the process when the JFrame is closed
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//only three JOGL lines of code ... and here they are
GLCanvas glcanvas =new GLCanvas();
glcanvas.addGLEventListener(new FirstCircleEventListener());
//add the GLCanvas just like we would any Component
getContentPane().add(glcanvas, BorderLayout.CENTER);
setSize(500, 300);
//center the JFrame on the screen
centerWindow(this);
}
public void centerWindow(Component frame) {
Dimension screenSize =
Toolkit.getDefaultToolkit().getScreenSize();
Dimension frameSize = frame.getSize();
if (frameSize.width > screenSize.width )
frameSize.width = screenSize.width;
if (frameSize.height > screenSize.height)
frameSize.height = screenSize.height;
frame.setLocation ((screenSize.width - frameSize.width ) >> 1,(screenSize.height - frameSize.height) >> 1);
}
}
class FirstCircleEventListener implements GLEventListener , MouseMotionListener{
final double ONE_DEGREE = (Math.PI/180);
final double THREE_SIXTY = 2 * Math.PI;
int xPosition = 50;
    int yPosition = 50;
    float red = 0.0f;
    float green = 0.5f;
    float blue = 0.5f;
    GLCanvas glc;
    double radius = 100;

    public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }
/**
* Take care of initialization here.
*/
@Override
public void init(GLAutoDrawable gld) {
GL gl = gld.getGL();
gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
gl.glViewport(-250, -150, 250, 150);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
gl.glOrtho(-250.0, 250.0, -150.0, 150.0,-1.0,1.0);
}
/**
* Take care of drawing here.
*/
@Override
public void display(GLAutoDrawable drawable) {
double x,y;


GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(0, 1, 1);
gl.glBegin(GL.GL_LINE_LOOP);
// angle is
// x = radius * (cosine of angle)
// y = radius * (sine of angle)
for (double a=0; a<THREE_SIXTY; a+=ONE_DEGREE) {
x = radius * (Math.cos(a));
y = radius * (Math.sin(a));
gl.glVertex2d(x, y);
}
gl.glEnd();
gl.glColor3f(red, green, blue);
 gl.glBegin(GL.GL_LINE_LOOP);
             gl.glVertex2d(radius * (Math.sin(ONE_DEGREE)), radius * (Math.cos(ONE_DEGREE)));
          gl.glVertex2d((radius * (Math.sin(ONE_DEGREE*144))), (radius * (Math.cos(ONE_DEGREE*144))));
           gl.glBegin(GL.GL_LINE_LOOP);
             gl.glVertex2d(radius * (Math.sin(ONE_DEGREE)), radius * (Math.cos(ONE_DEGREE)));
          gl.glVertex2d((radius * (Math.sin(216*ONE_DEGREE))), (radius * (Math.cos(216*ONE_DEGREE))));
           gl.glBegin(GL.GL_LINE_LOOP);
             gl.glVertex2d(radius * (Math.sin(72*ONE_DEGREE)), radius * (Math.cos(72*ONE_DEGREE)));
          gl.glVertex2d((radius * (Math.sin(216*ONE_DEGREE))), (radius * (Math.cos(216*ONE_DEGREE))));
           gl.glBegin(GL.GL_LINE_LOOP);
            gl.glVertex2d(radius * (Math.sin(72*ONE_DEGREE)), radius * (Math.cos(72*ONE_DEGREE)));
           gl.glVertex2d((radius * (Math.sin(288*ONE_DEGREE))), (radius * (Math.cos(288*ONE_DEGREE))));
           gl.glBegin(GL.GL_LINE_LOOP);
             gl.glVertex2d(radius * (Math.sin(144*ONE_DEGREE)), radius * (Math.cos(144*ONE_DEGREE)));
          gl.glVertex2d((radius * (Math.sin(288*ONE_DEGREE))), (radius * (Math.cos(288*ONE_DEGREE))));
          
           
          gl.glEnd();
}

@Override
public void reshape(GLAutoDrawable drawable,int x,int y,int width,int height) {}
@Override
public void displayChanged(GLAutoDrawable drawable,boolean modeChanged,boolean deviceChanged) {}

    @Override
    public void mouseDragged(MouseEvent me) {
       
    }

    @Override
   public void mouseMoved(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();
        Component c = e.getComponent();
        double width = c.getWidth();
        double height = c.getHeight();
//get percent of GLCanvas instead of
//points and then converting it to our
//'100' based coordinate system.
        xPosition = (int) ((x / width) *250);
        yPosition = ((int) ((y / height) * 150));
//reversing direction of y axis
        yPosition = 1 - yPosition;
         
        if(Math.pow(x, 2)+Math.pow(y, 2)>=200)
            red=0;
        green=0;
        blue=0;
        glc.repaint();
    }
}

