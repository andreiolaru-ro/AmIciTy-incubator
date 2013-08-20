package net.amicity.common.intelligence;

import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextItem;
import net.amicity.common.core.IntelligenceModule;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * This module gets sound notifications and does something based on their level
 * either adjusting the ringer volume or sending a message to a server.
 * @author ''Azgabast''
 *
 */
public class SoundIntel extends Service implements IntelligenceModule
{
	/**
	 * The current value of the recorded sound.
	 */
	int value;
	/**
	 * The object which stores the current soundItem;
	 */
	SoundItem si;
	@Override
	public void invoke(ContextItem item)
	{
		if ( item instanceof SoundItem ) {
			si = (SoundItem) item;
			if( si.value > .6 )
				//TODO
				System.out.println();
		}
		
	}

	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
