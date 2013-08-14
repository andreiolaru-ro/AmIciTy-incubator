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
package net.amicity.common.core;

import net.amicity.common.core.context.ContextCore;

/**
 * @author ''Azgabast'', vlad, cristian
 * This interface is implemented by all classes which offer information fetching
 * from outside the application.
 */
public interface SensorModule
{

	/**
	 * @param core : The contextCore which connects to the module.
	 */
	public void connect(ContextCore core);
	
	/**
	 * method to add the data to core's queues
	 */
	public void addDataDetected();
	
	/**
	 * obtain data using sensors
	 */
	public void obtainData();
	
	
}
