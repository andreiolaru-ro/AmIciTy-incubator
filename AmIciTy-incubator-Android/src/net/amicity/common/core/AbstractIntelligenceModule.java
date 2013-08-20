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

import java.util.Set;

/**
 * @author cristian
 * 
 */
public class AbstractIntelligenceModule implements IntelligenceModule {

	@Override
	public void invoke(ContextTypes item) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param t
	 *            -> a set of types from should the Module receive items
	 * @return a set of ContextItem related to the Types from parameter
	 */
	protected static Set<ContextItem> get(Set<ContextTypes> t) {
		return null;
		// TODO ask the contextStore for Items of Types: t
	}

}
