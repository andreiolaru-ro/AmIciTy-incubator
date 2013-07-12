package exercise.vlad.client;


import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Client extends JFrame implements ActionListener{
    
    private final int height = 510;
    private final int width = 640;
    
    private JButton lock;
    
    // status = 0 => este deblocat, status  = 1 => este blocat
    private int status = 0;
   
    private long timeStart;
    
    // lista pentru salvarea structurilor : timeStamp + String;
    private ArrayList<String> LockedT;
    
    // CANVAS-ul
    private GraphicsProgram GP;
    
    // Stringul de afisat
    private String output;
    
    Client(){
        
        init();
        components();
    }
    
    // functia init creeza fereastra si stabileste atributele acesteia
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
    
    // functia components adauga componentele la fereastra
    public void components(){
        

        GP = new GraphicsProgram(this); 
        add(GP);
       
        GP.setBounds(0,0,600,300);
       

        lock = new JButton("Lock Output");
        Dimension lock_dim = lock.getPreferredSize();
        lock.setBounds(50,350 ,lock_dim.width, lock_dim.height);
        add(lock);
        lock.addActionListener(this);
        
     //   JScrollPane scrolltxt = new JScrollPane(GP);
   /*     scrolltxt = new JScrollPane(GP,
        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolltxt.setMinimumSize(new Dimension(160, 200));
        scrolltxt.setPreferredSize(new Dimension(160, 200)); */

        
      //  scrolltxt.setPreferredSize(new Dimension(200,200));
    //    scrolltxt.setBounds( 20  , 20 , 600, 300);
    //    add(scrolltxt);
        
        this. setSize(width-1,height-1);
        this. setSize(width,height);
        
    }

    
    // functie apelata de threadul separat pentru a afisa mesajele de la consola
    // fuctiile setText si actionPerformed sunt synchronized pentru excludere mutuala asupra 
    // Arralistului care salveaza stringurile si asupra spatiului de afisare
    // status = 0 => textul este afisat
    // status = 1 => textul este salvat
    public synchronized void setText(String received){
       long time2 =   System.currentTimeMillis();
       if(status == 0){
            output = Integer.toString((int) (time2- timeStart)/100) + ":   " + received ;
            GP.repaint();
       }
       else
           LockedT.add((time2- timeStart)/100 + ":   "  + received + "\n");
         
    }
    // pentru obtinerea textului de catre Canvas
    public synchronized String getText(){
        return output;
        
    }
    
    // pentru obtinerea lsitei de stringuri de catre Canvas
    public synchronized ArrayList <String> getLockedText(){
        return LockedT;
    }

    // lista este stearsa de catre Canvas dupa ce datele au fost afisare
    public synchronized void setLocketText(){
        LockedT.clear();
    }
    

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
    
    public static void main(String arg[]){
        
        Client filtru  = new Client();
        
        // apelez threadul ce primeste mesaje de la celalalt program
        // ii trimit ca argument instanta filtru, pentru a aduga in TextArea 
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