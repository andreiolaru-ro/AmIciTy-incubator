package net.amicity.android.sensors;

import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.android.communications.DefaultMessageReceiver;
import net.amicity.android.communications.DefaultNetLink;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


/**
 * @author cristian
 *
 */
public class ServerModule extends Service implements SensorModule {
	
	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		System.out.println("Ma conectez la server");
		
		DefaultNetLink d = new DefaultNetLink();
		System.out.println("Am deschis conexiunea cu serverul");
		d.receiveFromServer(ContextCore.getServerSocket(), new DefaultMessageReceiver());
		
		// listen from other devices
		DefaultNetLink d2 = new DefaultNetLink();
		d2.initializeReceival(4501, new DefaultMessageReceiver());
		
		return Service.START_STICKY;
	}

	@Override
	public void connect(ContextCore cc) {
		// do nothing
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}