package exercise.vlad;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vlad
 */

import java.io.*;
import java.net.*;
import java.util.*;


interface Strings{
    public String s1 = "Cerul este albastru";
    public String s2 = "Marea isi schimba culoarea din albastru in verde";
    public String s3 = "Ana are un mar rosu ";
    public String s4 = "Facultatea Automatica este grea";
    public String s5 = "A foolosit mult albastru in pictura";
    public String s6 = "Mergem la picnic si soarele este rosu ";
    public String s7 = "Culorile echipei sunt albastru si rosu ";
}

public class Server {
    PrintWriter out;
    String text;
    public static void main(String arg[])throws IOException{
        Server s  = new Server();
        s.start();
    }
    void start() throws IOException{
		
        
	ServerSocket serverSocket = null;
	try {
		/* portul serverului este 4444 */
		serverSocket = new ServerSocket(4444); 
				
	} catch (IOException e) {
		System.err.println("Nu pot sa ascult pe la usile altora");
		System.exit(1);
	}
			
	Socket clientSocket = null;
		
        System.out.println("astept sa se faca conexiunea");
        
	try{ /* identifica clientul */
	        clientSocket = serverSocket.accept();  
	} 
        catch (IOException e) {
	         System.err.println("Accept failed.");
	         System.exit(1);
	}
	  
         System.out.println("s-a facut conexiunea");
         // initializez variabila PrintWriter out, prin care fac redirectarea catre client
         out = new PrintWriter(clientSocket.getOutputStream(), true);
        
        
      Random randomGenerator = new Random();
        
       
        while(true){
            
             int randomNr= randomGenerator.nextInt(7);
             try{
                switch(randomNr){
                    case 0: out.println(Strings.s1); Thread.sleep(4000);  break;
                    case 1: out.println(Strings.s2); Thread.sleep(5000); break;
                    case 2: out.println(Strings.s3); Thread.sleep(3000); break;
                    case 3: out.println(Strings.s4); Thread.sleep(6000); break;
                    case 4: out.println(Strings.s5); Thread.sleep(2000); break;
                    case 5: out.println(Strings.s6); Thread.sleep(5000); break;
                    case 6: out.println(Strings.s7); Thread.sleep(3000); break;
                  
                }
             }
             catch(Exception ie){
                 
             }
             System.out.println(randomNr);
        }
        
	/*out.close();
	clientSocket.close();
        serverSocket.close();*/
	   	
        }
}
