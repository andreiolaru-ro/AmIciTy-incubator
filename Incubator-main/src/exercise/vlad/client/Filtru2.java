package exercise.vlad.client;
// scrie textul "rosu" cu rosu intr-o propozitie

import java.awt.*;
import java.util.*;


/**
 *  this filter renders with red the word "red" included in the text belonging
 *  to Arraylist  Client.LockedT
 * @author vlad
 *
 */
class FiltruTwo implements Filtre{
	
    /**
     * the Font used for rendering
     */
    private static Font monoFont;
    /**
     *  varibile which receives the value of the string for redenring from
     *  method receiveData
     */
    private String text;
    
    
    /**
     * variable for redendering the graphic belonging to Canvas 
     */
    private Graphics g;
    /**
     * variable for knowing where to drawu the next String received 
     */
    private int heightOrigin;
    
    /**
    
     * variable for knowing where to draw the next String received 
    
     */
    private int widthOrigin;
    
    /**
     * variable which has the value of the height of the String rendered
     */
//    private int height;
    
   /**
      variabile which saves the arraylist received 
    */
 //   private ArrayList<String> al;
    
    /**
     * variable which has the value of the height of the String rendered
     */
    private int Height = 0;
    
    /**
     * constructor of this class:
     * initialises general dates ( font)
     */
    FiltruTwo(){
        
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
    }
    
    @Override
   	public void receiveData(Object textRecv, Graphics gRecv,int width, int height1){
           
           this.text =(String) textRecv;
           this.g = gRecv;
           heightOrigin = height1;
           widthOrigin = width;
           setText();
           
       }
    
    
 /*   @SuppressWarnings("unchecked")
	@Override
	public void receiveData(Object  arrayList, Graphics gRecv,int width, int heightRecv){
         
         
		 al = ((ArrayList<String>) arrayList);
         this.g = gRecv;
         int i;
         heightOrigin = heightRecv;
         for(i=0; i< al.size(); i++){
             widthOrigin = width;
             text = al.get(i);
             setText();
         }
        
    } */
    
    @Override
   	public void setText(){
           
            String s[]= text.split(" ");   
            int w, i;
            g.setFont(monoFont);
            FontMetrics fm = g.getFontMetrics();
            
            for( i = 0 ; i < s.length; i++ ){
                
                compareWord(s[i]);
                w = fm.stringWidth(s[i] + " ");
                g.drawString(s[i] + " ", widthOrigin, heightOrigin);
                widthOrigin = widthOrigin + w;

            }   
            Height =  fm.getAscent();
    }
    
/*     @Override
	public void setText(){
         
         String s[]= text.split(" ");   
         int w, i;
         g.setFont(monoFont);
         FontMetrics fm = g.getFontMetrics();
         for( i = 0 ; i < s.length; i++ ){
             
             g.setColor(Color.red);
             compareWord(s[i]);
            
             w = fm.stringWidth(s[i] + " ");
             g.drawString(s[i] + " ", widthOrigin , heightOrigin);
             widthOrigin = widthOrigin + w;
 
         }  
         
         heightOrigin += fm.getAscent();
         height +=fm.getAscent();
         
     } */
     @Override
	public void compareWord(String wordCompared){

        if(wordCompared.compareTo("rosu") == 0)
                g.setColor(Color.red);
        else
                 g.setColor(Color.BLACK);
        
     }
/*     @Override
	public int findHeight(){
        int x = height;
        height = 0;
        return x;
     }*/
     
     @Override
	public int findHeight(){
         return Height;
     }
     
     @SuppressWarnings("boxing")
	@Override
     public void addStringsToHistory(ArrayList<Integer> type, ArrayList<String> Strings){
     	type.add(1);
     	Strings.add(text);
     	
     }
/*	public void addStringsToHistory(ArrayList<Integer> type, ArrayList<String> Strings){
    	 
    	int i;
    	for( i = 0 ; i < al.size(); i++ ){
    		type.add(1);
    		Strings.add(al.get(i));
    	}
     	
     }*/
 
}