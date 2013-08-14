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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import net.amicity.common.core.context.ContextCore;

/**
 * @author ''Azgabast'', vlad, cristian The class which takes updates from the
 *         ContextUpdates queue and adds them to the sendQueue and/or post
 *         notifications in the notificationsQueue.
 */
public class ContextManager extends Thread {

	/**
	 * instance to access the ContextCore's synchronized queues : Update and to
	 * manage them by using core's methods
	 */
	ContextCore myCore;
	/**
	 * A map representation of type of a context and a set of Intelligence
	 * Modules related to context
	 */
	HashMap<ContextTypes, ArrayList<IntelligenceModule>> contextIntelModule;
	/**
	 * A set of devices associated to the same user
	 */
	HashSet<?> devices;

	/**
	 * @param coreReceived
	 *            : instance of singleton ContextCore
	 */
	public  ContextManager(ContextCore coreReceived) {
		myCore = coreReceived;
		
	}
	
	
	@Override
	public void run(){
		while (true) {
			if(myCore.contextUpdates.isEmpty() == false){
				ContextItem itemSelected = myCore.getContextUpdate();
				System.out.println("a intrat aici");
			}
	
		}
	}

}
