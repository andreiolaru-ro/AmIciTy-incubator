package net.amicity.common.context_types;

import net.amicity.common.core.ContextTypes;

/**
 * this class contains the user's location. It is passed to the infrastructure
 * in order to connect to server
 * 
 * @author vlad
 * 
 */
public class LocationItem extends AbstractItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the location detected to connect to its server
	 */
	public String location;

	/**
	 * initialising the members
	 * 
	 * @param locationDetected
	 *            : user's location
	 */
	public LocationItem(String locationDetected) {
		type = ContextTypes.LOCATION_CONTEXT;
		location = locationDetected;

	}
}
