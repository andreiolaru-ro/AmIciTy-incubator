/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Grigoras.
 * 
 * This file is part of NetLink-PC.
 * 
 * NetLink-PC is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * NetLink-PC is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with NetLink-PC.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.android.communications;

import net.amicity.common.communications.MessageReceiver;
import net.amicity.common.context_types.AbstractItem;
import net.amicity.common.core.context.ContextCore;

/**
 * @author cristian
 * 
 */
public class DefaultMessageReceiver implements MessageReceiver {

	@Override
	public void receive(Object obj) {
		AbstractItem item = (AbstractItem) obj;
		System.out.println("Server put context updates");
		ContextCore.postContextUpdate(item);
	}

}
