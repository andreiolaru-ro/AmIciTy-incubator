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
package net.amicity.common.core.context;

import java.util.concurrent.LinkedBlockingQueue;
import net.amicity.common.context_types.AbstractItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.Message;
import net.amicity.common.core.Notification;

/**
 * @author ''Azgabast'', vlad, cristian The top-level module of the application.
 */
public class ContextCore {

	/**
	 * 
	 */
	 private final ContextStorage contextStorage;

	/**
	 * a synchronized queue used to add new ContextItems by Sensor modules or
	 * Intelligent modules and to extract added ContextItems to be prepared for
	 * Notification
	 */
	 private final LinkedBlockingQueue<AbstractItem> contextUpdates;
	/**
	 * a synch queue to notify intelligent modules that they may be interested
	 * in some (processed)ContextItems added in ContextStorage
	 */
	 private final LinkedBlockingQueue<Notification> notificationQueue;

	/**
	 * q of messages sent to other devices/ for connection with the server
	 */
	 LinkedBlockingQueue<Message> sendQueue;

	/**
	 * initialising the class's queues
	 */
	public ContextCore() {
		this.contextUpdates = new LinkedBlockingQueue<AbstractItem>();
		this.notificationQueue = new LinkedBlockingQueue<Notification>();
		this.contextStorage = new ContextStorage();
		System.out.println(" ContextCore constructor ");

	}

	/**
	 * @param newItem
	 *            : ContextItem to be added by the IntelligentModule or
	 *            SensorModule to be analised and used by interested entities
	 */
	public void postContextUpdate(AbstractItem newItem) {
		getContextUpdates().add(newItem);
	}

	/**
	 * @return the Contextitem by ContextManager to be processed, creating an
	 *         ContextStore's component and to notify the interested entities
	 */
	public AbstractItem getContextUpdate() {
		return getContextUpdates().remove();
	}

	/**
	 * @param newNotificaition
	 *            : the Notification added by ContextManager after adding a
	 *            processed ContextItem to ContextStore
	 */
	public void postNotification(Notification newNotificaition) {
		getNotificationQueue().add(newNotificaition);
	}

	/**
	 * @return : the Notification which mentions the next module to be notified
	 */
	public Notification getNotification() {
		return getNotificationQueue().remove();
	}

	/**
	 * @return The contextUpdates queue.
	 */
	public LinkedBlockingQueue<AbstractItem> getContextUpdates()
	{
		return contextUpdates;
	}


	/**
	 * @return The current context storage.
	 */
	public ContextStorage getContextStorage()
	{
		return contextStorage;
	}

	/**
	 * @return The notification queue
	 */
	public LinkedBlockingQueue<Notification> getNotificationQueue()
	{
		return notificationQueue;
	}



}
