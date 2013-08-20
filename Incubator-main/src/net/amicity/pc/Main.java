package net.amicity.pc;

import net.amicity.common.core.ContextManager;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.WirelessModule;

/**
 * @author cristian
 * 
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// external entity that starts everything

		ContextCore cc = new ContextCore();

		WirelessModule wm = new WirelessModule();
		wm.connect(cc);
		wm.obtainData();

		ContextManager cm = new ContextManager(cc);
		cm.run();

		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.run();

	}
}
