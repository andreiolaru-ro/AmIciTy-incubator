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
 * A class for sending perceptions
 * 
 * @author cristian
 * 
 */
public class PerceptionItem extends AbstractItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The accelerometer perception
	 */
	String action;

	/**
	 * The sound perception
	 */
	double value;

	/**
	 * The user that send perceptions
	 */
	String user;

	/**
	 * @param user
	 *            the user that sends
	 * @param value
	 *            the value of sound perception
	 * @param action
	 *            the action from accelerometer perception
	 */
	public PerceptionItem(String user, double value, String action) {
		this.user = user;
		this.value = value;
		this.action = action;
		this.type = ContextTypes.PERCEPTION_CONTEXT;
	}

}
