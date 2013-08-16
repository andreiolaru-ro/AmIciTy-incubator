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

/**
 * @author ''Azgabast'', vlad, cristian This class contains the context types
 *         that are used.
 * 
 */
public enum ContextTypes {
	/**
	 * Context type for the LocationModule
	 */
	LOCATION_CONTEXT, /**
	 * Context type for the SoundModule
	 */
	SOUND_CONTEXT,
	/**
	 * Context type for AccelerometerModule if the suspect walks
	 */
	ACCELEROMETER_WALKS,
	/**
	 * Context type for AccelerometerModule if the suspect stays
	 */
	ACCELEROMETER_STAYS;

}
