package net.amicity.pc;

import net.amicity.common.core.ContextManager;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.*;



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
		cm.start();
		wd.connect(cc);
	}

}
