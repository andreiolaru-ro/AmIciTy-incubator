package com.example.test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

/**
 * @author ''Azgabast'' This is the service which listens to the ambient sound
 *         periodically. It also adjusts the phone's ringer volume accordingly.
 *         If the sound would surpass a certain level, the service connects to a
 *         server ( which he got from the activity that started the service) and
 *         notifies him about this fact.
 */
public class RingTServ extends Service
{
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
	/**
	 * The connection for the server.
	 */
	Connection c;

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

				//c = (Connection) intent.getExtras().getParcelable("connection"); // if implements parcelable
				try
				{
					c = new Connection (InetAddress.getByName((String)intent.getExtras().get("conip")), (String)intent.getExtras().get("conid"), (Integer)intent.getExtras().get("conport"));
				}
				catch (UnknownHostException e1)
				{
					e1.printStackTrace();
				}
				if (d > .6)
				{
					Log.e("Cristi", "ALERT");
					DefaultNetLink df = new DefaultNetLink();

					df.send(c, "ALERT! Someone spoke with " + (float) d
							+ " which is greater than .6");

				}

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

		}, 500, 2000);

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
