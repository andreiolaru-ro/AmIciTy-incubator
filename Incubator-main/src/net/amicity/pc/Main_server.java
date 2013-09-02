package net.amicity.pc;

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
					try {
						if (i.getSocket().getInputStream().read() == -1) {
							i.getSocket().close();
							i.setStateOff();
						}
						else {
							System.out.println("Connected with " + i.getId());
						}
					}
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}, 0, 10000);

	}
}
