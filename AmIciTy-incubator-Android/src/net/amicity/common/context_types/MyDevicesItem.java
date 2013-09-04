package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.communications.Connection;
import net.amicity.common.core.ContextTypes;


/**
 * An item that says on what devices i'm connected
 * @author cristian
 *
 */
public class MyDevicesItem extends AbstractItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	ArrayList<Connection> myDevices;
	
	/**
	 * The Constructor that initiates the ArrayList
	 */
	public MyDevicesItem() {
		myDevices = new ArrayList<Connection>();
		type = ContextTypes.DEVICES_CONTEXT;
	}
	
	/**
	 * @return the array list of my devices
	 */
	public ArrayList<Connection> getMyDevices() {
		return this.myDevices;
	}
	
	/**
	 * @param newDevices new Devices to add for me;
	 */
	public void setMyDevices(ArrayList<Connection> newDevices) {
		myDevices.addAll(newDevices);
	}

}
