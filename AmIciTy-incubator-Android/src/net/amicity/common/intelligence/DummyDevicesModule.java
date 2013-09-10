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

import net.amicity.android.MainActivity;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.MyDevicesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;

/**
 * A class for showing all my devices
 * 
 * @author cristian
 * 
 */
public class DummyDevicesModule implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * An arraylist of all my devices
	 */
	ArrayList<Connection> myDevices;

	/**
	 * The main activity instance, for updating its texts.
	 */
	MainActivity ma;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 * @param ma
	 *            the main activity which will be updated.
	 */
	public DummyDevicesModule(ContextCore cc, MainActivity ma) {
		myCore = cc;
		this.ma = ma;
	}

	@Override
	public void invoke() {
		myDevices = ((MyDevicesItem) myCore.getContextStorage().get(
				ContextTypes.DEVICES_CONTEXT)).getMyDevices();
		final String devices = "";
		for (final Connection c : myDevices) {
			System.out.println(c.getId());
			devices.concat(c.getId() + "\n");
			// ma.getChanges().setText("");

		}
		ma.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				ma.getDevices().setText(devices);

			}
		});
		// System.out.println("This are my devices: " + myDevices);
	}

}
