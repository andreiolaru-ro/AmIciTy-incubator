<<<<<<< HEAD
nullnullpackage net.amicity.pc.sensors;
=======
nullnullnullpackage net.amicity.pc.sensors;
>>>>>>> refs/remotes/origin/development/architecture

import java.net.Socket;

import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultMessageReceiver;
import net.amicity.pc.communications.DefaultNetLink;


/**
 * @author cristian
 *
 */
public class ServerModule implements SensorModule {
	
	/**
	 * instance of ContextCore to acces its queues
	 */
	ContextCore myCore;
	/**
	 * the server socket
	 */
	private Socket server;
	
	/**
	 * @param server the server socket
	 */
	public ServerModule(Socket server) {
		this.server = server;
	}

	@Override
	public void connect(ContextCore cc) {
		myCore = cc;
		//listen from server:
		DefaultNetLink d = new DefaultNetLink();
		d.receiveFromServer(server, new DefaultMessageReceiver());
		//listen from other devices
		DefaultNetLink d2 = new DefaultNetLink();
		d2.initializeReceival(4501, new DefaultMessageReceiver());
	}
	
}
