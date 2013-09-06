/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.common.context_types;

import java.io.Serializable;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;

/**
 * An abstract item class containing ContextType
 * 
 * @author cristian
 * 
 */
public abstract class AbstractItem implements ContextItem, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the ContextType
	 */
	ContextTypes type;

	@Override
	public void setType(ContextTypes type) {
		this.type = type;
	}

	@Override
	public ContextTypes getType() {
		return this.type;
	}

}
