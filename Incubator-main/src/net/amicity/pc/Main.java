package net.amicity.pc;

import java.util.ArrayList;
import java.util.HashMap;
import net.amicity.common.core.ContextManager;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.DummyMessage;
import net.amicity.common.intelligence.LocationModule;
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

		LocationModule lm = new LocationModule(cc);
		DummyMessage dm = new DummyMessage(cc);

		final HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm = new HashMap<ContextTypes, ArrayList<IntelligenceModule>>();
		ArrayList<IntelligenceModule> iModules = new ArrayList<IntelligenceModule>();
		iModules.add(lm);
		hm.put(ContextTypes.WIRELESS_CONTEXT, iModules);
		ArrayList<IntelligenceModule> iModules2 = new ArrayList<IntelligenceModule>();
		iModules2.add(dm);
		hm.put(ContextTypes.LOCATION_CONTEXT, iModules2);

		WirelessModule wm = new WirelessModule();
		wm.connect(cc);

		new Thread(new Runnable() {

			@Override
			public void run() {
				ContextManager cm = new ContextManager(cc, hm);
				cm.run();
			}
		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				NotificationDispatcher nd = new NotificationDispatcher(cc);
				nd.run();
			}

		}).start();

	}
}
