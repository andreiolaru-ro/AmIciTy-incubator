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
		AccelerometerItem myAccelerometerItem = ((AccelerometerItem) myCore
				.getContextStorage().get(ContextTypes.ACCELEROMETER));
		MyDevicesItem myDeviceItem = ((MyDevicesItem) myCore
				.getContextStorage().get(ContextTypes.DEVICES_CONTEXT));
		if (myAccelerometerItem != null && myDeviceItem != null) {
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
