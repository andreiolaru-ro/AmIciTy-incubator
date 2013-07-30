/*******************************************************************************
 * Copyright (c) 2013 ''Azgabast''.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ''Azgabast'' - initial API and implementation
 ******************************************************************************/
import java.net.InetAddress;


/**
 * @author ''Azgabast''
 *
 */
public class Connection
{
	/**
	 *  The Ip of the device to connect with.
	 */
	InetAddress ip;
	
	/**
	 * The id of the device to connect with.
	 */
	String id;
	/**
	 * 
	 * The list of possible states of a connection.
	 *
	 */
	enum State{
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
	 * @param ip The device ip.
	 * @param id The device id.
	 * @param s The state of the device.
	 */
	public Connection( InetAddress ip, String id , State s) {
		this.ip= ip;
		this.id = id;
		this.s = s;
	}
	
	/**
	 * @param s The state to be changed with.
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
	 * @return The state of the connection.
	 */
	public State getState() {
		return this.s;
	}
	
	
}
