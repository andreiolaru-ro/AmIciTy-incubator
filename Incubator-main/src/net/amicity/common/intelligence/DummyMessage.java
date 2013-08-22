package net.amicity.common.intelligence;

import java.net.InetAddress;
import java.net.UnknownHostException;
import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.LocationItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.communications.DefaultNetLink;

public class DummyMessage implements IntelligenceModule
{
	
	ContextStorage cs;
	ContextCore cc;
	String location;
	DefaultNetLink d;
	public DummyMessage( ContextCore cc) {
		this.cc = cc;
		this.cs = cc.getContextStorage();
		d = new DefaultNetLink();
	}
	@Override
	public void invoke()
	{
		location = ((LocationItem) cs
				.get(ContextTypes.LOCATION_CONTEXT)).location;
		if( location.equals("CANTI"))
			try
			{
				System.out.println(" conectare!!");
				d.send(new Connection(InetAddress.getByName("172.16.4.144"), "Titi", 4500), "Salut!");
			}
			catch (UnknownHostException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

}
