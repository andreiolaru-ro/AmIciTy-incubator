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
 * class which describes the characters of a station used in communication
 * 
 * @author vlad
 * 
 */
class Station {

	/**
	 * the Station's Ip used in communication
	 */
	public String Ip;
	/**
	 * the Station's Port used in communication
	 */
	public int Port;
	/**
	 * the Station's Id used in communication
	 */
	public String Id;
	/**
	 * to know whether the Station is the Server
	 */
	public boolean isServer;
	/**
	 * the Station's location used in communication
	 */
	public String location;

	/**
	 * @param IpGiven
	 *            : the Ip given at the creation of Station's instance
	 * @param PortGiven
	 *            : the Port given at the creation of Station's instance
	 * @param IdGiven
	 *            : the Id given at the creation of Station's instance
	 * @param isServer
	 *            :
	 * @param locationGiven
	 *            : the location given at the creation of Station's instance
	 */
	Station(String IpGiven, int PortGiven, String IdGiven, boolean isServer,
			String locationGiven) {

		Ip = IpGiven;
		Port = PortGiven;
		Id = IdGiven;
		this.isServer = isServer;
		location = locationGiven;

	}

	/**
	 * the constructor in case the station is not server
	 * 
	 * @param IpGiven
	 *            :
	 * @param PortGiven
	 *            :
	 * @param IdGiven
	 *            :
	 * @param locationGiven
	 *            :
	 */
	Station(String IpGiven, int PortGiven, String IdGiven, String locationGiven) {
		this(IpGiven, PortGiven, IdGiven, false, locationGiven);
	}

}
