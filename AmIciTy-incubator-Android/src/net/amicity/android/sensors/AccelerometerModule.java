/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.android.sensors;

import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;

/**
 * 
 * The class which listens periodically to accelerometer and creates a context
 * item based on the level of movemets.
 * 
 * @author cristian
 * 
 */
public class AccelerometerModule extends Service implements
		SensorEventListener, SensorModule {

	/**
	 * The core linked with the module.
	 */
	ContextCore ctxCore;
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
	/**
	 * A clock implementation for calculating activity in time
	 */
	private Chronometer clock = new Chronometer();
	/**
	 * Represent the total number of movements on x, y and z direction
	 */
	float total;
	/**
	 * The accelerometer item.
	 */
	AccelerometerItem accelerometerItem;

	@Override
	public void connect(ContextCore cc) {
		this.ctxCore = cc;
		accelerometerItem = new AccelerometerItem(0);
	}

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		mInitialized = false;
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		clock.start();
		mSensorManager.registerListener(this, mAccelerometer,
				SensorManager.SENSOR_DELAY_NORMAL);

		// maybe a while(true) here

		return Service.START_STICKY;
	}

	@Override
	public void onSensorChanged(SensorEvent event) {

		if (clock.getMinutes(System.currentTimeMillis()) > 5) {
			clock.stop();
			try {
				accelerometerItem.changeType(total);
				ctxCore.postContextUpdate(accelerometerItem);
				Thread.sleep(4000);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			total = 0;
		}

		float x = event.values[0];
		float y = event.values[1];
		float z = event.values[2];

		if (!mInitialized) {
			mLastX = x;
			mLastY = y;
			mLastZ = z;

			mInitialized = true;
		}
		else {
			float deltaX = Math.abs(mLastX - x);
			float deltaY = Math.abs(mLastY - y);
			float deltaZ = Math.abs(mLastZ - z);

			total = deltaX + deltaY + deltaZ;

			if (deltaX < NOISE)
				deltaX = (float) 0.0;
			if (deltaY < NOISE)
				deltaY = (float) 0.0;
			if (deltaZ < NOISE)
				deltaZ = (float) 0.0;

			mLastX = x;
			mLastY = y;
			mLastZ = z;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// can be ignored
	}

	@Override
	public IBinder onBind(Intent intent) {
		// can be ignored
		return null;
	}

}

/**
 * A class for measure the time.
 * 
 * @author cristian
 * 
 */
class Chronometer {

	/**
	 * begin -> the starting time
	 */
	private long begin;

	/**
	 * Starts the chronometer
	 */
	public void start() {
		begin = System.currentTimeMillis();
	}

	/**
	 * Stop the chronometer
	 */
	public void stop() {
		begin = System.currentTimeMillis();
	}

	/**
	 * @param end
	 *            -> the current time
	 * @return the time in milliseconds
	 */
	public long getMilliseconds(long end) {
		return end - begin;
	}

	/**
	 * @param end
	 *            -> the current time
	 * @return the time in seconds
	 */
	public double getSeconds(long end) {
		return (end - begin) / 1000.0;
	}

	/**
	 * @param end
	 *            -> the current time
	 * @return the time in minutes
	 */
	public double getMinutes(long end) {
		return (end - begin) / 60000.0;
	}

	/**
	 * @param end
	 *            -> the current time
	 * @return time in hours
	 */
	public double getHours(long end) {
		return (end - begin) / 3600000.0;
	}

}
