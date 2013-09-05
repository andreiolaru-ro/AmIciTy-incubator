package net.amicity.pc;

import java.util.ArrayList;
import java.util.HashMap;

import net.amicity.common.communications.SimplePeerMachinesManager;
import net.amicity.common.core.ContextManager;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.DummyDevicesModule;
import net.amicity.common.intelligence.DummyMessage;
import net.amicity.common.intelligence.FileAnalizerModule;
import net.amicity.common.intelligence.LocationModule;
import net.amicity.common.intelligence.ShowPerceptionModule;
import net.amicity.pc.sensors.ChangeDetectorModule;
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
				
		
		// Create ContextCore
		final ContextCore cc = new ContextCore();

		PCInterface pci = new PCInterface();
		while (pci.getUserName().length() == 0) {
			System.out.flush();
		}


		System.out.println(" USER IS: " + pci.getUserName());
		cc.setUsername(pci.getUserName());
		
		// start the files monitorization
		ChangeDetectorModule cdm = new ChangeDetectorModule();
		cdm.startTimer();

		// Create intelligence modules

		FileAnalizerModule fam = new FileAnalizerModule(cc, cdm);
		LocationModule lm = new LocationModule(cc);
		SimplePeerMachinesManager peer =  new SimplePeerMachinesManager();
		DummyMessage dm = new DummyMessage(cc, peer);
		DummyDevicesModule ddm = new DummyDevicesModule(cc);
		ShowPerceptionModule spm = new ShowPerceptionModule(cc);



		// make the link between ContextTypes and intelligence modules related
		// to type
		final HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm = new HashMap<ContextTypes, ArrayList<IntelligenceModule>>();


		ArrayList<IntelligenceModule> iModules = new ArrayList<IntelligenceModule>();
		iModules.add(lm);
		hm.put(ContextTypes.WIRELESS_CONTEXT, iModules);
		ArrayList<IntelligenceModule> iModules2 = new ArrayList<IntelligenceModule>();
		iModules2.add(dm);
		hm.put(ContextTypes.LOCATION_CONTEXT, iModules2);
		ArrayList<IntelligenceModule> iModules3 = new ArrayList<IntelligenceModule>();
		iModules3.add(ddm);
		hm.put(ContextTypes.DEVICES_CONTEXT, iModules3);
		ArrayList<IntelligenceModule> iModules4 = new ArrayList<IntelligenceModule>();
		iModules4.add(fam);
		hm.put(ContextTypes.FILE_CONTEXT, iModules4);
		ArrayList<IntelligenceModule> iModules5 = new ArrayList<IntelligenceModule>();
		iModules5.add(spm);
		hm.put(ContextTypes.PERCEPTION_CONTEXT, iModules5);

		// start sensors services

		WirelessModule wm = new WirelessModule();
		wm.connect(cc);

		// Create the ContextManger
		ContextManager cm = new ContextManager(cc, hm);
		cm.start();
		// Create the Notification Dispatcher
		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.start(); 


	}
}
