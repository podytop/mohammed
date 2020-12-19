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
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;
import javax.media.opengl.*;
import javax.media.opengl.glu.GLU;
import java.awt.Point;


public class print extends JFrame implements MouseMotionListener, MouseListener ,ActionListener  {

    PaintGl listener = new PaintGl();
    GLCanvas glcanvas;
    

    public static void main(String[] args) {

       print app = new print();
       SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                app.setVisible(true);
            }
        }
        );
              
    }

    public print() {
        
        super("Printer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel jp = new JPanel();                      // create panel  The Panel is a simplest container class. It provides space in which an application can attach any other component. It inherits the Container class.
        JButton jb = new JButton("Clear");             // create "Clear" button
        jb.addActionListener(this);                    // this : instance of listener calss 
        jp.add(jb);                                    // put the button we create in panel   **adds component to a specified container**
        
         JButton jb1 = new JButton("Undo");                     // create "Undo" button"
        jb1.addActionListener(this);
        jp.add(jb1);
        
        JButton jb2 = new JButton("Color");       // create "Color" button"
        jb2.addActionListener(this);
        jp.add(jb2);
        
        JButton jb3 = new JButton("Redo");     // create "redo" button"
        jb3.addActionListener(this);
        jp.add(jb3);
        
        getContentPane().add("South", jp);     //Determine the place of the buttons  

        glcanvas = new GLCanvas();           // Canvas is a blank rectangular area where the user can draw or trap input from the user.
        glcanvas.addGLEventListener(listener);          //sets up a function (which is   PaintGl listener)  that will be called whenever the specified event is delivered to the target 
        
        glcanvas.addMouseListener(this);
        glcanvas.addMouseMotionListener(this);

        getContentPane().add(glcanvas, BorderLayout.CENTER);
        setSize(700, 500);	     
                centerWindow(this);       //Or        setLocationRelativeTo(null);           used to center frame in the screen.
    }
    
    public void centerWindow(Component frame) {
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        
        frame.setLocation((screenSize.width - frameSize.width) >> 1,(screenSize.height - frameSize.height) >> 1);
        
    }

   public void actionPerformed(ActionEvent ae) {       //invoked automatically whenever you click on the registered buttons
          
       listener.st = ae.getActionCommand();         //save the button name in string in listener  ,  getActionCommand() gives you a String representing the action command
        glcanvas.repaint();
        
    }

    public void mouseClicked(MouseEvent e) {
      
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        
        double x = e.getX();
        double y = e.getY();

        Component c = e.getComponent();

        
        double width = c.getWidth();
        double height = c.getHeight();

        
        int xPosition = (int) ((x / width) * 100);
        int yPosition = (int) ((y / height) * 100);

        
        yPosition = 100 - yPosition;
         
        Point p = new Point(xPosition , yPosition);
         listener.poin.add(p);
//                glcanvas.repaint();

    }

   

    @Override
    public void mouseDragged(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        Component c = e.getComponent();

       
        double width = c.getWidth();
        double height = c.getHeight();

        int xPosition1 = (int) ((x / width) * 100);
        int yPosition1 = (int) ((y / height) * 100);

        
           yPosition1 = 100 - yPosition1;
           Point p1 = new Point(xPosition1, yPosition1);
        listener.poin.add(p1);           
            glcanvas.repaint();
            
    }
     public void mouseReleased(MouseEvent e) {

listener.point.add(listener.poin);
          listener.poin = new ArrayList<Point>();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
         
    }
}

 class PaintGl implements GLEventListener {
    
    String st = "" ;
    float red = 1f;
    float green = 1f;
    float blue = 1f;
        ArrayList<Point> poin = new ArrayList<Point>();
        ArrayList<Float> rgb = new ArrayList<Float>();
        
        Stack <ArrayList> point = new Stack();
                Stack <ArrayList> temp_point = new Stack();

      ArrayList<Point> redo_point = new ArrayList<Point>();
   ArrayList<Point> undo_point = new ArrayList<Point>();
   
    public void init(GLAutoDrawable gld) {
        GL gl = gld.getGL();
        GLU glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(0,0,100,100);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
       gl.glOrtho(0,100,0, 100,1.0,-1.0);
    }

    
    public void display(GLAutoDrawable drawable) {

 GL gl = drawable.getGL();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        
          paint_points(gl);
        
        if(st.equals("Clear")){
          gl.glClear(GL.GL_COLOR_BUFFER_BIT);
          point=new Stack<ArrayList>();
           st = "";
           
        }else if(st.equals("Undo")){
             undo_point = point.get(point.size() - 1);
             if(undo_point.size()>10){
          for( int i=0; i<10;i++){
      
          undo_point.remove(undo_point.size()-1);
                 temp_point.add(undo_point);}
             } else {
             for( int i=0; i<undo_point.size();i++){
      
          undo_point.remove(undo_point.size()-1);
                 temp_point.add(undo_point);}
             
             }
//point.pop();
 // paint_points( gl);
  
          if (undo_point.isEmpty()){
              point.pop();
                 undo_point= point.get(point.size() - 1);
          }
//      or we could just write    point.pop();   but it will remove the whole line 
          st = "";
          
          }else if(st.equals("Redo")){
              
//                redo_point = temp_point.get(temp_point.size() - 1);
 point.add(temp_point.get(temp_point.size() - 1));
 
 paint_points(gl);
                 
          st="";
          
          
        }else if(st.equals("Color")){
           rgb.add(red);
        rgb.add(green);
        rgb.add(blue);
          JColorChooser cl = new JColorChooser();
          Color cc = cl.showDialog(cl,"Colors", Color.white);
          red = cc.getRed()/250;
          green = cc.getGreen()/250;
          blue = cc.getBlue()/250;
          
          st="";
           }
       
    }



    public void reshape(GLDrawable drawable, int x, int y, int width, int height) {
    }

    public void displayChanged(GLDrawable drawable,boolean modeChanged,boolean deviceChanged) {
    }

    @Override
    public void reshape(GLAutoDrawable glad, int i, int i1, int i2, int i3) {
    }

    @Override
    public void displayChanged(GLAutoDrawable glad, boolean bln, boolean bln1) {
    }
    
    public void paint_points(GL gl){
        gl.glColor3f(red, green, blue);
        gl.glBegin(GL.GL_LINES);

 for(int i = 0  ; i < point.size() ;  i++){
                 ArrayList<Point> p1 = point.get(i);

for( int k = 0 , j = 1  ; j < p1.size() ; k++ ,j++){
                gl.glVertex2d(p1.get(k).x,p1.get(k).y);
                 gl.glVertex2d(p1.get(j).x,p1.get(j).y);
            } 
 }  
           
            for(int i = 0 , j = 1 ; j < poin.size() ; j++ , i++){
                gl.glVertex2d(poin.get(i).x,poin.get(i).y);
                 gl.glVertex2d(poin.get(j).x,poin.get(j).y);
            } 
         gl.glEnd();
    
    }
     
    
 }
