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
package net.amicity.common.intelligence;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

/**
 * @author ''Azgabast''
 * 
 */
public class SoundChanger extends Service {

	/**
	 * The audio manager to access the sound volume.
	 */
	AudioManager audioManager;

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		double value = intent.getExtras().getDouble("value");

		audioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);

		audioManager.setStreamVolume(AudioManager.STREAM_RING,
				(int) Math.ceil(value * 7),
				AudioManager.FLAG_ALLOW_RINGER_MODES
						| AudioManager.FLAG_PLAY_SOUND);
		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
