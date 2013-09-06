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

import java.util.ArrayList;

import net.amicity.android.communications.DefaultNetLink;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.context_types.PerceptionItem;
import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;

/**
 * @author cristian
 * 
 */
public class AndroidPerceptionsTransfer implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * The accelerometer perception
	 */
	String action;
	/**
	 * The sound perception
	 */
	double value;
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
	public AndroidPerceptionsTransfer(ContextCore cc) {
		myCore = cc;
	}

	@Override
	public void invoke() {
		action = ((AccelerometerItem) myCore.getContextStorage().get(
				ContextTypes.ACCELEROMETER)).man;
		value = ((SoundItem) myCore.getContextStorage().get(
				ContextTypes.SOUND_CONTEXT)).getValue();
		MyDevicesItem myDeviceItem = ((MyDevicesItem) myCore
				.getContextStorage().get(ContextTypes.DEVICES_CONTEXT));
		if (myDeviceItem != null) {
			myDevices = myDeviceItem.getMyDevices();
			for (Connection c : myDevices) {
				System.out.println("incearcaa cu: "
						+ c.getId().substring(c.getId().indexOf('-') + 1,
								c.getId().indexOf('-') + 3));
				if (c.getId()
						.substring(c.getId().indexOf('-') + 1,
								c.getId().indexOf('-') + 3)
						.equalsIgnoreCase("pc")) {
					// send perceptions
					System.out.println("Send Perceptions");
					DefaultNetLink d = new DefaultNetLink();
					PerceptionItem pi = new PerceptionItem(
							myCore.getUsername(), value, action);
					d.send(c, pi);
				}
			}
		}
	}

}
