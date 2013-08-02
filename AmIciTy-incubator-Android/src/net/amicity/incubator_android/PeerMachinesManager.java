package net.amicity.incubator_android;


/**
 * contains the methods used to operate the data of the stations from the 
 * location where the user is situated
 * 
 * @author vlad
 *
 */
interface PeerMachinesManager{
	/**
	 *  creating the list of the data from the stations fron the room
	 */
	public void setLocationStations();
	/**
	 * @param location : where the user is situated based on the wifi comparison
	 * @return the Server Station from the location received
	 */
	public Station getServerForLocation(String location);
	/**
	 * @param id : used to identify the station
	 * @return the Station Ip used for connection
	 */
	public String getStationIp(Object id);
}