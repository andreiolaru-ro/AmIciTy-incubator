package net.amicity.common.intelligence;

import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;

/**
 * This module gets sound notifications and does something based on their level
 * either adjusting the ringer volume or sending a message to a server.
 * 
 * @author ''Azgabast''
 * 
 */
public class SoundIntel extends Service implements IntelligenceModule {

	/**
	 * The current value of the recorded sound.
	 */
	int value;
	/**
	 * The object which stores the current soundItem;
	 */
	SoundItem si;
	
	/**
	 * The linked contextCore;
	 */
	ContextCore cc;
	
	/**
	 * The context storage of the contextCore;
	 */
	ContextStorage storage;
	
	/**
	 * The audio manager used to access the ringer volume.
	 */
	AudioManager audioManager;
	
	/**
	 * @param cc the context core
	 */
	public SoundIntel(ContextCore cc) {
		this.cc  = cc;
	}

	@Override
	public void invoke() {
		audioManager = (AudioManager) this
				.getSystemService(Context.AUDIO_SERVICE);
		si = (SoundItem) storage.get(ContextTypes.SOUND_CONTEXT);
		
		audioManager.setStreamVolume(AudioManager.STREAM_RING,
				(int) Math.ceil(si.getValue() * 7),
				AudioManager.FLAG_ALLOW_RINGER_MODES | AudioManager.FLAG_PLAY_SOUND);
		
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
