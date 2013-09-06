package net.amicity.common.intelligence;

import java.io.File;
import java.util.ArrayList;

import net.amicity.android.communications.DefaultNetLink;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.context_types.TransferFileItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;


/**
 * @author cristian
 *
 */
public class AndroidFileTransfer implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * The accelerometer action
	 */
	String action;
	/**
	 * An arraylist of all my devices
	 */
	ArrayList<Connection> myDevices;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 */
	public AndroidFileTransfer(ContextCore cc) {
		myCore = cc;
	}
	
	@Override
	public void invoke() {
		AccelerometerItem myAccelerometerItem = ((AccelerometerItem) myCore.getContextStorage().get(
				ContextTypes.ACCELEROMETER));
		MyDevicesItem myDeviceItem = ((MyDevicesItem) myCore.getContextStorage().get(
				ContextTypes.DEVICES_CONTEXT));
		if(myAccelerometerItem != null && myDeviceItem != null) {
			myDevices = myDeviceItem.getMyDevices();
			action = myAccelerometerItem.man;
			for(Connection c : myDevices) {
				System.out.println("incearca cu: " + c.getId().substring(c.getId().indexOf('-') + 1, c.getId().indexOf('-') + 3));
				if(c.getId().substring(c.getId().indexOf('-') + 1, c.getId().indexOf('-') + 3).equalsIgnoreCase("pc")) {
					if(action.equals("stays")) {
						//transfer files to c
						TransferFileItem tfi = new TransferFileItem();
						tfi.addFiles(new File("/workspace"));
						System.out.println("poti incepe transferul");
						DefaultNetLink d = new DefaultNetLink();
						d.send(c, tfi);
					}
				}
			}
		}
		
	}
	
}
