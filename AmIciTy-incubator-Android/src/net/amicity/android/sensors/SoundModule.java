package net.amicity.android.sensors;

import java.util.Timer;
import java.util.TimerTask;
import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextItem;
import net.amicity.common.core.SensorModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

/**
 * The class which listens periodically to ambient sounds and creates a
 * context item based on the level of the sound.
 * @author ''Azgabast''
 *
 */
public class SoundModule extends Service implements SensorModule
{

	/**
	 * The core linked with the module.
	 */
	ContextCore ctxCore;
	/**
	 * The sound item.
	 */
	ContextItem soundItem;
	
	@Override
	public void connect(ContextCore cc)
	{
		this.ctxCore = cc;		
	}
	/**
	 * The timer which sets when the service should start working and states the
	 * time of repetition.
	 */
	Timer t;
	/**
	 * The media recorder used for listening to the ambient sounds.
	 */
	MediaRecorder m;
	/**
	 * The audio manager used to access the ringer volume.
	 */
	AudioManager audioManager;

	@Override
	public int onStartCommand(final Intent intent, int flags, int startId)
	{
		super.onStartCommand(intent, flags, startId);
		audioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);
		t = new Timer();
		m = new MediaRecorder();
		m = new MediaRecorder();
		m.setAudioSource(MediaRecorder.AudioSource.MIC);
		m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		m.setOutputFile(Environment.getExternalStorageDirectory()
				.getAbsolutePath() + "/ex.3gp");
		m.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try
		{
			m.prepare();
		}
		catch (Exception e)
		{

			e.printStackTrace();
		}
		m.start();
		m.getMaxAmplitude();

		t.scheduleAtFixedRate(new TimerTask()
		{

			@Override
			public void run()
			{
				double d = m.getMaxAmplitude() / 32767.0;
				audioManager.setStreamVolume(AudioManager.STREAM_RING,
						(int) Math.ceil(d * 7),
						AudioManager.FLAG_ALLOW_RINGER_MODES);
				Log.e("Cristi", "Changed to " + (int) Math.ceil(d * 7));
		
				soundItem = new SoundItem( d );
				ctxCore.postContextUpdate(soundItem);
				
				m.stop();
				m.reset();
				m.release();
				m = null;
				m = new MediaRecorder();
				m.setAudioSource(MediaRecorder.AudioSource.MIC);
				m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				m.setOutputFile(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/ex.3gp");
				m.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
				try
				{
					m.prepare();
				}
				catch (Exception e)
				{

					e.printStackTrace();
				}

				m.start();
				m.getMaxAmplitude();

			}

		}, 500, 3000);

		return Service.START_STICKY;

	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		t.cancel();
		if (m != null)
			m.release();
	}

	@Override
	public IBinder onBind(Intent i)
	{
		return null;
	}

}
