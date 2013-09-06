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
package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.communications.Connection;
import net.amicity.common.core.ContextTypes;

/**
 * An item that says on what devices i'm connected
 * 
 * @author cristian
 * 
 */
public class MyDevicesItem extends AbstractItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	ArrayList<Connection> myDevices;

	/**
	 * The Constructor that initiates the ArrayList
	 */
	public MyDevicesItem() {
		myDevices = new ArrayList<Connection>();
		type = ContextTypes.DEVICES_CONTEXT;
	}

	/**
	 * @return the array list of my devices
	 */
	public ArrayList<Connection> getMyDevices() {
		return this.myDevices;
	}

	/**
	 * @param newDevices
	 *            new Devices to add for me;
	 */
	public void setMyDevices(ArrayList<Connection> newDevices) {
		myDevices.addAll(newDevices);
	}

}
