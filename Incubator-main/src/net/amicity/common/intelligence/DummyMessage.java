package net.amicity.common.intelligence;

import java.net.InetAddress;

import java.net.UnknownHostException;

import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.LocationItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.PCInterface;
import net.amicity.pc.communications.DefaultNetLink;
import net.amicity.common.communications.SimplePeerMachinesManager;

/**
 * A DummyClass that sends information to the server if it is in Canti
 * 
 * @author cristian
 * 
 */
public class DummyMessage implements IntelligenceModule {

	/**
	 * The ContextStorage
	 */
	ContextStorage cs;
	/**
	 * The ContextCore
	 */
	ContextCore cc;
	/**
	 * The location
	 */
	String location;
	/**
	 * DefaultNetLink for connecting to server
	 */
	DefaultNetLink d;
	
	/**
	 * list of all dives in room
	 */
	SimplePeerMachinesManager myPeerList;

	/**
	 * @param cc
	 */
	public DummyMessage(ContextCore cc, SimplePeerMachinesManager peerListReceived) {
		this.cc = cc;
		this.cs = cc.getContextStorage();
		d = new DefaultNetLink();
		myPeerList = peerListReceived;
	}

	@Override
	public void invoke() {
		
		location = ((LocationItem) cs.get(ContextTypes.LOCATION_CONTEXT)).location;
		String ip = myPeerList.getServerForLocation(location);
		
		try {
			PCInterface.addNotification("conectare");
			d.createConnection(
					new Connection(InetAddress.getByName(ip),
							"", 4500),
					new Connection(InetAddress.getLocalHost(), cc
							.getUsername(), 4500));
			PCInterface.addNotification("connected to 172.16.15.223");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		

	}
}
