package com.example.infrastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


/**
 * 
 * cointains the methods to determine the location and to add new ones
 * in history
 * @author vlad
 *
 */
interface LocationDetector{
	/**
	 * has the puropose to detect the user's location based on the comparison
	 * between the history's wifis and the ones detected
	 * 
	 * @return : returns the location detected
	 */
	public String getLocation();
}


/**
 * 
 * determines the user's location and manages the history of the networks
 * @author vlad
 *
 */
public class WifiLocationDetection extends Activity implements LocationDetector
{

	/**
	 * cointains the networks detected
	 */
	ArrayList<String> wifiList;
	/**
	 * used for receving the networks detected by the WifiManager
	 */
	WifiReceiver receiverWifi;
	
	/**
	 * used for detecting the Wifi's acces points
	 */
	WifiManager mainWifi;
	
	/**
	 *  shows the location detected
	 */
	TextView mainText;
	
	/**
	 *  shows the common networks detected
	 */
	TextView showText;
	
	/**
	 * simulates the history of user's old locations and its wifis associated
	 */
	TreeMap<String, ArrayList<String>> dataNetLocation;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wifi_location_detection);
		wifiList= new ArrayList<String>();
		
		initHardData();
		mainText = (TextView) findViewById(R.id.mainText);
		showText = (TextView) findViewById(R.id.showText);
	     mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	     receiverWifi = new WifiReceiver(this);
	     mainWifi.setWifiEnabled(true);
	     registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	     mainWifi.startScan();
	     mainText.setText("\\nStarting Scan...\\n"); 
		
		
	}
	
	/**
	 * hardcoding the history locations
	 */
	public void initHardData(){
		dataNetLocation = new TreeMap<String, ArrayList<String>>();
		ArrayList<String> firstLocation = new ArrayList<String>();
		ArrayList<String> secondLocation = new ArrayList<String>();
		ArrayList<String> thirdLocation = new ArrayList<String>();
		ArrayList<String> fourthLocation = new ArrayList<String>();
		ArrayList<String> fifthLocation = new ArrayList<String>();
		
		firstLocation.add("Tempus");
		firstLocation.add("ACS-UPB");
		firstLocation.add("EG208");
		firstLocation.add("rosedu.org");
		firstLocation.add("RTC");
		
		secondLocation.add("Decan");
		secondLocation.add("EC003s");
		secondLocation.add("ZyXEL");
		secondLocation.add("fmc013");
		
		thirdLocation.add("Tempus");
		thirdLocation.add("gericom");
		thirdLocation.add("eb105");
		thirdLocation.add("ACS-UPB");
		thirdLocation.add("ZYXEL");
		
		fourthLocation.add("ED009");
		fourthLocation.add("metnet");
		fourthLocation.add("change");
		fourthLocation.add("ef205");
		fourthLocation.add("ZYXEL");
		fourthLocation.add("ed220");
		fourthLocation.add("E-CAESER");
		fourthLocation.add("uberap1");
		
		
		fifthLocation.add("acasagelu");
		fifthLocation.add("SErioux");
		fifthLocation.add("OurNetork");
		fifthLocation.add("HARR2");
		fifthLocation.add("corina");
		
		
		dataNetLocation.put("CANTI", firstLocation);
		dataNetLocation.put("EC105", secondLocation);
		dataNetLocation.put("cantina", thirdLocation);
		dataNetLocation.put("ED200", fourthLocation);
		dataNetLocation.put("acasa", fifthLocation);
	
	}
	public String getLocation(){

		
		int max = 0, value  = 0, i;
		Set<String> keys = dataNetLocation.keySet();
		String LocationDetected= new String("No Location detected");
		
		
		ArrayList<String> commun = new ArrayList<String>();
		
		for(Iterator<String> itLocation = keys.iterator(); itLocation.hasNext();){
			
			value = 0;
			String location =(String) itLocation.next();
			ArrayList<String> networks = dataNetLocation.get(location);
			commun.clear();
			
			for(Iterator<String> itNet = wifiList.iterator(); itNet.hasNext();){
				String network =(String) itNet.next();
				if(networks.contains(network)== true){
					value ++;	
					commun.add(network);
				}
			}
			if(value > max){
				max  = value;
				LocationDetected = location; 
				showText.setText("");
				for(i=0; i<commun.size(); i++)
					showText.append(commun.get(i)+ "\n");

			}
		}
		
		mainText.setText(LocationDetected);
		return LocationDetected;
				
	}

}


/**
 * receiver of the notifications from the Wifimanager's method scan
 * 
 * @author vlad
 *
 */
class WifiReceiver extends BroadcastReceiver
{
	
	/**
	 * instance of MainActivity in order to populate its members
	 */
	WifiLocationDetection main;

	/**
	 * 
	 * @param wld :instance of the MainActivity in
	 * 	 order to gain acces to its members
	 */
	WifiReceiver(WifiLocationDetection wld)
	{
		main = wld;
	}

	public void onReceive(Context c, Intent intent)
	{
		List <ScanResult> networksDetected=  main.mainWifi.getScanResults();
		main.mainText.setText("am trecut de scanare");
	
		
          for(Iterator<ScanResult> it = networksDetected.iterator(); it.hasNext();){
          	
          	ScanResult networkResult =(ScanResult) it.next();
          	if(main.wifiList.contains(networkResult.SSID) == false)
          		main.wifiList.add(networkResult.SSID);
           }
		
		main.mainText.setText("numele retelelor au fost salvate");
		main.getLocation();
		
	}

}

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


/**
 *  operates the data from the stations in order to initiate communication
 * 
 * @author vlad
 *
 */
class StationsData implements PeerMachinesManager{
	/**
	 * list cotinaing "Station" instances with its data
	 */
	ArrayList <Station> stationsReceived;
	
	/**
	 *  initiating the members of the class
	 */
	StationsData()
	{
		stationsReceived = new ArrayList<Station>();
		
	}
	public void setLocationStations(){
		
		stationsReceived.add(new Station("172.128.12.6", 5000,"1","CANTI"));
		stationsReceived.add(new Station("172.128.12.7", 2333,"2","CANTI"));
		stationsReceived.add(new Station("172.128.12.8", 1555,"3","CANTI"));
		stationsReceived.add(new Station("172.128.12.9", 7000,"4","CANTI"));
		stationsReceived.add(new Station("172.128.12.10", 4000,"5","CANTI"));
		stationsReceived.add(new Station("172.128.12.11", 2323,"6","CANTI"));
		stationsReceived.add(new Station("172.128.12.12", 4444,"7",true,"CANTI"));
	}
	public Station getServerForLocation(String location ){
		for(Iterator<Station> it  = stationsReceived.listIterator(); it.hasNext();){
			Station st = (Station) it.next();
			if(st.isServer== true && st.location.compareTo(location)== 0)
				return st;
			
		}
		return null;
	}
	public String getStationIp(Object Id){
		
		for(Iterator<Station> it  = stationsReceived.listIterator(); it.hasNext();){
			Station st = (Station) it.next();
			if(st.Id == Id)
				return st.Ip;
			
		}
		return null;
		
	}
}
