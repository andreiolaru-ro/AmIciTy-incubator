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
package net.amicity.common.intelligence;

import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;

/**
 * 
 * 
 * @author cristian
 * 
 */
public class DummyAccelerometerTest implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * A string that indicates if the people walks or stays
	 */
	String action;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 */
	public DummyAccelerometerTest(ContextCore cc) {
		myCore = cc;
	}

	@Override
	public void invoke() {

		action = ((AccelerometerItem) myCore.getContextStorage().get(
				ContextTypes.ACCELEROMETER)).man;
		System.out.println("The (wo)man is: " + action);

	}

}
