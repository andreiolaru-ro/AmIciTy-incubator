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
