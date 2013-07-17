package exercise.vlad.client;
// clasa Receiver se ocupa cu captarea streamului 

import java.net.*;
import java.io.*;


/**
 * class which has the purpose to catch text from the server
 *  it then redirects to the client thread
 * 
 * @author vlad
 *
 */
class Receiver extends Thread{
    
    /**
     * instance class Client to know where to redirect the received text
     */
    private Client reDirect;
	/**
	 * instance which saves the text received from Server
	 */
	private BufferedReader in;
    
    /**
     * @param c : instance of the Client which will receive the text
     */
    Receiver(Client c){
        reDirect = c;
    }
    
    
    // in function run is established the conection with the Server
    // the messages are received in a while
    @SuppressWarnings("null")
	@Override
	public void run(){
        
    	 Socket kkSocket= null;
	     in = null;

     // the connection is set
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
                
                while ((fromServer = in.readLine()) == null){
                	// this busy wating runs untill something 
                	//is received from Server
                }
                
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