package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.communications.Connection;
import net.amicity.common.core.ContextTypes;


/**
 * 
 * same as MyDevicesItem , but from other user
 * @author vlad
 *
 */
public class OtherDevicesItem extends AbstractItem
{
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
	public OtherDevicesItem() {
		myDevices = new ArrayList<Connection>();
		type = ContextTypes.OTHER_DEVICES_CONTEXT;
	}

	/**
	 * @return the array list of my devices
	 */
	public ArrayList<Connection> getTheDevices() {
		return this.myDevices;
	}

	/**
	 * @param newDevices
	 *            new Devices to add for me;
	 */
	public void setHisDevices(ArrayList<Connection> newDevices) {
		myDevices.addAll(newDevices);
	}
	
	
}
