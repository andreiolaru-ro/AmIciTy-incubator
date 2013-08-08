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


public class RingTServ extends Service
{
	Timer t;
	MediaRecorder m;
	AudioManager audioManager;
	@Override
    public void onCreate() {
		super.onCreate();
      Log.e("LALA", "LALALAL");
     // Toast.makeText(this, "HI", Toast.LENGTH_LONG);
		///AudioManager audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		//audioManager.setStreamVolume(AudioManager.STREAM_RING, 0, AudioManager.FLAG_ALLOW_RINGER_MODES|AudioManager.FLAG_PLAY_SOUND);
    }

	
	@Override
	  public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
	    Log.e("Cristi", "IEI");
	    
		//Toast.makeText(this, "HI", Toast.LENGTH_LONG);
		audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
	    t = new Timer();
		m = new MediaRecorder();
		m = new MediaRecorder();
		m.setAudioSource(MediaRecorder.AudioSource.MIC);
		m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		m.setOutputFile( Environment.getExternalStorageDirectory().getAbsolutePath() + "/ex.3gp");
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
		
		final Runnable r = new Runnable() {
		
			@Override
			public void run()
			{	
				double d = m.getMaxAmplitude()/32767.0;
				
				if( d > .7) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							7, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 7");
				}
				if( d <= .7 && d > .4) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							4, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 4");
				}
				if( d <= .4 && d > .1) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							2, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 2");
				}
				if( d <= .1) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							0, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 0");
				
				}
				
				m.stop();
				m.reset();
				m.release();
				m = null;
				m = new MediaRecorder();
				m.setAudioSource(MediaRecorder.AudioSource.MIC);
				m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				m.setOutputFile( Environment.getExternalStorageDirectory().getAbsolutePath() + "/ex.3gp");
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
			
		};
		
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run()
			{
				double d = m.getMaxAmplitude()/32767.0;
				
				if( d > .87) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							7, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 7");
				}
				if( d <= .87 && d > .74) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							6, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 6");
				}
				if( d <= .74 && d > .61) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							5, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 5");
				}
				if( d <= .61 && d > .48) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							4, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 4");
				}
				if( d <= .48 && d > .35) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							3, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 3");
				}
				if( d <= .35 && d > .22) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							2, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 2");
				}
				if( d <= .22 && d > .11) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							1, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 1");
				}
				if( d <= .11) {
					audioManager.setStreamVolume(AudioManager.STREAM_RING, 
							0, 
							AudioManager.FLAG_ALLOW_RINGER_MODES);
					Log.e("Cristi", "Changed to 0");
				}
				
				if( d > .6) {
					Log.e("Cristi","ALERTA");
					DefaultNetLink df = new DefaultNetLink();

					Connection c;
					try
					{
						c = new Connection(InetAddress.getByName("192.168.0.195"),
								"gica", 4500);
						df.send(c, "Alerta!! cineva a vorbit cu "+ d + " care e mai mare de .6");
						
					}
					catch (UnknownHostException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
				
				m.stop();
				m.reset();
				m.release();
				m = null;
				m = new MediaRecorder();
				m.setAudioSource(MediaRecorder.AudioSource.MIC);
				m.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
				m.setOutputFile( Environment.getExternalStorageDirectory().getAbsolutePath() + "/ex.3gp");
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
	public void onDestroy() {
		super.onDestroy();
		 t.cancel();
	        if( m != null)
	        	m.release();
	}


	@Override
	public IBinder onBind(Intent arg0)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
