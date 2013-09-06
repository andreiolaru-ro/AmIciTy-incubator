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
package net.amicity.common.core;

import java.util.concurrent.ConcurrentHashMap;

import net.amicity.common.context_types.AbstractItem;

/**
 * The ContextItem Storage picked by Intelligent Modules depending on the
 * notification
 * 
 * @author vlad
 * 
 */
public class ContextStorage extends
		ConcurrentHashMap<ContextTypes, AbstractItem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
