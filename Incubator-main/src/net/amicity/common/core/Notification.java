package net.amicity.common.core;


/**
 * Notification added by Contextmanager and pulled from NotificationManager
 * in order to notify the Intelligent Modules to get a ContextItem 
 * 
 * @author vlad
 *
 */
public class Notification
{
	/**
	 *  the type of notification to know which intelligent module should notify
	 */
	IntelligentTypes myNotified;
	/**
	 * @param toBeNotified : the value given to MyNotified
	 */
	public Notification(IntelligentTypes toBeNotified){
		myNotified = toBeNotified;
	} 

}
