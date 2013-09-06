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
package net.amicity.pc.sensors;

import java.net.Socket;

import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultMessageReceiver;
import net.amicity.pc.communications.DefaultNetLink;

/**
 * @author cristian
 * 
 */
public class ServerModule implements SensorModule {

	/**
	 * instance of ContextCore to acces its queues
	 */
	ContextCore myCore;
	/**
	 * the server socket
	 */
	private final Socket server;

	/**
	 * @param server
	 *            the server socket
	 */
	public ServerModule(Socket server) {
		this.server = server;
	}

	@Override
	public void connect(ContextCore cc) {
		myCore = cc;
		// listen from server:
		DefaultNetLink d = new DefaultNetLink();
		d.receiveFromServer(server, new DefaultMessageReceiver());
		// listen from other devices
		DefaultNetLink d2 = new DefaultNetLink();
		d2.initializeReceival(4501, new DefaultMessageReceiver());
	}

}
