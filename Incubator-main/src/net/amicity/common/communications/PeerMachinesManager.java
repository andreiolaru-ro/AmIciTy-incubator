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
package net.amicity.common.communications;

/**
 * contains the methods used to operate the data of the stations from the
 * location where the user is situated
 * 
 * @author vlad
 * 
 */
interface PeerMachinesManager {

	/**
	 * creating the list of the data from the stations fron the room
	 */
	public void addLocationStations(String Ip, boolean server, String location);

	/**
	 * @param location
	 *            : where the user is situated based on the wifi comparison
	 * @return the Server Station from the location received
	 */
	public String getServerForLocation(String location);

	/**
	 * @param id
	 *            : used to identify the station
	 * @return the Station Ip used for connection
	 */
	public String getStationIp(Object id);
}
