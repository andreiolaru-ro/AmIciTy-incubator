package exercise.vlad.client;

import java.awt.event.*;
import java.awt.*;

import javax.swing.*;

import java.util.*;

/**
 * class for creating the Frame where the components(Canvas and Button) 
 * will be shown
 */
public class Client extends JFrame implements ActionListener{
    
    /**
	 * serial number used in a possible communication
	 */
	private static final long serialVersionUID = 1L;

	/**
     the window's height
     */
    private final int height = 510;
    
    /**
     the window's width
     */
    private final int width = 640;
    
    /**
     the buttuon the user uses for disabling/ enabling receveing messages
     */
    private JButton lock;
    
    /**
     status = 0 =>  enabled,
     status  = 1 => disabled
     */
    // 
    private int status = 0;
   
    /**
    variable used for calculating when the tet is received
     */
    private long timeStart;
    
   
    /**
     an aray of the structures: timeStamp + String;
     */
    private ArrayList<String> LockedT;
    
    /**
      the Canvas component of the Program
     */
    private GraphicsProgram GP;
    
    
    /**
     the string received and to be shown
     */
    private String output;
    
    /**
     * scroll bar the person uses to scroll to all text
     */
    public JScrollBar vbar;
    
    /**
    the constructor of the class
    contains the methods to initialise the window and its components
     */
    Client(){
        
        init();
        components();
    }
    
    
    /**
    function init creates the frame and establishes its components
     */
    public void init(){
        this.setTitle(" Fereastra Filtru de texte");
        this.setSize(width, height);
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2 - width/2, dim.height/2 - height/2);
        this.setResizable( false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        
        
        timeStart =   System.currentTimeMillis();     
        LockedT = new ArrayList<String>();
        output = "";
        
    }
    
    /**
     function components adds components to the window
     */
    public void components(){
        

        GP = new GraphicsProgram(this); 
        add(GP);
       
        lock = new JButton("Lock Output");
        Dimension lock_dim = lock.getPreferredSize();
        lock.setBounds(50,350 ,lock_dim.width, lock_dim.height);
        add(lock);
        lock.addActionListener(this);
        
        
        vbar = new JScrollBar(Adjustable.VERTICAL);
        GP.add(vbar,BorderLayout.EAST);
        
    //    JScrollPane scrolltxt = new JScrollPane(GP);
   /*     scrolltxt = new JScrollPane(GP,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolltxt.setMinimumSize(new Dimension(160, 200));
        scrolltxt.setPreferredSize(new Dimension(160, 200)); */

        
   //     scrolltxt.setPreferredSize(new Dimension(200,200));
   //     scrolltxt.setBounds( 20  , 20 , 600, 300);
   //     add(scrolltxt);
        
        this. setSize(width-1,height-1);
        this. setSize(width,height);
        
    }

    
    
    /**
     *    
     the functions are synchronised for mutual exclusion 
     on the arraylist which saves the strings and on the canvas
     between the thread which draws the text and the one that receives them
     
     status = 0 =>the text is shown
     status = 1 =>the text is saved
     
     * @param received : the String received from the thread
     *  which runs in class Receiver 
     */
    public synchronized void setText(String received){
       long time2 =   System.currentTimeMillis();
       if(status == 0){
            output = Integer.toString((int) (time2- timeStart)/100) + ":   " + received ;
            GP.repaint();
       }
       else
           LockedT.add((time2- timeStart)/100 + ":   "  + received + "\n");
         
    }
    
    /**
     * @return Sting by the Canvas when the Canvas is not blocked
     */
    public synchronized String getText(){
        return output;
    }
    
    /**
     * getter for getting the list of strings by the canvas
     * @return the list which saves the structures string + timeStamp 
     */
    public synchronized ArrayList <String> getLockedText(){
        return LockedT;
    }

    /**
     * the list is Cleared bu Canvas once the dats where shown
     */
    public synchronized void setLocketText(){
        LockedT.clear();
    }
    

    @Override
	public synchronized void  actionPerformed(ActionEvent e){
         String command = e.getActionCommand();
         if(command.equals("Lock Output") )
             // daca a fost blocat:
             // se deblocheaza, se modifica variabila Canvasului pentru a stii ca va primi Lista si nu un string
             if( status == 1 ){
                 status = 0;
                 
                 GP.changeAfis();  // GP isi va modifica o variabila pentru a stii ca trebuie a afiseze un ArrayList
                 GP.repaint();
             }
             else
                 status = 1; 
           
    }
    
    /**
     * creates the object Client which has the role to draw the text
     * creates the object Receiver which receives the text from the Server
     * @param arg : list of parameters received 
     */
    public static void main(final String arg[]){
        
        Client filtru  = new Client();
        Receiver r = new Receiver(filtru);
        r.start();

        try{
            r.join();
        }
        catch(InterruptedException e){
            System.out.println("Eroare la join");
        } 
    }
    
}