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
package net.amicity.common.communications;

import java.net.Socket;

/**
 * @author cristian
 * 
 */

public interface NetLink {

	/**
	 * @param c
	 *            -> the user you want to send
	 * @param o
	 *            -> the object you want to send
	 */
	public void send(Connection c, Object o);

	/**
	 * @param port
	 *            -> the server port
	 * @param msgR
	 *            -> MessageReceiver for execute commands
	 */
	public void initializeReceival(int port, MessageReceiver msgR);

	/**
	 * @param server
	 *            -> server to connect
	 * @param me
	 *            -> send my conncetion
	 */
	void createConnection(Connection server, Connection me);

	/**
	 * @param port
	 *            -> the server port
	 */
	void serverReceival(int port);

	/**
	 * clients receive data from server
	 * @param server the socket server
	 * @param msgR the message receiver
	 */
	void receiveFromServer(Socket server, MessageReceiver msgR);

}
