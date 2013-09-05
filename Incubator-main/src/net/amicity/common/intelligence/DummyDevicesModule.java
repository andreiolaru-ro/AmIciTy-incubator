package net.amicity.common.intelligence;

import java.util.ArrayList;

import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.communications.Connection;


/**
 * A class for showing all my devices
 * @author cristian
 *
 */
public class DummyDevicesModule implements IntelligenceModule{

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * An arraylist of all my devices
	 */
	ArrayList<Connection> myDevices;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 */
	public DummyDevicesModule(ContextCore cc) {
		myCore = cc;
	}
	
	@Override
	public void invoke() {
		myDevices = ((MyDevicesItem) myCore.getContextStorage().get(
				ContextTypes.DEVICES_CONTEXT)).getMyDevices();
		for(Connection c : myDevices) {
			System.out.println(c.getId());
		}
		//System.out.println("This are my devices: " + myDevices);
	}
	
}
