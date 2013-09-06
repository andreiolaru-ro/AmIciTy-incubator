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
package net.amicity.android;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import net.amicity.android.sensors.AccelerometerModule;
import net.amicity.android.sensors.SoundModule;
import net.amicity.android.sensors.WifiModule;
import net.amicity.common.core.ContextManager;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.AndroidFileTransfer;
import net.amicity.common.intelligence.AndroidPerceptionsTransfer;
import net.amicity.common.intelligence.DummyAccelerometerTest;
import net.amicity.common.intelligence.DummyDevicesModule;
import net.amicity.common.intelligence.DummyMessage;
import net.amicity.common.intelligence.LocationModule;
import net.amicity.common.intelligence.SoundIntel;
import net.amicity.incubator_android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author cristian
 * 
 */
public class MainActivity extends Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * An array of intents that starts services
	 */
	public ArrayList<Intent> intents;

	/**
	 * The textbox for the username;
	 */
	EditText usrname;
	/**
	 * The textarea for the changes to be seen;
	 */
	TextView changes;
	/**
	 * The button which logs the user in.
	 */
	Button b;
	/**
	 * The devices connected to this one.
	 */
	TextView devices;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_act);
		Intent i = getIntent();
		intents = new ArrayList<Intent>();

		// Create ContextCore

		ContextCore cc = new ContextCore();
		// Create intelligence modules
		String uname = i.getExtras().getString("username");
		cc.setUsername(uname);

		// changes.setText("User is:" + i.getExtras().getString("username"));
		LocationModule lm = new LocationModule(cc);
		SoundIntel si = new SoundIntel(cc, this);
		DummyAccelerometerTest dat = new DummyAccelerometerTest(cc);
		DummyMessage dm = new DummyMessage(cc, this);
		DummyDevicesModule ddm = new DummyDevicesModule(cc);
		AndroidFileTransfer aft = new AndroidFileTransfer(cc);
		AndroidPerceptionsTransfer apt = new AndroidPerceptionsTransfer(cc);

		// make the link between ContextTypes and intelligence modules related
		// to type
		final HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm = new HashMap<ContextTypes, ArrayList<IntelligenceModule>>();

		ArrayList<IntelligenceModule> iModules = new ArrayList<IntelligenceModule>();
		iModules.add(lm);
		hm.put(ContextTypes.WIRELESS_CONTEXT, iModules);

		ArrayList<IntelligenceModule> iModules2 = new ArrayList<IntelligenceModule>();
		iModules2.add(si);
		hm.put(ContextTypes.SOUND_CONTEXT, iModules2);

		ArrayList<IntelligenceModule> iModules3 = new ArrayList<IntelligenceModule>();
		iModules3.add(dat);
		iModules3.add(aft);
		iModules3.add(apt);
		hm.put(ContextTypes.ACCELEROMETER, iModules3);

		ArrayList<IntelligenceModule> iModules4 = new ArrayList<IntelligenceModule>();
		iModules4.add(dm);
		hm.put(ContextTypes.LOCATION_CONTEXT, iModules4);

		ArrayList<IntelligenceModule> iModules5 = new ArrayList<IntelligenceModule>();
		iModules5.add(aft);
		iModules5.add(ddm);
		hm.put(ContextTypes.DEVICES_CONTEXT, iModules5);

		// start sensors services

		Intent intent = new Intent(this, WifiModule.class);
		intents.add(intent);
		startService(intent);
		Intent intent2 = new Intent(this, SoundModule.class);
		startService(intent2);
		intents.add(intent2);
		Intent intent3 = new Intent(this, AccelerometerModule.class);
		startService(intent3);
		intents.add(intent3);

		// Create the ContextManger
		ContextManager cm = new ContextManager(cc, hm);
		cm.start();
		// Create the Notification Dispatcher
		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.start();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		for (Intent i : intents) {
			this.stopService(i);
		}
		try {
			ContextCore.getServerSocket().close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
