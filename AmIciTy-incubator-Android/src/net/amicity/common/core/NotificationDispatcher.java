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
import net.amicity.common.intelligence.LocationModule;
import net.amicity.common.intelligence.SoundIntel;

/**
 * @author ''Azgabast'', vlad, cristian The class which takes notifications from
 *         the notificationQueue and calls for the IntelligenceModules which are
 *         interested, in a random order.
 */
public class NotificationDispatcher extends Thread {

	/**
	 * instance used for accesing the queues used
	 */
	ContextCore myCore;
	/**
	 * a LocationModule instance for calling the invoke method
	 */
	LocationModule locationContact;
	/**
	 * a SoundIntel instance for calling the invoke method
	 */
	SoundIntel soundIntel;

	/**
	 * @param core
	 *            : received for accesing the queues
	 */
	public NotificationDispatcher(ContextCore core) {
		myCore = core;
		locationContact = new LocationModule();
	}

	@Override
	public void run() {
		while (true) {
			if (myCore.notificationQueue.isEmpty() == false) {
				Notification notExtract = myCore.getNotification();
				for (IntelligentTypes i : notExtract.intelModules) {
					if (i == IntelligentTypes.LOCATION_INTELLIGENT)
						locationContact.invoke();
					if (i == IntelligentTypes.SOUND_INTELLIGENT)
						soundIntel.invoke();
				}

			}
		}
	}
}
