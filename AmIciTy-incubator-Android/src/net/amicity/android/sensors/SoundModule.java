package net.amicity.android.sensors;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * The class which listens periodically to ambient sounds and creates a
 * context item based on the level of the sound.
 * @author ''Azgabast''
 *
 */
public class SoundModule extends Service implements SensorModule
{

	/**
	 * The core linked with the module.
	 */
	ContextCore ctxCore;
	/**
	 * The sound item.
	 */
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
