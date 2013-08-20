/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru , Vlad Herescu, Cristian Radu Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.common.core;

import java.util.ArrayList;

/**
 * Notification added by Contextmanager and pulled from NotificationManager in
 * order to notify the Intelligent Modules to get a ContextItem
 * 
 * @author vlad
 * 
 */
public class Notification {
	/**
	 *  the type of notification 
	 */
	//IntelligentTypes myNotified;
	ContextTypes type;
	/**
	 * The list of modules to be notified.
	 */
	ArrayList<IntelligentTypes> intelModules;
	/**
	 * @param toBeNotified : the value given to type
	 * @param modules : the modules to be notified
	 */
	public Notification(ContextTypes toBeNotified, ArrayList<IntelligentTypes> modules){
		type = toBeNotified;
		intelModules = new ArrayList<IntelligentTypes>(modules);
		
	}
}
