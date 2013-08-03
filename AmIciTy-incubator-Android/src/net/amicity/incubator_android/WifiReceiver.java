package net.amicity.incubator_android;

import java.util.Iterator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;

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

	@Override
	public void onReceive(Context c, Intent intent)
	{
		List <ScanResult> networksDetected=  main.mainWifi.getScanResults();
	
		
          for(Iterator<ScanResult> it = networksDetected.iterator(); it.hasNext();){
          	
          	ScanResult networkResult = it.next();
          	if(main.wifiList.contains(networkResult.SSID) == false)
          		main.wifiList.add(networkResult.SSID);
           }
		
		main.getLocation(); 
		
	}

}