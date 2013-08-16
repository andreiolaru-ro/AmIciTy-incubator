/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru , Vlad Herescu, Cristian Radu Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
