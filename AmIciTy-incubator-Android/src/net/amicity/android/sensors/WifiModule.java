package net.amicity.android.sensors;

import java.util.ArrayList;

import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;


/**
 * 
 * determines the wireless networks;
 * @author vlad
 *
 */
public class WifiModule extends Activity
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
	

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		

		super.onCreate(savedInstanceState);
	//	setContentView(R.layout.activity_wifi_location_detection);
		wifiList= new ArrayList<String>();
		

	     mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
	     receiverWifi = new WifiReceiver(this);
	     mainWifi.setWifiEnabled(true);
	     registerReceiver(receiverWifi, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
	     mainWifi.startScan();
		
	}

}
