package net.amicity.common.communications;


import java.util.ArrayList;
import java.util.Iterator;




/**
 *  operates the data from the stations in order to initiate communication
 * 
 * @author vlad
 *
 */
class SimplePeerMachinesManager implements PeerMachinesManager{
	/**
	 * list cotinaing "Station" instances with its data
	 */
	ArrayList <Station> stationsReceived;
	
	/**
	 *  initiating the members of the class
	 */
	 SimplePeerMachinesManager()
	{
		stationsReceived = new ArrayList<Station>();
		setLocationStations();
		
	}
	@Override
	public void setLocationStations(){
		
		stationsReceived.add(new Station("172.128.12.6", 5000,"1","CANTI"));
		stationsReceived.add(new Station("172.128.12.7", 2333,"2","CANTI"));
		stationsReceived.add(new Station("172.128.12.8", 1555,"3","CANTI"));
		stationsReceived.add(new Station("172.128.12.9", 7000,"4","CANTI"));
		stationsReceived.add(new Station("172.128.12.10", 4000,"5","CANTI"));
		stationsReceived.add(new Station("192.168.0.195", 2323,"6",true,"CANTI"));
		stationsReceived.add(new Station("192.168.0.128", 4444,"7",true,"acasa"));
	}
	@Override
	public Station getServerForLocation(String location ){
		
		
		for(Iterator<Station> it  = stationsReceived.listIterator(); it.hasNext();){
			Station st =  it.next();
			if(st.isServer== true && st.location.compareTo(location)== 0){
				
				return st;
			}
			
		}
		return null; 
	}
	@Override
	public String getStationIp(Object Id){
		
		for(Iterator<Station> it  = stationsReceived.listIterator(); it.hasNext();){
			Station st = it.next();
			if(st.Id == Id)
				return st.Ip;
			
		}
		return null;
		
	}
}
