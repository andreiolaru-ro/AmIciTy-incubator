package net.amicity.common.communications;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;




/**
 *  operates the data from the stations in order to initiate communication
 * 
 * @author vlad
 *
 */
public class SimplePeerMachinesManager implements PeerMachinesManager{
	/**
	 * list cotinaing "Station" instances with its data
	 */
	ArrayList <Station> stationsReceived;
	
	/**
	 * a map Location-IP
	 */
	TreeMap<String, String> serversIP ;
	
	/**
	 *  initiating the members of the class
	 */
	 public SimplePeerMachinesManager()
	{
		stationsReceived = new ArrayList<Station>();
		serversIP  = new TreeMap<String,String>();
		addServersIP();
		//setLocationStations();
		
	}
	 public void addServersIP(){
		 serversIP.put("CANTI", "172.16.15.119");
		 serversIP.put("acasa", "192.168.0.128"); 
	 }
	 
	@Override
	public void addLocationStations(String Ip, boolean server, String location){
		
		stationsReceived.add(new Station(Ip, 4501, "1", true,location ));
		
	/*	stationsReceived.add(new Station("172.128.12.6", 5000,"1","CANTI"));
		stationsReceived.add(new Station("172.128.12.7", 2333,"2","CANTI"));
		stationsReceived.add(new Station("172.128.12.8", 1555,"3","CANTI"));
		stationsReceived.add(new Station("172.128.12.9", 7000,"4","CANTI"));
		stationsReceived.add(new Station("172.128.12.10", 4000,"5","CANTI"));
		stationsReceived.add(new Station("192.168.0.195", 2323,"6",true,"CANTI"));
		stationsReceived.add(new Station("192.168.0.128", 4444,"7",true,"acasa"));*/
	}
	@Override
	public String getServerForLocation(String location ){
		
		
		for(Map.Entry<String, String> serverIp : serversIP.entrySet()){
			if(serverIp.getKey().equals(location) == true){
				addLocationStations("192.168.0.195", true, location);
				return serverIp.getValue();
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
