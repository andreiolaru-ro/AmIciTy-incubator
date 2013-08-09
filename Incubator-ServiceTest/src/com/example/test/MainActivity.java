package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * @author ''Azgabast'' This is a test Activity which starts a service which
 *         listens to the surrounding sounds periodically. It adjusts the ringer
 *         volume accordingly. If the ambient sound should surpass a certain
 *         level, a message will be sent to a server stating this fact.
 * 
 */
public class MainActivity extends Activity
{
	/**
	 * The button which turns the service on/off.
	 */
	Button toggle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent service = new Intent(this, RingTServ.class);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//try  //uncomment this if Connection implements Parcelable
		//{
			//service.putExtra("connection",
				//	new Connection(InetAddress.getByName("192.168.0.195"),
							//"Lala", 4500));
			service.putExtra("conip", "192.168.0.195");
			service.putExtra("conid", "w/e");
			service.putExtra("conport", 4500);
		//}
		//catch (UnknownHostException e1)
		//{
		//	e1.printStackTrace();
		//}
		toggle = (Button) findViewById(R.id.toggle);
		stopService(service);
		toggle.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View e)
			{
				if (toggle.getText().toString().equalsIgnoreCase("start"))
				{
					toggle.setText("Stop");
					startService(service);
				}
				else
				{
					toggle.setText("Start");
					stopService(service);
				}

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
