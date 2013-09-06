/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.common.intelligence;

import java.net.InetAddress;
import java.net.UnknownHostException;

import net.amicity.android.MainActivity;
import net.amicity.android.communications.DefaultNetLink;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.LocationItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;

/**
 * A DummyClass that sends information to the server if it is in Canti
 * 
 * @author cristian
 * 
 */
public class DummyMessage implements IntelligenceModule {

	/**
	 * The ContextStorage
	 */
	ContextStorage cs;
	/**
	 * The ContextCore
	 */
	ContextCore cc;
	/**
	 * The location
	 */
	String location;
	/**
	 * DefaultNetLink for connecting to server
	 */
	DefaultNetLink d;
	/**
	 * The main activity.
	 */
	MainActivity act;

	/**
	 * @param cc
	 * @param act
	 */
	public DummyMessage(ContextCore cc, MainActivity act) {
		this.cc = cc;
		this.cs = cc.getContextStorage();
		this.act = act;
		d = new DefaultNetLink(act);
	}

	@Override
	public void invoke() {
		location = ((LocationItem) cs.get(ContextTypes.LOCATION_CONTEXT)).location;
		if (location.equals("CANTI"))
			try {
				System.out.println(" conectare!!");
				d.createConnection(
						new Connection(InetAddress.getByName("172.16.15.223"),
								"", 4500),
						new Connection(InetAddress.getLocalHost(), cc
								.getUsername(), 4500));
			}
			catch (UnknownHostException e) {
				e.printStackTrace();
			}

	}
}
