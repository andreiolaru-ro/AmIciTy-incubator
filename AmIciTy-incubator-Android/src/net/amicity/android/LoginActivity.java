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

public class LoginActivity extends Activity {

	Button b;
	EditText username;
	Intent i;
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
