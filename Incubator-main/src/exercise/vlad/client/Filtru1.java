package exercise.vlad.client;

// scrie textul "albastru" cu albastru intr-o propozitie

import java.awt.*;
import javax.swing.*;


class FiltruOne implements Filtre{
    
    private static Font monoFont;
    private String text;            // pentru salvarea liniei de text
    private GraphicsProgram GP;     
    private Graphics g;
    private int heightOrigin;       // locul de unde se va face afisarea
    private int widthOrigin;
    private int Height;             // variabila care va salva inaltimea textului
    
    // se realizeaza initializarile
    FiltruOne(GraphicsProgram GP){
        
        this.GP = GP;
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
        Height = 0;
    }
    public void receiveData(Object text, Graphics g,int width, int height){
        
        this.text =(String) text;
        this.g = g;
        heightOrigin = height;
        widthOrigin = width;
        setText();
        
    }
    
    // descompun textul intr-o multime de cuvinte si analizez fiecare cuvant daca este indeplinita conditia
    public void setText(){
        
         String s[]= text.split(" ");   
         int w,h, i;
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
    
    // verific daca cuvantul indeplineste conditia
    // daca da, schimb culoarea textului
    public void compareWord(String wordCompared){
        if(wordCompared.compareTo("albastru") == 0)
                g.setColor(Color.BLUE);
        else
                 g.setColor(Color.BLACK);
 
    }
    
    public int findHeight(){
        return Height;
    }
}