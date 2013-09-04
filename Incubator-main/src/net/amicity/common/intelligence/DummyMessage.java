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
	 * @param cc
	 */
	public DummyMessage(ContextCore cc) {
		this.cc = cc;
		this.cs = cc.getContextStorage();
		d = new DefaultNetLink();
	}

	@Override
	public void invoke() {
		location = ((LocationItem) cs.get(ContextTypes.LOCATION_CONTEXT)).location;
		if (location.equals("CANTI"))
			try {
				PCInterface.addNotification("conectare");
				d.createConnection(
						new Connection(InetAddress.getByName("172.16.15.223"),
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
