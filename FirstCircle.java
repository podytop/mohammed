/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

/**
 *
 * @author ahmed
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.media.opengl.*;
import java.awt.Point;

/**
* This is a basic JOGL app. Feel free to
* reuse this code or modify it.
*/
public class FirstCircle extends JFrame implements ActionListener{
    
            FirstCircleListener listener = new FirstCircleListener();
    static GLCanvas glcanvas = null;


public static void main(String[] args) {
final FirstCircle app = new FirstCircle();
// show what we've done
SwingUtilities.invokeLater (
new Runnable() {
public void run() {
app.setVisible(true);
}
}
);
}
public FirstCircle() {
//set the JFrame title
super("Pentagram");//kill the process when the JFrame is closed
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

JPanel jp = new JPanel();                      
        JButton jb = new JButton("Clockwise");            
        jb.addActionListener(this);                    
        jp.add(jb);                                    
        
         JButton jb1 = new JButton("Anti-Clockwise");                     
        jb1.addActionListener(this);
        jp.add(jb1);
        
        JButton jb2 = new JButton("Exit");      
        jb2.addActionListener(this);
        jp.add(jb2);
getContentPane().add("South", jp);     

glcanvas = new GLCanvas();
glcanvas.addGLEventListener(listener);
        listener.setGLCanvas(glcanvas);
        getContentPane().add(glcanvas, BorderLayout.CENTER);
setSize(500, 300);
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
frame.setLocation (
(screenSize.width - frameSize.width ) >> 1,
(screenSize.height - frameSize.height) >> 1
);
}

public void actionPerformed(ActionEvent ae) {
listener.st = ae.getActionCommand();         
glcanvas.repaint();    
    }  
}

class FirstCircleListener implements GLEventListener{ 
        String st = "" ;
final double ONE_DEGREE = (Math.PI/180);
final double THREE_SIXTY = 2 * Math.PI;
GLCanvas glc;
double angle =ONE_DEGREE  ;
double angle1 = ONE_DEGREE;
int i = 0; 
int j = 0; 

public void setGLCanvas(GLCanvas glc) {
        this.glc = glc;
    }

public void init(GLAutoDrawable drawable) {
GL gl = drawable.getGL();
gl.glClearColor(0.0f, 0.0f, 0.0f, 0.4f);
gl.glViewport(-250, -150, 250, 150);
gl.glMatrixMode(GL.GL_PROJECTION);
gl.glLoadIdentity();
gl.glOrtho(-250, 250, -150, 150, -1.0, 1.0);
}

public void display(GLAutoDrawable drawable) {
double x,y;
double radius = 100;
float red = 0.0f;
float green = 1.0f;
float blue = 0.0f;


GL gl = drawable.getGL();
gl.glClear(GL.GL_COLOR_BUFFER_BIT);
gl.glColor3f(red, green, blue);
gl.glBegin(GL.GL_LINE_LOOP);
for (double a = 0; a <= THREE_SIXTY; a += ONE_DEGREE) {
x = (radius * (Math.cos(a)));
y = (radius * (Math.sin(a)));
gl.glVertex2d(x, y);
}
gl.glEnd();
gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(100, 0);
            gl.glVertex2d(90,0);
            gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(-100, 0);
            gl.glVertex2d(-90, 0);
            gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(0, 100);
            gl.glVertex2d(0, 90);
             gl.glBegin(GL.GL_LINES);
            gl.glVertex2d(0, -100);
            gl.glVertex2d(0, -90);
            gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*30)), radius * (Math.sin(ONE_DEGREE*30)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*30))-10), (radius * (Math.sin(ONE_DEGREE*30)))-10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*60)), radius * (Math.sin(ONE_DEGREE*60)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*60))-10), (radius * (Math.sin(ONE_DEGREE*60)))-10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*120)), radius * (Math.sin(ONE_DEGREE*120)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*120))+10), (radius * (Math.sin(ONE_DEGREE*120)))-10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*150)), radius * (Math.sin(ONE_DEGREE*150)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*150))+10), (radius * (Math.sin(ONE_DEGREE*150)))-10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*210)), radius * (Math.sin(ONE_DEGREE*210)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*210))+10), (radius * (Math.sin(ONE_DEGREE*210)))+10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*240)), radius * (Math.sin(ONE_DEGREE*240)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*240))+10), (radius * (Math.sin(ONE_DEGREE*240)))+10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*300)), radius * (Math.sin(ONE_DEGREE*300)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*300))-10), (radius * (Math.sin(ONE_DEGREE*300)))+10);
           gl.glBegin(GL.GL_LINES);
             gl.glVertex2d(radius * (Math.cos(ONE_DEGREE*330)), radius * (Math.sin(ONE_DEGREE*330)));
          gl.glVertex2d((radius * (Math.cos(ONE_DEGREE*330))-10), (radius * (Math.sin(ONE_DEGREE*330)))+10);
          gl.glEnd();

gl.glPushMatrix();
    gl.glRotated(angle, 0, 0, 1);
    gl.glBegin(GL.GL_TRIANGLES);
gl.glVertex2d(0, radius);
gl.glVertex2d(4, 0);
gl.glVertex2d(0, 0);
gl.glEnd();
    gl.glPopMatrix();    

gl.glPushMatrix();
    gl.glRotated(angle1, 0, 0, 1);
    gl.glBegin(GL.GL_TRIANGLES);
gl.glVertex2d(0, radius);
gl.glVertex2d(4, 0);
gl.glVertex2d(0, 0);
gl.glEnd();
    gl.glPopMatrix();    

 if(st.equals("Clockwise")){
    angle -= 6;
    glc.repaint();
     i++;
             if (i%12==0){
                angle1 -= 6;
             }
               st = "";
        }else if(st.equals("Anti-Clockwise")){
     angle += 6;
    glc.repaint();
     j++;
             if (j%12==0){
                angle1 += 6;
             }
          st = "";
        
          }else if(st.equals("Exit")){
        System.exit(0);
                  st = "";
          }
}
@Override
public void reshape( GLAutoDrawable drawable, int x, int y, int width, int height0) {
}
@Override
public void displayChanged( GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
}
}