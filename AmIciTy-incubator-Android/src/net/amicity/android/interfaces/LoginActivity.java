/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.android.interfaces;

import net.amicity.android.MainActivity;
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
