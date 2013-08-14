package net.amicity.android.sensors;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SoundModule extends Service implements SensorModule
{

	ContextCore ctxCore;
	ContextItem ctxItem;
	@Override
	public void connect(ContextCore cc)
	{
		this.ctxCore = cc;		
	}
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

}
