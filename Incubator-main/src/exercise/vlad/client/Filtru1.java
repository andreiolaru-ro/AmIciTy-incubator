package exercise.vlad.client;
// scrie textul "albastru" cu albastru intr-o propozitie

import java.awt.*;


/**
 * 
 * this filter renders with blue the word "blue" included in the text received
 * when the button ist unblocked
 * @author vlad
 *
 */
class FiltruOne implements Filtre{
    
    
    /**
     * the font used for rendering
     */
    private static Font monoFont;
    /**
     * variabile which receive's the value of the text for render
     */
    private String text;       
    /**
     * variabile belonging to the canvas used for rendering
     */
    private Graphics g;
    /**
     * the place ( height) from which to render the text
     */
    private int heightOrigin;       // locul de unde se va face afisarea
    /**
     *  the place ( width) from which to render the text
     */
    private int widthOrigin;
    /**
     * variable which has the value of the height of the String rendered
     */
    private int Height;             // variabila care va salva inaltimea textului
    
    /**
     * constructor of the class to initialise general data
     */
    FiltruOne(){
        
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
        Height = 0;
    }
    
    @Override
	public void receiveData(Object textRecv, Graphics gRecv,int width, int height){
        
        this.text =(String) textRecv;
        this.g = gRecv;
        heightOrigin = height;
        widthOrigin = width;
        setText();
        
    }
    
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
    
    @Override
	public void compareWord(String wordCompared){
        if(wordCompared.compareTo("albastru") == 0)
                g.setColor(Color.BLUE);
        else
                 g.setColor(Color.BLACK);
 
    }
    
    @Override
	public int findHeight(){
        return Height;
    }
}