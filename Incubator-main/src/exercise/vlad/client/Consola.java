package exercise.vlad.client;

import java.awt.*;
import javax.swing.*;
import java.util.*;


/*class TextType{
	Object o;
	int type;
	TextType(ArrayList)
	
	
}*/

/**
 * the canvas used in JFrame, where the text will be shown and filtered
 * @author vlad
 *
 */
class GraphicsProgram extends JPanel{
	/**
	 * an array of the types to know what fliters must be used
	 * 0 = blue filter, 1 = red filter
	 */
	private ArrayList<Integer> typeString;
	/**
	 * an array of old strings (history of strings to be repainted) 
	 */
	private ArrayList<String> StringReceived;
	
	
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
         
          this.frame = frame;
          afisArray = false;
          
          blueFiltru = new FiltruOne(); 
          redFiltru = new FiltruTwo();
          
          Originy = 0;
          Originx = 0;
          
          typeString = new ArrayList<Integer>();
          StringReceived = new ArrayList<String>();
          
          setBackground(Color.white);
          this.setSize(new Dimension(640,300));
          setLayout(new BorderLayout());
          
          
     }

      @Override
	public void paintComponent(Graphics g){
    	    
    	  
    	super.paintComponent(g);  
    	  
    	int i;
        Originx = 0;
    	
    	for(i = 0; i < StringReceived.size(); i++ ){
    		
    		
    		@SuppressWarnings("boxing")
			int integer = typeString.get(i);
			if(integer == 0){
				
    			String s = StringReceived.get(i) ;
    			blueFiltru.receiveData(s, g,Originy, Originx);  
                Originx += blueFiltru.findHeight();  
    			
    		}
			if(integer == 1){
				
				String s = StringReceived.get(i) ;
    			redFiltru.receiveData(s, g,Originy, Originx);  
                Originx += redFiltru.findHeight();  
				
				
    		} 

    	}
  
               
	    if(afisArray == false ){
	    	
	    		   String s = frame.getText();
                   blueFiltru.receiveData(s, g,Originy, Originx);  
                   Originx += blueFiltru.findHeight(); 
                   blueFiltru.addStringsToHistory(typeString, StringReceived);
                   
        }

        if(afisArray == true){
                  
                  ArrayList< String> TextAl = frame.getLockedText();
                  
                  i=0;
                  for(i=0; i<TextAl.size(); i++){
                	  redFiltru.receiveData(TextAl.get(i), g,Originy, Originx);
                	  Originx += redFiltru.findHeight(); 
                	  redFiltru.addStringsToHistory(typeString, StringReceived);
                  }
                  
                  
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