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
 *	The class which takes updates from the ContextUpdates queue and adds them
 * to the sendQueue and/or post notifications in the notificationsQueue.
 */
public class ContextManager
{
	/**
	 * instance to acces the ContextCore's synchr queues : Update
	 * and to manage them by using core's methods
	 */
	ContextCore myCore;	
	/**
	 * @param coreReceived : instance of singleton ContextCore
	 */
	ContextManager(ContextCore coreReceived){
		myCore = coreReceived;
		while(true){
			try
			{
				wait();
				ContextItem itemSelected = myCore.getContextUpdate();
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//
		}
	}
	
	
	
	
}
