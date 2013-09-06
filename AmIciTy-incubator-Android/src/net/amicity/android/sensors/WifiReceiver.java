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

import java.util.Iterator;
import java.util.List;

import net.amicity.common.context_types.WirelessItem;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;

/**
 * receiver of the notifications from the Wifimanager's scan method
 * 
 * @author vlad
 * 
 */
public class WifiReceiver extends BroadcastReceiver {

	/**
	 * instance of MainActivity in order to populate its members
	 */
	WifiModule main;

	/**
	 * 
	 * @param wld
	 *            :instance of the MainActivity in order to gain acces to its
	 *            members
	 */
	WifiReceiver(WifiModule wld) {
		main = wld;
	}

	@Override
	public void onReceive(Context c, Intent intent) {
		List<ScanResult> networksDetected = main.mainWifi.getScanResults();

		for (Iterator<ScanResult> it = networksDetected.iterator(); it
				.hasNext();) {

			ScanResult networkResult = it.next();
			if (main.wifiList.contains(networkResult.SSID) == false) {
				main.wifiList.add(networkResult.SSID);
			}
		}

		((WirelessItem) main.wirelessItem).wifiDetected.addAll(main.wifiList);
		main.addDataDetected();
	}

}
