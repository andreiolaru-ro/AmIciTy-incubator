package net.amicity.common.intelligence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.IntelligenceModule;


/**
 * smart module using sophisticated algoritms to detect the user's location 
 * based on wireless networks received 
 * @author vlad
 */
public class LocationModule implements IntelligenceModule
{
	/**
	 * arraylist of wireless netowrks detected
	 */
	ArrayList <String> wifiDetected;
	
	/**
	 * history of networks and locations 
	 */
	TreeMap<String, ArrayList<String>> dataNetLocation;
	
	
	/**
	 * constructor of the class
	 * initialize its members
	 */
	public LocationModule(){
		
		dataNetLocation = new TreeMap<String, ArrayList<String>>();
		initHardData();
	}

	@Override
	public void invoke(ContextItem item)
	{
		wifiDetected = (ArrayList <String>) item;
		getLocation();
		
	}
	
	
	/**
	 * initialise the history locations defined by netowrks
	 */
	public void initHardData(){
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

	/**
	 * @return the Location detected used to as a key to identify the server's
	 * address
	 */
	public String getLocation(){
	
		int max = 0, value  = 0;
		Set<String> keys = dataNetLocation.keySet();
		String LocationDetected= new String("No Location detected");
		ArrayList<String> commun = new ArrayList<String>();
		
		for(Iterator<String> itLocation = keys.iterator(); itLocation.hasNext();){
			
			value = 0;
			String locationHistory =itLocation.next();
			ArrayList<String> networks = dataNetLocation.get(locationHistory);
			commun.clear();
			
			for(Iterator<String> itNet = wifiDetected.iterator(); itNet.hasNext();){
				String network =itNet.next();
				if(networks.contains(network)== true){
					value ++;	
					commun.add(network);
				}
			}
			if(value > max){
				max  = value;
				LocationDetected = locationHistory; 

			}
		}

		return LocationDetected;
				
	}

}
