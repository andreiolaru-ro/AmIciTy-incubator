package net.amicity.common.intelligence;

import java.io.File;
import java.util.ArrayList;

import net.amicity.pc.communications.DefaultNetLink;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.context_types.PCAccelerometerItem;
import net.amicity.common.context_types.TransferFileItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;


/**
 * A class for sending back files to phone after suspect leaves
 * @author cristian
 *
 */
public class PCFileTransfer implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * a boolean that says if i already sent the files
	 */
	boolean sent;
	/**
	 * The accelerometer action
	 */
	String action;
	/**
	 * the action user
	 */
	String user;
	/**
	 * An arraylist of all my devices
	 */
	ArrayList<Connection> myDevices;
	
	/**
	 * @param cc the context core
	 */
	public PCFileTransfer(ContextCore cc) {
		myCore = cc;
		sent = false;
	}

	@Override
	public void invoke() {
		PCAccelerometerItem pai = ((PCAccelerometerItem) myCore
				.getContextStorage().get(ContextTypes.ACCELEROMETER));
		action = pai.man;
		user = pai.user;
		myDevices = ((MyDevicesItem) myCore
				.getContextStorage().get(ContextTypes.DEVICES_CONTEXT)).getMyDevices();
		System.out.println("PCFileTransfer invoked");
		if( !sent ) {
			if(action.equals("walking")) {
				for(Connection c : myDevices) {
					if(c.getId().equals(user)) {
						System.out.println("Started the file transfer");
						TransferFileItem tfi = new TransferFileItem();
						tfi.addFiles(new File("./munca"));
						System.out.println("poti incepe transferul");
						DefaultNetLink d = new DefaultNetLink();
						d.send(c, tfi);
					}
				}
			}
		}
	}

}