/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru , Vlad Herescu, Cristian Radu Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;


/**
 * result of wireless sensors
 * @author vlad
 */
public class WirelessItem implements ContextItem{
	/**
	 * enum used to know the ContextItem's type by ContextManager
	 */
	ContextTypes type;
	/**
	 * arraylist of wireless netowrks detected
	 */
	public ArrayList <String> wifiDetected;
	/**
	 * initialising the members
	 */
	public WirelessItem(){
		type = ContextTypes.WIRELESS_CONTEXT; 
		wifiDetected = new ArrayList<String>();
	}
	
}
