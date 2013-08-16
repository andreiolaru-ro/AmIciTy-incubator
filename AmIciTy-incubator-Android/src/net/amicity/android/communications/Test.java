/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Grigoras..
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.android.communications;

import java.net.InetAddress;
import java.net.UnknownHostException;

import net.amicity.common.communications.Connection;
import net.amicity.common.communications.MessageReceiver;
import net.amicity.incubator_android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author root
 * 
 */
public class Test extends Activity implements View.OnClickListener,
		MessageReceiver {

	/**
	 * textField -> for messages
	 */
	EditText textField;
	/**
	 * Button to send
	 */
	Button send;
	/**
	 * TextView -> for listening
	 */
	TextView tx;
	/**
	 * Button to listen
	 */
	Button listen;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textField = (EditText) findViewById(R.id.editText1);
		send = (Button) findViewById(R.id.button1);
		tx = (TextView) findViewById(R.id.textView1);
		listen = (Button) findViewById(R.id.button2);

		send.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				try {
					DefaultNetLink d = new DefaultNetLink();
					Connection c = new Connection(InetAddress
							.getByName("192.168.0.196"), "gica", 4500);
					d.send(c, textField.getText().toString());
					textField.setText("");
				}
				catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}

		});
		listen.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		listen.setEnabled(false);
		DefaultNetLink d = new DefaultNetLink();

		d.initializeReceival(4500, this);

	}

	@Override
	public void receive(Object obj) {
		tx.setText((String) obj);
	}

}
