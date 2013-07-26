/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Grigoras.
 * 
 * This file is part of Accelerometer.
 * 
 * Accelerometer is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * Accelerometer is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Accelerometer.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.example.accelerometer;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author cristian
 * 
 */
public class AccelerometerPerceptions extends Activity implements
		SensorEventListener {

	/**
	 * mLastX -> last x coordinate taken by accelerometer
	 */
	private float mLastX;
	/**
	 * mLastX -> last y coordinate taken by accelerometer
	 */
	private float mLastY;
	/**
	 * mLastX -> last z coordinate taken by accelerometer
	 */
	private float mLastZ;
	/**
	 * mInitialized -> true if sensor is initialized
	 */
	private boolean mInitialized;
	/**
	 * The SensorManager
	 */
	private SensorManager mSensorManager;
	/**
	 * The Accelerometer sensor
	 */
	private Sensor mAccelerometer;
	/**
	 * A minimum value for showing movement.
	 */
	private final float NOISE = (float) 5.0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.main);
		mInitialized = false;
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// can be ignored

	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		TextView tvX = (TextView) findViewById(R.id.x_axis);
		TextView tvY = (TextView) findViewById(R.id.y_axis);
		TextView tvZ = (TextView) findViewById(R.id.z_axis);

		ImageView iv = (ImageView) findViewById(R.id.image);

		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;

			tvX.setText("0.0");
			tvY.setText("0.0");
			tvZ.setText("0.0");

			mInitialized = true;
		}
		else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);

			if (deltaX < NOISE)
				deltaX = (float) 0.0;
			if (deltaY < NOISE)
				deltaY = (float) 0.0;
			if (deltaZ < NOISE)
				deltaZ = (float) 0.0;

			mLastX = x;
			mLastY = y;
			mLastZ = z;

			tvX.setText(Float.toString(deltaX));
			tvY.setText(Float.toString(deltaY));
			tvZ.setText(Float.toString(deltaZ));

			iv.setVisibility(View.VISIBLE);

			if (deltaX > deltaY && deltaX > deltaZ) {
				iv.setImageResource(R.drawable.shaker_fig_1);
			}
			else if (deltaY > deltaX && deltaY > deltaZ) {
				iv.setImageResource(R.drawable.shaker_fig_2);
			}
			else if (deltaZ > deltaX && deltaZ > deltaY) {
				iv.setImageResource(R.drawable.shaker_fig_3);
			}
			else {
				iv.setVisibility(View.INVISIBLE);
			}
		}
	}

}
