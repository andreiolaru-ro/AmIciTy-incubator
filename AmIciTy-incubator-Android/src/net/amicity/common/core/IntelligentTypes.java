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
 * used to identify the notification's receiver
 * 
 * @author vlad
 *
 */
public enum IntelligentTypes
{
	/**
	 * the receiver of the notification is LocationModule
	 */
	LOCATION_INTELLIGENT,
	/**
	 * the receiver of the notification is MonitorModule
	 */
	MONITOR_INTELLIGENT,
	/**
	 * the receicer of the notification is SoundModule
	 */
	SOUND_INTELLIGENT;
}