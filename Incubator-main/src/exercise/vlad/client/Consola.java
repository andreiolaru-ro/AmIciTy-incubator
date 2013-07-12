package exercise.vlad.client;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


class GraphicsProgram extends JPanel{
	  
    private static Font monoFont;
    private Client frame;
    
    // pentru a stii daca trebuie sa afisez un text nou venit sau textul cat timp a fost blocat
    private boolean afisArray; 
    private FiltruOne blueFiltru;
    private FiltruTwo redFiltru;
    private int Originx;
    private final int Originy;
    
     GraphicsProgram(Client frame){
         
       //   setSize(new Dimension(700, 300));
      //    this.setPreferredSize(new Dimension(700,300);
          monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
          this.frame = frame;
          afisArray = false;
          
          // initializarea filtrelor
          blueFiltru = new FiltruOne(this); 
          redFiltru = new FiltruTwo(this);
          
          Originy = 0;
          Originx = 0;
          
     }

      public void paintComponent(Graphics g){
  
               
	    //   super.paintComponent(g);     
              int w, h;
              if(afisArray == false ){
                   blueFiltru.receiveData(frame.getText(), g,Originy, Originx);  
                   Originx += blueFiltru.findHeight();     
              }

              if(afisArray == true){
                  
                   ArrayList< String> TextAl = frame.getLockedText();
                   redFiltru.receiveData(TextAl, g,Originy, Originx);
              
                  Originx += redFiltru.findHeight();  
                  afisArray = false;
                  frame.setLocketText(); 
                  
              } 
      }
              
      public void changeAfis(){
            afisArray = true;
      }
}