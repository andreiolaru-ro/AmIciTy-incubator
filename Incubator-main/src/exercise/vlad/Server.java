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


/**
 * Strings to be drawn
 * @author vlad
 *
 */
interface Strings{
    /**
     * constant String 1
     */
    public String s1 = "Cerul este albastru";
    /**
     * 
     * constant String 2
     */
     
    public String s2 = "Marea isi schimba culoarea din albastru in verde";
    /**
     * constant String 3
     */
    public String s3 = "Ana are un mar rosu ";
    /**
     * constant String 4
     */
    public String s4 = "Facultatea Automatica este grea";
    /**
     * constant String 5
     */
    public String s5 = "A foolosit mult albastru in pictura";
    /**
     * constant String 6
     */
    public String s6 = "Mergem la picnic si soarele este rosu ";
    /**
     * constant String 7
     */
    public String s7 = "Culorile echipei sunt albastru si rosu ";
}

/**
 * class which transmits Strings to the Client
 * @author vlad
 *
 */
public class Server {
	
    /**
     * instance which writes random Strings frm interface Strings
     */
    PrintWriter out;
    /**
     * 
     */
    String text;
    /**
     * @param arg : param received
     * @throws IOException : due to the fact that it contains instance of Server
     */
    public static void main(String arg[])throws IOException{
        Server s  = new Server();
        s.start();
    }
    /**
     * @throws IOException : because of the work with Server and Client 
     */
    void start() throws IOException{
		
        
	ServerSocket serverSocket = null;
	try {
		/* portul serverului este 4444 */
		// imi da warning pentru ca nu este inchis
		// tr s ama gandesc la o metoda de inchidere a serverului
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
                 System.out.println("Error to print");
             }
             System.out.println(randomNr);
        }
        
//	out.close();
//	clientSocket.close();
 //       serverSocket.close();
	   	
        }
}
