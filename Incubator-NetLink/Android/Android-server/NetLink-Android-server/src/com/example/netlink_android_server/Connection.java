/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, ''Azgabast''.
 * 
 * This file is part of NetLink-PC.
 * 
 * NetLink-PC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * NetLink-PC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with NetLink-PC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.example.netlink_android_server;

import java.net.InetAddress;

/**
 * @author ''Azgabast''
 * 
 */
public class Connection {

	/**
	 * The time passed since this connection was created.
	 */
	long lifeSpan;
	/**
	 * The Ip of the device to connect with.
	 */
	InetAddress ip;

	/**
	 * The id of the device to connect with.
	 */
	String id;

	/**
	 * The port used for the connection.
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
	 * @param port
	 *            The port for the connection
	 */
	public Connection(InetAddress ip, String id, int port) {
		this.ip = ip;
		this.id = id;
		this.s = State.Off;
		this.port = port;
	}

	Connection() {
		super();
	}

	/**
	 * @param s
	 *            The state to be changed with.
	 * 
	 */
	public void setState(State s) {
		this.s = s;
		if (this.s == State.On)
			lifeSpan = System.currentTimeMillis();
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
	 * @return The state of the connection.
	 */
	public State getState() {
		return this.s;
	}

	/**
	 * @return The port for the current connection.
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port
	 *            The port for the current connection.
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @return The time this connection was on
	 */
	public long getConnectionTime() {
		return System.currentTimeMillis() - lifeSpan;
	}

}
