/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.pc;

import java.util.ArrayList;
import java.util.HashMap;

import net.amicity.common.core.ContextManager;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.DummyDevicesModule;
import net.amicity.common.intelligence.DummyMessage;
import net.amicity.common.intelligence.LocationModule;
import net.amicity.common.intelligence.PCFileTransfer;
import net.amicity.common.intelligence.SaveTransferedFiles;
import net.amicity.common.intelligence.ShowPerceptionModule;
import net.amicity.pc.intelligence.FileAnalizerModule;
import net.amicity.pc.intelligence.SimplePeerMachinesManager;
import net.amicity.pc.interfaces.PCInterface;
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
		ContextCore.setUsername(pci.getUserName());

		// start the files monitorization
		ChangeDetectorModule cdm = new ChangeDetectorModule();
		cdm.startTimer();

		// Create intelligence modules

		FileAnalizerModule fam = new FileAnalizerModule(cc, cdm);
		LocationModule lm = new LocationModule(cc);
		SimplePeerMachinesManager peer = new SimplePeerMachinesManager(cc, fam);
		DummyMessage dm = new DummyMessage(cc, peer);
		DummyDevicesModule ddm = new DummyDevicesModule(cc);
		ShowPerceptionModule spm = new ShowPerceptionModule(cc);
		SaveTransferedFiles stf = new SaveTransferedFiles(cc);
		PCFileTransfer pft = new PCFileTransfer(cc);

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
		ArrayList<IntelligenceModule> iModules6 = new ArrayList<IntelligenceModule>();
		iModules6.add(stf);
		hm.put(ContextTypes.TRANSFER_FILE_CONTEXT, iModules6);
		ArrayList<IntelligenceModule> iModules7 = new ArrayList<IntelligenceModule>();
		iModules7.add(peer);
		hm.put(ContextTypes.OTHER_DEVICES_CONTEXT, iModules7);
		ArrayList<IntelligenceModule> iModules8 = new ArrayList<IntelligenceModule>();
		iModules8.add(pft);
		hm.put(ContextTypes.ACCELEROMETER, iModules8);
		ArrayList<IntelligenceModule> iModules9 = new ArrayList<IntelligenceModule>();
		iModules9.add(peer);
		hm.put(ContextTypes.SEND_ITEM_CONTEXT, iModules9);
		ArrayList<IntelligenceModule> iModules10 = new ArrayList<IntelligenceModule>();
		iModules10.add(peer);
		hm.put(ContextTypes.RECEIVED_ITEM_CONTEXT, iModules10);

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
