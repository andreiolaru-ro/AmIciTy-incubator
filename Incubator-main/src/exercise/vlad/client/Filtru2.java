package exercise.vlad.client;

// scrie textul "rosu" cu rosu intr-o propozitie

import java.awt.*;
import java.util.*;
import javax.swing.*;

class FiltruTwo implements Filtre{
    private static Font monoFont;
    private String text;
    private GraphicsProgram GP;
    private Graphics g;
    private int heightOrigin;
    private int widthOrigin;
    private int height;
    
    FiltruTwo(GraphicsProgram GP){
        
        this.GP = GP;
        monoFont = new Font("Monospaced", Font.BOLD | Font.ITALIC, 18);
    }
     public void receiveData(Object  arrayList, Graphics g,int width, int height){
         
         ArrayList al = (ArrayList) arrayList;
         this.g = g;
         int i;
         heightOrigin = height;
         for(i=0; i< al.size(); i++){
             widthOrigin = width;
             text =(String) al.get(i);
             setText();
         }
        
    }
     public void setText(){
         
         String s[]= text.split(" ");   
         int w,h=0, i;
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
         
     }
     public void compareWord(String wordCompared){

        if(wordCompared.compareTo("rosu") == 0)
                g.setColor(Color.red);
        else
                 g.setColor(Color.BLACK);
        
     }
     public int findHeight(){
        int x = height;
        height = 0;
        return x;
     }
 
}