package net.amicity.android;

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
import net.amicity.common.intelligence.DummyAccelerometerTest;
import net.amicity.common.intelligence.LocationModule;
import net.amicity.common.intelligence.SoundIntel;
import net.amicity.incubator_android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
	 * The context Core
	 */
	ContextCore cc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_act);
		// Create ContextCore
		cc = new ContextCore();
		// Create intelligence modules
		LocationModule lm = new LocationModule(cc);
		SoundIntel si = new SoundIntel(cc);
		DummyAccelerometerTest dat = new DummyAccelerometerTest(cc);
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
		iModules2.add(dat);
		hm.put(ContextTypes.ACCELEROMETER, iModules3);
		// start sensors services
		Intent intent = new Intent(this, WifiModule.class);
		startService(intent);
		Intent intent2 = new Intent(this, SoundModule.class);
		startService(intent2);
		Intent intent3 = new Intent(this, AccelerometerModule.class);
		startService(intent3);

		// Create the ContextManger
		ContextManager cm = new ContextManager(cc, hm);
		cm.start();
		// Create the Notification Dispatcher
		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.start();

	}

}
