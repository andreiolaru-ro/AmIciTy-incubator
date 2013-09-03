package net.amicity.pc.sensors;

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

	@Override
	public void connect(ContextCore cc) {
		myCore = cc;
		
		DefaultNetLink d = new DefaultNetLink();
		d.initializeReceival(4500, new DefaultMessageReceiver());
		
	}
	
}
