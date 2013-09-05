/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Grigoras.
 * 
 * This file is part of NetLink-PC.
 * 
 * NetLink-PC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * NetLink-PC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with NetLink-PC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.android.communications;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

import net.amicity.android.MainActivity;
import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
import net.amicity.common.communications.ConnectionManager;
import net.amicity.common.communications.MessageReceiver;
import net.amicity.common.communications.NetLink;
import net.amicity.common.core.context.ContextCore;
import net.amicity.android.sensors.ServerModule;
import android.content.Intent;
import android.util.Log;

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
	 * The main activity
	 */
	MainActivity activity;
	
	/**
	 * @param act 
	 * 
	 */
	public DefaultNetLink(MainActivity act) {
		manager = new ConnMgr();
		this.activity = act;
	}
	
	/**
	 * the emptyconstructor
	 */
	public DefaultNetLink() {
		manager = new ConnMgr();
	}
	
	@Override
	public void send(final Connection c, final Object o) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Socket client;
					ObjectOutputStream out;
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

		}).start();

	}

	@Override
	public void initializeReceival(int port, final MessageReceiver msgR) {
		final ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
			final Test act = (Test) msgR;

			System.out.println("Server started on port: " + port);

			new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
							Socket client = serverSocket.accept();
							ObjectInputStream in = new ObjectInputStream(
									client.getInputStream());
							final Object obj = in.readObject();

							act.runOnUiThread(new Runnable() {

								@Override
								public void run() {
									msgR.receive(obj);
									// act.tx.setText((String) obj);
								}
							});

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
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		}
		catch (SocketException ex) {
			Log.e("ServerActivity", ex.toString());
		}
		return null;
	}

	@Override
	public void createConnection(final Connection server, final Connection me) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				Socket client;
				ObjectOutputStream out;

				try {
					client = new Socket(server.getIp(), server.getPort());
					ContextCore.setServerSocket(client);
					
					System.out.println("Starting the server service");
					
					// Start the server
					Intent intent = new Intent(activity, ServerModule.class);
					activity.startService(intent);
					
					out = new ObjectOutputStream(client.getOutputStream());
					out.writeObject(me);
					out.flush();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}).start();
		
	}

	@Override
	public void serverReceival(int port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void receiveFromServer(final Socket server, final MessageReceiver msgR) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						System.out.println("Start receiving");
						ObjectInputStream in = new ObjectInputStream(
								server.getInputStream());
						Object obj = in.readObject();
						if(obj instanceof String) {
							//do nothing
						}
						else
							msgR.receive(obj);
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

}
