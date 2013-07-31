/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Neagoe.
 * 
 * This file is part of NetLink-PC.
 * 
 * NetLink-PC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * NetLink-PC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with NetLink-PC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package netlink;

import java.net.InetAddress;

/**
 * @author ''Azgabast''
 * 
 */
public class Connection {

	/**
	 * The Ip of the device to connect with.
	 */
	InetAddress ip;

	/**
	 * The id of the device to connect with.
	 */
	String id;

	/**
	 * The port of the device to connect with.
	 */
	int port;

	/**
	 * 
	 * The list of possible states of a connection.
	 * 
	 */
	enum State {
		/**
		 * State on.
		 */
		On, /**
		 * State off.
		 */
		Off, /**
		 * State timedout.
		 */
		Timedout
	}

	/**
	 * The state of the connection.
	 */
	State s;

	/**
	 * @param ip
	 *            The device ip.
	 * @param id
	 *            The device id.
	 * @param s
	 *            The state of the device.
	 * @param port
	 *            The device port
	 */
	public Connection(InetAddress ip, String id, State s, int port) {
		this.ip = ip;
		this.id = id;
		this.s = s;
		this.port = port;
	}

	/**
	 * @param s
	 *            The state to be changed with.
	 * 
	 */
	public void setState(State s) {
		this.s = s;
	}

	/**
	 * @return The ip of the connection.
	 */
	public InetAddress getIp() {
		return this.ip;
	}

	/**
	 * @return The id of the device connected with.
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return The port of the device connected with.
	 */
	public int getPort() {
		return this.port;
	}

	/**
	 * @return The state of the connection.
	 */
	public State getState() {
		return this.s;
	}

}
