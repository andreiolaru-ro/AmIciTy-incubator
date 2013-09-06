package net.amicity.android;

import net.amicity.incubator_android.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * The Activity which provides a textbox where the user may login and the
 * corresponding login button. After the button is pressed, the user will enter
 * the application.
 * 
 * @author ''Azgabast''
 * 
 */
public class LoginActivity extends Activity {

	/**
	 * The login button.
	 */
	Button b;
	/**
	 * The user textbox.
	 */
	EditText username;
	/**
	 * The intent which contains the username to be sent to the main activity.
	 */
	Intent i;
	/**
	 * The current context.
	 */
	Context c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		b = (Button) findViewById(R.id.login);
		// startActivity(i);

		username = (EditText) findViewById(R.id.username);
		c = this;
		b.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (username.getText().length() != 0
						&& username.getText().toString().contains("-")) {
					i = new Intent(c, MainActivity.class);

					i.putExtra("username", username.getText().toString());
					Log.e("Cristi", i.getExtras().getString("username"));
					startActivity(i);
				}

			}
		});

	}
}
