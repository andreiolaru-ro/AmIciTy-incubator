/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
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
	// ContextCore myCore;
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

	}

	@Override
	public void onDestroy() {
		receiverWifi.abortBroadcast();
		super.onDestroy();
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
