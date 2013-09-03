package net.amicity.pc.sensors;

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
		DefaultNetLink d = new DefaultNetLink();
		d.receiveFromServer(server, new DefaultMessageReceiver());
		
	}
	
}
