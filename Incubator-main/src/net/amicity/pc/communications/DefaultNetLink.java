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

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
import net.amicity.common.communications.ConnectionManager;
import net.amicity.common.communications.MessageItem;
import net.amicity.common.communications.MessageReceiver;
import net.amicity.common.communications.NetLink;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.FileAnalizerModule;
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
			out.flush();
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
								out.flush();
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
									out2.flush();
								}
							}
							new Thread(new Runnable() {

								@Override
								public void run() {
									while (true) {
										if(manager.getConnection(newCon.getId()).isOn()) {
											ObjectInputStream in2;
											try {
												in2 = new ObjectInputStream(client
														.getInputStream());
												Object obj2 = in2.readObject();
												if (obj2 instanceof MessageItem){
													new HelpMessage(obj2, client);
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
						}
						if( obj instanceof MessageItem){
							System.out.println("cineva vrea sa te ajute");
						}
					/*	else {
							System.out.println("Received an item");
							msgR.receive(obj);
						}*/
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



class HelpMessage extends JFrame implements ActionListener{
	
	/**
	 * don;t know why it is necessary
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * the frame's width
	 */
	int width;
	/**
	 * the frame's height
	 */
	int height;
	/**
	 * if the user asks for help
	 */
	JButton butonYes;
	/**
	 * if the user doesn't need help
	 */
	JButton butonNo;
	
	/**
	 * the filename for which help is required
	 */
	String myFilename;
	
	
	/**
	 * the user which needs help
	 */
	String myUser;
	
	
	Socket myClient;
	
	
	/**
	 * initialising the window
	 * @param filename : the file's name for which help is required
	 * @param user : the nema of the user whoch called for help
	 */
	HelpMessage(Object objectReceived, Socket client ){
		MessageItem myItem = (MessageItem) objectReceived;
		myFilename = myItem.myFilename;
		myUser = myItem.myUser;
		
		myClient = client;
		
		width = 300;
		height = 200;
		createWindow();
		addWrite();
		
	}
	
	
	/**
	 * setting the properties
	 */
	public void createWindow(){

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle winSize = 
		GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
		this.setTitle("Need Help?");
		this.setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(dimension.width - width ,  winSize.height  - height);
		this.setVisible(true);
		this.toFront();
		setLayout(null);
	}
	
	/**
	 * adding the components
	 */
	public void addWrite(){
		
		JLabel eticheta = new JLabel( myUser +   " needs help for: " + myFilename);
		Dimension dim = eticheta.getPreferredSize();
		width = dim.width + 20;
		eticheta.setBounds((290 - dim.width)/2  ,30 , dim.width, dim.height);
		add(eticheta);
		

		butonYes = new JButton("Yes, with pleasure");
		dim = butonYes.getPreferredSize();
		butonYes.setBounds(10  ,100 , dim.width, dim.height);
		add(butonYes); 
		butonYes.addActionListener(this);
		
		butonNo = new JButton("No, too busy");
		Dimension dim2 = butonYes.getPreferredSize();
		butonNo.setBounds(20 + dim.width  ,100 , dim2.width, dim2.height);
		add(butonNo); 
		butonNo.addActionListener(this);
		
		
		
		
		this.setSize(width -1, height -1);
		this.setSize(width, height);
		
	
	} 
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
	     
		String command = e.getActionCommand();
		
		
		if(command.equals("Yes, with pleasure") == true){
			ObjectOutputStream out;
			try
			{
				out = new ObjectOutputStream(myClient.getOutputStream());
				out.writeObject(new MessageItem(ContextCore.getUsername(), myFilename));
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			dispose();
		}
		if(command.equals("No, too busy") == true){
			dispose();
		}
		

		
	}
	
}



