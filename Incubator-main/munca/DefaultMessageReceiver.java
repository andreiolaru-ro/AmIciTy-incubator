<<<<<<< HEAD
nullnull/*******************************************************************************
=======
nullnullnull/*******************************************************************************
>>>>>>> refs/remotes/origin/development/architecture
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
package net.amicity.pc.communications;

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
