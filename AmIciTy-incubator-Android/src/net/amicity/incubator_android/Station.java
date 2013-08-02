package net.amicity.incubator_android;



/**
 * class which describes the characters of a station used in communication
 * 
 * @author vlad
 *
 */
class Station{
	/**
	 *  the Station's Ip used in communication 
	 */
	public String Ip;
	/**
	 *  the Station's Port used in communication
	 */
	public int Port;
	/**
	 * the Station's Id used in communication
	 */
	public String Id;
	/**
	 *  to know whether the Station is the Server
	 */
	public boolean isServer;
	/**
	 * the Station's location used in communication
	 */
	public String location;
	
	
	/**
	 * @param IpGiven : the Ip given at the creation of Station's instance 
	 * @param PortGiven :  the Port given at the creation of Station's instance 
	 * @param IdGiven :  the Id given at the creation of Station's instance 
	 * @param isServer : 
	 * @param locationGiven :  the location given at the creation of Station's instance 
	 */
	Station(String IpGiven, int PortGiven, String IdGiven,boolean isServer, String locationGiven  ){
		
		Ip = IpGiven;
		Port = PortGiven;
		Id = IdGiven;
		this.isServer = isServer;
		location = locationGiven;
		
	}
	
	/**
	 * the constructor in case the station is not server
	 * @param IpGiven : 
	 * @param PortGiven : 
	 * @param IdGiven :
	 * @param locationGiven : 
	 */
	Station(String IpGiven, int PortGiven, String IdGiven, String locationGiven  ){
		this(IpGiven, PortGiven, IdGiven,false, locationGiven);
	}
	
}