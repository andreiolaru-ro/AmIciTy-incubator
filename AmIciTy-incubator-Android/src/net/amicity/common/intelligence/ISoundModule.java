package net.amicity.common.intelligence;

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
public class ISoundModule extends Service implements IntelligenceModule
{

	@Override
	public void invoke(ContextItem item)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
