package testing;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * Tester for the communications infrastructure.
 * 
 * @author Andrei Olaru
 *
 */
public class CommunicationsTester
{
	/**
	 * The port onto which internet (TCP) connections should be made.
	 * <p>
	 * It reads "city"
	 */
	final static int LISTENING_PORT = 3489;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO implement and test these functionalities:
		
		LocationDetector d = new WifiLocationDetection();
		System.out.println(d.getLocation());
		PeerMachinesManager pm = new SimplePeerMachinesManager();
		Station m = pm.getServerForLocation(d.getLocation());
		System.out.println(m.Ip);
		
		
		DefaultNetLink test = new DefaultNetLink();
		    
		 try
			{
				Connection c = new Connection(InetAddress.getByName(m.Ip), "Server", 4500);
				ConnMgr connectionManager = new ConnMgr(); 
				connectionManager.addConnection(c);
				test.send(c, "HI!");
			}
			
			catch (UnknownHostException e)
			{
				e.printStackTrace();
			}
		
		
		// ConnectionManager cm = new ConnectionManager();
		// cm.addConnection(new Connection(m));
		// NetLink net = new InetLink();
		// net.initializeReceival(LISTENING_PORT, new EchoMessageReceiver());
		// net.send(cm.getConnectionTo(m.getID()), "Hello World");
	}
	
}
