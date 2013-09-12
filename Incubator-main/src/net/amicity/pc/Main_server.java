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
package net.amicity.pc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.pc.communications.DefaultNetLink;

/**
 * @author cristian
 * 
 */
public class Main_server {
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DefaultNetLink dnf = new DefaultNetLink();
		dnf.serverReceival(4500);
		final ConnMgr manager = (ConnMgr) dnf.getConnectionManager();

		Timer t = new Timer();
		t.scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {

				for (Connection i : manager.getConnections()) {
					if (i.isOn()) {
						try {
							ObjectOutputStream out = new ObjectOutputStream(i
									.getSocket().getOutputStream());
							out.writeObject("Hello");
							System.out.println("Connection with " + i.getId()
									+ "is available");
						}
						catch (IOException e) {
							System.out.println("connection with " + i.getId()
									+ " is closed");
							try {
								i.getSocket().close();
								ArrayList<Connection> other = manager
										.getOtherConnections(i);
								for (Connection c : other) {
									ArrayList<Connection> newOther = new ArrayList<Connection>();
									newOther.addAll(other);
									newOther.remove(c);
									MyDevicesItem mdi2 = new MyDevicesItem();
									mdi2.setMyDevices(newOther);
									ObjectOutputStream out2 = new ObjectOutputStream(
											c.getSocket().getOutputStream());
									out2.writeObject(mdi2);
									System.out.println("Am trimis iesirea unui tip");
								}
							}
							catch (IOException e1) {
								e1.printStackTrace();
							}
							i.setStateOff();
						}
					}
				}
			}
		}, 0, 10000);

	}
}
