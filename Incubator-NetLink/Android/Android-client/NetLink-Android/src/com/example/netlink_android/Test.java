package com.example.netlink_android;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
public class Test extends Activity {

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
		final TextView tx = (TextView) findViewById(R.id.textView1);

		button.setOnClickListener(new View.OnClickListener() {

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
					tx.setText(e.getMessage());
					e.printStackTrace();
				}
			}

		});
	}

}
