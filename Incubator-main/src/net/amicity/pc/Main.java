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

		final ContextCore cc = new ContextCore();

		WirelessModule wm = new WirelessModule();
		wm.connect(cc);

		new Thread(new Runnable() {

			@Override
			public void run() {
				ContextManager cm = new ContextManager(cc);
				cm.run();
			}
		}).start();

		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.run();

	}
}
