package testing;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;


/**
 * 
 * class which implements detecting lo
 * 
 * @author vlad
 *
 */
class WifiLocationDetection implements LocationDetector{
	/**
	 * arraylist of wireless netowrks detected
	 */
	ArrayList <String> wifiDetected;
	/**
	 * history of networks and locations 
	 */
	TreeMap<String, ArrayList<String>> dataNetLocation;
	/**
	 * the location detected used further for connection
	 */
	String location;
	
	/**
	 * constructor of the class
	 * initialize its members
	 */
	WifiLocationDetection(){
		
		wifiDetected = new ArrayList<String>();
		dataNetLocation = new TreeMap<String, ArrayList<String>>();
		initHardData();
		location = new String("No location detected"); 
		detectWireless();
	}
	
	
	/**
	 * detects the wireless available
	 */
	public void detectWireless(){
			
		
		ProcessBuilder builder = new ProcessBuilder("netsh", "wlan", "show", "networks");
		
		Process process;
		try
		{
			process = builder.start();				// pornesc programul
			InputStream is = process.getInputStream();   // obtin outuputul shellului ca input in program
												// clasa este abstracta
			InputStreamReader isr = new InputStreamReader(is);  // creez o instanta ISR pe baza lui InputStream
			BufferedReader br = new BufferedReader(isr); // transform streamul de biti in caractere
			String line;
			while ((line = br.readLine()) != null) {
			     StringTokenizer st = new StringTokenizer(line,": ");
			     while(st.hasMoreTokens()){
			     	String nameLine= st.nextToken();
			     	if(nameLine.compareTo("SSID") == 0){
			     		st.nextToken();
			     		wifiDetected.add(st.nextToken());
			     		
			     	}
			     	else
			     		break;
			     }
			}
			br.close();
			
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	
	@Override
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


