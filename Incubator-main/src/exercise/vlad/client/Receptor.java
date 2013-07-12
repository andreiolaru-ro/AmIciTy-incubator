package exercise.vlad.client;

// clasa Receiver se ocupa cu captarea streamului 

import java.net.*;
import java.io.*;
import java.util.*;


class Receiver extends Thread{
    
    private Client reDirect;
    
    // primeste ca argument instanta ferestrei pentru a avea acces la TextArea
    Receiver(Client c){
        reDirect = c;
    }
    
    
    // in functia run se stabileste conexiunea cu serverul
    // primeste mesaje intr-un while
    // asteapa sa primeasca mesajul OVER, dupa care iese din bucla while
    // la fiecare mesaj primit se determina si momentul in care a fost primit 
    // mesajul si timpul sunt afisate in TextArea
    public void run(){
        
         Socket kkSocket = null;
	 BufferedReader in = null;

         // realizeaza conexiunea
	 try {
	       kkSocket = new Socket("localhost", 4444);
	       in = new BufferedReader(new InputStreamReader(kkSocket.getInputStream()));
	 } 
         catch (UnknownHostException e) {
	            System.err.println("Don't know about host: taranis.");
	            System.exit(1);
	 } catch (IOException e) {
	         System.err.println("Couldn't get I/O for the connection to: taranis.");
	         System.exit(1);
	 }
 
	  String fromServer;

	  try{
              
            while(true){  
                
                // asteapta ca serverul sa aiba ceva de transmis
                while ((fromServer = in.readLine()) == null) {} 
                reDirect.setText( fromServer);
               
            }
            
	  } 
          catch (IOException e){
	    	   System.exit(1);
	  }
          try{
          
            in.close();
            kkSocket.close();
          }
          catch(IOException e){
              System.out.println("Eroare la inchidere socket ");
          }
    }
}