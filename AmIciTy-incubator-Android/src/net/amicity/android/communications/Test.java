package net.amicity.android.communications;

import java.net.InetAddress;
import java.net.UnknownHostException;

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
