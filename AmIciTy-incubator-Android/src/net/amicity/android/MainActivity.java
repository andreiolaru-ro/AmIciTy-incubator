package net.amicity.android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import net.amicity.android.sensors.WifiModule;
import net.amicity.common.core.ContextManager;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.NotificationDispatcher;
import net.amicity.common.core.context.ContextCore;
import net.amicity.common.intelligence.LocationModule;
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
		cc = new ContextCore("mamaie");
		// Create intelligence modules
		LocationModule lm = new LocationModule(cc);
		// make the link between ContextTypes and intelligence modules related
		// to type
		final HashMap<ContextTypes, ArrayList<IntelligenceModule>> hm = new HashMap<ContextTypes, ArrayList<IntelligenceModule>>();
		ArrayList<IntelligenceModule> iModules = new ArrayList<IntelligenceModule>();
		iModules.add(lm);
		hm.put(ContextTypes.WIRELESS_CONTEXT, iModules);
		// start sensors services
		Intent intent = new Intent(this, WifiModule.class);
		Bundle b = new Bundle();
		b.putSerializable("core", cc);
		intent.putExtras(b);
		startService(intent);

		// Create the ContextManger
		ContextManager cm = new ContextManager(cc, hm);
		cm.start();
		// Create the Notification Dispatcher
		NotificationDispatcher nd = new NotificationDispatcher(cc);
		nd.start();

		System.out.println("before : ");
		while (this.getIntent().getExtras() == null) {
			continue;
		}
		b = this.getIntent().getExtras();
		cc = (ContextCore) b.getSerializable("core");
		System.out.println("after : " + cc.getContextUpdates().size());
	}

}
