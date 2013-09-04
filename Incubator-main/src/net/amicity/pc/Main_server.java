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
							ObjectOutputStream out = new ObjectOutputStream(i.getSocket().getOutputStream());
							out.writeObject("Hello");
							System.out.println("Connection with " + i.getId()
									+ "is available");
						}
						catch (IOException e) {
							System.out.println("connection with " + i.getId()
									+ " is closed");
							try {
								//send to other devices closed connection
								ArrayList<Connection> other = manager.getOtherConnections(i);
								for(Connection c : other) {
									ArrayList<Connection> newOther = new ArrayList<Connection>();
									newOther.addAll(other);
									newOther.remove(c);
									MyDevicesItem mdi2 = new MyDevicesItem();
									mdi2.setMyDevices(other);
									ObjectOutputStream out2 = new ObjectOutputStream(c.getSocket().getOutputStream());
									out2.writeObject(mdi2);
									out2.flush();
								}
								i.getSocket().close();
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
