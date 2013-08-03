package com.example.infrastructure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;




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
	public void onPause(){
		super.onPause();
		this.unregisterReceiver(receiverWifi);
	}

}




