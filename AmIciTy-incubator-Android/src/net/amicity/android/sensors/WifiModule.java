package net.amicity.android.sensors;

import java.util.ArrayList;
import net.amicity.common.context_types.AbstractItem;
import net.amicity.common.context_types.WirelessItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.IBinder;

/**
 * 
 * determines the wireless networks;
 * 
 * @author vlad
 * 
 */
public class WifiModule extends Service implements SensorModule {

	/**
	 * cointains the networks detected
	 */
	ArrayList<String> wifiList;
	/**
	 * used for receving the networks detected by the WifiManager
	 */
	WifiReceiver receiverWifi;
	/**
	 * instance of ContextCore to acces its queues
	 */
	//ContextCore myCore;
	/**
	 * a ContextItem which will be added to core's queue
	 */
	AbstractItem wirelessItem;

	/**
	 * used for detecting the Wifi's acces points
	 */
	WifiManager mainWifi;


	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		System.out.println(" wirelessModule starts ");

		wirelessItem = new WirelessItem();

		wifiList = new ArrayList<String>();

		mainWifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		receiverWifi = new WifiReceiver(this);
		mainWifi.setWifiEnabled(true);
		registerReceiver(receiverWifi, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
		mainWifi.startScan();

		return Service.START_STICKY;
	}

	/**
	 * add data to contextUpdate queue
	 */
	public void addDataDetected() {

	ContextCore.postContextUpdate(wirelessItem);
		System.out.println("Detected: "
				+ ((WirelessItem) wirelessItem).wifiDetected.size()
				+ " networks");
	
	}

	@Override
	public void connect(ContextCore coreReceived) {
		// dummy
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
