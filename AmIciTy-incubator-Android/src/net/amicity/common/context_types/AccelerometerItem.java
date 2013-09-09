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

import net.amicity.common.core.ContextTypes;

/**
 * This class represents a accelerometer item, generated by the accelerometer
 * module. It has a float which retains the total number of moves. Also changes
 * type from walking to stay
 * 
 * @author cristian
 * 
 */
public class AccelerometerItem extends AbstractItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the total number of moves (on x, y and z) in 5 minutes
	 */
	float total;
	/**
	 * String that says if the man walks or stays
	 */
	public String man;
	/**
	 * The accelerometer limit between stays and walks
	 */
	private float noise;

	/**
	 * public constructor for accelerometerItem, initiates the string
	 */
	public AccelerometerItem() {
		man = new String();
		noise = 1.2f;
	}

	/**
	 * @param nr
	 *            -> number of moves recorded by accelerometer
	 */
	public void changeType(float nr) {
		this.total = nr;
		type = ContextTypes.ACCELEROMETER;
		if (total > noise)
			man = "walking";
		else
			man = "stays";
	}
}
