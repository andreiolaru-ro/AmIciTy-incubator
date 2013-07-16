package exercise.vlad.client;

import java.awt.*;
import javax.swing.*;
import java.util.*;


/**
 * the canvas used in JFrame, where the text will be shown and filtered
 * @author vlad
 *
 */
class GraphicsProgram extends JPanel{
	  
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * the frame which contains the canvas
     * used for receveing the data such as the text
     * and controlling the other members of the frame
     */
    private Client frame;
    
    // pentru a stii daca trebuie sa afisez un text nou venit sau textul cat timp a fost blocat
    /**
     * used for knowing whether the text must be drawn using blue Filter 
     * or a red Filter 
     */
    private boolean afisArray; 
    /**
     * instance of the filter which colors the word "blue" with blue
     */
    private FiltruOne blueFiltru;
    /**
     * instance of the filter which colors the word "red" with red
     */
    private FiltruTwo redFiltru;
    /**
     * variable which says from where to draw the next String 
     */
    private int Originx;
    
    /**
     * variable which says from where to draw the next String 
     */
    private final int Originy;
    
     /**
     * @param frame : instance of the frame to get information from the components 
     * belonging to the frame 
     */
    GraphicsProgram(Client frame){
         
     //   new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
          this.frame = frame;
          afisArray = false;
          
          blueFiltru = new FiltruOne(); 
          redFiltru = new FiltruTwo();
          
          Originy = 0;
          Originx = 0;
          
     }

      @Override
	public void paintComponent(Graphics g){
  
               
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
              
      /**
       * called bye the Client instance to inform GraphicProgram instance that it
       * has to draw an ArrayList
     */
    public void changeAfis(){
            afisArray = true;
      }
}