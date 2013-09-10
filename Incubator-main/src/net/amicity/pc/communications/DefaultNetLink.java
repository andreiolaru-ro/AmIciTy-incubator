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
import java.util.ArrayList;

import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
import net.amicity.common.communications.ConnectionManager;
import net.amicity.common.communications.MessageReceiver;
import net.amicity.common.communications.NetLink;
import net.amicity.common.context_types.MessageItem;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.context_types.OtherDevicesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.interfaces.Anunt;
import net.amicity.pc.interfaces.HelpMessage;
import net.amicity.pc.sensors.ServerModule;

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
			client = new Socket(c.getIp(), 4501);
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

			System.out.println("Server with clients started on port: " + port);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Socket client = serverSocket.accept();
							System.out.println("accepted from client");
							ObjectInputStream in = new ObjectInputStream(
									client.getInputStream());
							Object obj = in.readObject();
							if(obj instanceof MessageItem){
								System.out.println("AM PRIMIT UN MESSAGEITEM");
							}
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
			ContextCore.setServerSocket(client);

			// Start the server
			ServerModule sm = new ServerModule(client);
			sm.connect(ContextCore.class.newInstance());

			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(me);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
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
							final Socket client = serverSocket.accept();
							ObjectInputStream in = new ObjectInputStream(client
									.getInputStream());
							Object obj = in.readObject();
							final Connection newCon = (Connection) obj;
							newCon.setSocket(client);
							manager.addConnection(newCon);

							ArrayList<Connection> other = manager
									.getOtherConnections(newCon);
							System.out.println(other);
							if (!other.isEmpty()) {
								System.out.println("am intrat in if");
								ObjectOutputStream out = new ObjectOutputStream(
										client.getOutputStream());
								MyDevicesItem mdi = new MyDevicesItem();
								mdi.setMyDevices(other);
								out.writeObject(mdi);
								System.out.println("Am trimis");
								for (Connection c : other) {
									ArrayList<Connection> newOther = new ArrayList<Connection>();
									newOther.addAll(other);
									newOther.add(newCon);
									newOther.remove(c);
									MyDevicesItem mdi2 = new MyDevicesItem();
									mdi2.setMyDevices(newOther);
									ObjectOutputStream out2 = new ObjectOutputStream(
											c.getSocket().getOutputStream());
									out2.writeObject(mdi2);
								}
							}
							new Thread(new Runnable() {

								@Override
								public void run() {
									while (true) {
										if (manager.getConnection(
												newCon.getId()).isOn()) {
											ObjectInputStream in2;
											try {
												in2 = new ObjectInputStream(
														client.getInputStream());
												Object obj2 = in2.readObject();
												if (obj2 instanceof String){
													String wordRecv= (String) obj2;
													if(wordRecv.equals("HELP")== true)
													    sendUsersItem(client);
												}

											}
											catch (IOException e) {
												// do nothing
											}
											catch (ClassNotFoundException e) {
												// do nothing
											}
										}
									}
								}
							}).start();
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

	@Override
	public void receiveFromServer(final Socket server,
			final MessageReceiver msgR) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("Start receiving");
						ObjectInputStream in = new ObjectInputStream(
								server.getInputStream());
						Object obj = in.readObject();

						if (obj instanceof String) {
							// do nothing
						}
						else {
							System.out.println("Received an item");
							if (obj instanceof OtherDevicesItem){
								System.out.println("AM PRIMIT DISPOZITIVELE");
							}
							msgR.receive(obj);
						}


					}
					catch (IOException e) {
						// do nothing
						try {
							server.close();
						}
						catch (IOException e1) {
							//do nothing
						}
					}
					catch (ClassNotFoundException e) {
						// do nothing
					}
				}

			}

		}).start();
	}
	
	
	/**
	 * @param client : the client to whom the list of devices will be send
	 */
	public void sendUsersItem(Socket client){
		OtherDevicesItem items = new OtherDevicesItem();
		items.setHisDevices( manager.getAllHisConnections("cucu"));
		ObjectOutputStream out;
		try
		{
			out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(items);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
