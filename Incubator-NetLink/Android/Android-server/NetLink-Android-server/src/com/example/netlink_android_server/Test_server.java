package com.example.netlink_android_server;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Test_server extends Activity implements View.OnClickListener {

	/**
	 * textField -> for messages
	 */
	EditText textField;
	/**
	 * Button to send
	 */
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textField = (EditText) findViewById(R.id.editText1);
		button = (Button) findViewById(R.id.button1);

		button.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		DefaultNetLink d = new DefaultNetLink();

		d.initializeReceival(4500, new DefaultMessageReceiver(), this);

	}
}
