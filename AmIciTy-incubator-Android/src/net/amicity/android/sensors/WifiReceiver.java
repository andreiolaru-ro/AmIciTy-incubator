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
