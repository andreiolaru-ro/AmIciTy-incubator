package net.amicity.pc;

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
		wd.connect(cc);
	}

}
