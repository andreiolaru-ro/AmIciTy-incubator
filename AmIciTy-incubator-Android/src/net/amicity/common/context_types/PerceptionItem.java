package net.amicity.common.context_types;

import net.amicity.common.core.ContextTypes;


/**
 * A class for sending perceptions
 * @author cristian
 *
 */
public class PerceptionItem extends AbstractItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The accelerometer perception
	 */
	String action;
	
	/**
	 * The sound perception
	 */
	double value;
	
	/**
	 * The user that send perceptions
	 */
	String user;
	
	/**
	 * @param user the user that sends
	 * @param value the value of sound perception
	 * @param action the action from accelerometer perception
	 */
	public PerceptionItem(String user, double value, String action) {
		this.user = user;
		this.value = value;
		this.action = action;
		this.type = ContextTypes.PERCEPTION_CONTEXT;
	}

}
