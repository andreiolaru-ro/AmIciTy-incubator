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

import net.amicity.common.communications.MessageItem;
import net.amicity.common.context_types.AbstractItem;
import net.amicity.common.core.context.ContextCore;

/**
 * @author ''Azgabast'', vlad, cristian The class which takes updates from the
 *         ContextUpdates queue and adds them to the sendQueue and/or post
 *         notifications in the notificationsQueue.
 */
public class ContextManager extends Thread {

	/**
	 * instance to acces the ContextCore's synchr queues : Update and to manage
	 * them by using core's methods
	 */
	ContextCore myCore;

	/**
	 * a map that links a ContextType to intelligenceModules that has to be
	 * invoked
	 */
	HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm;

	/**
	 * @param coreReceived
	 *            : instance of singleton ContextCore
	 * @param hm
	 *            : a map that links a ContextType to intelligenceModules that
	 *            has to be invoked
	 */
	public ContextManager(ContextCore coreReceived,
			HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm) {
		myCore = coreReceived;
		this.hm = hm;
	}

	@Override
	public void run() {
		while (true) {
			if (ContextCore.getContextUpdates().isEmpty() == false) {
				AbstractItem item = ContextCore.getContextUpdate();
				if( item instanceof MessageItem){
					System.out.println("S-A PRIMIT in CONTEXTMANAGER UN MessageItem");
				}
				
				if (hm.containsKey(item.getType())) {
					Notification newNot = new Notification(hm.get(item
							.getType()));
					myCore.getContextStorage().put(item.getType(), item);
					myCore.postNotification(newNot);
				}
			}
		}
	}

}
