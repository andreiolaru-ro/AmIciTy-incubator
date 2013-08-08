package com.example.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	TextView tv;
	Button toggle;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Intent service = new Intent(this, RingTServ.class); 
		tv = (TextView) findViewById(R.id.notify);
		toggle = (Button) findViewById(R.id.toggle);
		stopService(service);
		toggle.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View e)
			{
				if( toggle.getText().toString().equalsIgnoreCase("start")) {
					Log.e("Cristi", "HERE");
					toggle.setText("Stop");
					startService(service);
				}
				else {
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
