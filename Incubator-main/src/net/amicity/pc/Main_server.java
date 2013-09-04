package net.amicity.pc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;

import net.amicity.common.communications.ConnMgr;
import net.amicity.common.communications.Connection;
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
