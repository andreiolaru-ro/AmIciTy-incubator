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
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.LocationItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultNetLink;
import net.amicity.pc.intelligence.SimplePeerMachinesManager;
import net.amicity.pc.interfaces.PCInterface;

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
	 * list of all dives in room
	 */
	SimplePeerMachinesManager myPeerList;

	/**
	 * @param cc
	 * @param peerListReceived
	 */
	public DummyMessage(ContextCore cc,
			SimplePeerMachinesManager peerListReceived) {
		this.cc = cc;
		this.cs = cc.getContextStorage();
		d = new DefaultNetLink();
		myPeerList = peerListReceived;
	}

	@Override
	public void invoke() {

		location = ((LocationItem) cs.get(ContextTypes.LOCATION_CONTEXT)).location;
		String ip = myPeerList.getServerForLocation(location);

		try {
			PCInterface.addNotification("connecting..");
			d.createConnection(new Connection(InetAddress.getByName(ip), "",
					4500),
					new Connection(InetAddress.getByName(getLocalIpAddress()),
							ContextCore.getUsername(), 4500));
			PCInterface.addNotification("connected to server");
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @return A string representing the local ip adress
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& !inetAddress.getHostName().contains(":")) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
