package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;


/**
 * result of wireless sensors
 * @author vlad
 */
public class WirelessItem implements ContextItem{
	/**
	 * enum used to know the ContextItem's type by ContextManager
	 */
	ContextTypes type;
	/**
	 * arraylist of wireless netowrks detected
	 */
	public ArrayList <String> wifiDetected;
	/**
	 * initialising the members
	 */
	public WirelessItem(){
		type = ContextTypes.WIRELESS_CONTEXT; 
		wifiDetected = new ArrayList<String>();
	}
	
}