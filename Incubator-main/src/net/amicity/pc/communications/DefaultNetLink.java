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
package net.amicity.pc.communications;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
import net.amicity.common.communications.ConnectionManager;
import net.amicity.common.communications.MessageReceiver;
import net.amicity.common.communications.NetLink;

/**
 * @author cristian
 * 
 */
public class DefaultNetLink implements NetLink {

	/**
	 * The connection manager for all connections available.
	 */
	ConnectionManager manager;

	/**
	 * 
	 */
	public DefaultNetLink() {
		manager = new ConnMgr();
	}

	@Override
	public void send(Connection c, Object o) {
		Socket client;
		ObjectOutputStream out;

		try {
			client = new Socket(c.getIp(), c.getPort());
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(o);
			out.flush();
			out.close();
			client.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeReceival(int port, final MessageReceiver msgR) {
		final ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);

			System.out.println("Server started on port: " + port);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Socket client = serverSocket.accept();
							ObjectInputStream in = new ObjectInputStream(
									client.getInputStream());
							Object obj = in.readObject();
							msgR.receive(obj);
							in.close();
							client.close();
						}
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}).start();
		}
		catch (IOException e) {
			System.out.println("Could not listen on port: " + port);
			e.printStackTrace();
		}
	}

	@Override
	public void createConnection(Connection server, Connection me) {
		Socket client;
		ObjectOutputStream out;

		try {
			client = new Socket(server.getIp(), server.getPort());
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(me);
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void serverReceival(int port) {
		final ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);

			System.out.println("Server started on port: " + port);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Socket client = serverSocket.accept();
							ObjectInputStream in = new ObjectInputStream(
									client.getInputStream());
							Object obj = in.readObject();
							Connection newCon = (Connection) obj;
							newCon.setSocket(client);
							manager.addConnection(newCon);
						}
						catch (IOException e) {
							e.printStackTrace();
						}
						catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}

				}

			}).start();
		}
		catch (IOException e) {
			System.out.println("Could not listen on port: " + port);
			e.printStackTrace();
		}
	}

	/**
	 * @return the Connection manager
	 */
	public ConnectionManager getConnectionManager() {
		return this.manager;
	}

}
