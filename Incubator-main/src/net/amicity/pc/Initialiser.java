package net.amicity.pc;

import net.amicity.common.core.ContextManager;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.*;



/**
 * 
 * clasa de teste, nu sunt sigur daca asta ramane
 * @author vlad
 *
 */
public class Initialiser
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{

		WirelessModule wd = new WirelessModule();
	     ContextCore cc = new ContextCore();
		ContextManager cm = new ContextManager(cc);
		NotificationDispatcher notifier = new NotificationDispatcher(cc);
		cm.start();
		notifier.start();
		wd.connect(cc);
	}

}
