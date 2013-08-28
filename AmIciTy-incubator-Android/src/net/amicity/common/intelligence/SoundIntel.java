package net.amicity.common.intelligence;

import net.amicity.android.MainActivity;
import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import android.content.Intent;
import android.media.AudioManager;

/**
 * This module gets sound notifications and does something based on their level
 * either adjusting the ringer volume or sending a message to a server.
 * 
 * @author ''Azgabast''
 * 
 */
public class SoundIntel implements IntelligenceModule {

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
	 * The audio manager used to access the ringer volume.
	 */
	AudioManager audioManager;
	/**
	 * The main activity.
	 */
	MainActivity ma;

	/**
	 * @param cc
	 *            the context core
	 * @param act
	 *            the main Activity.
	 */
	public SoundIntel(ContextCore cc, MainActivity act) {
		this.cc = cc;
		this.ma = act;
	}

	@Override
	public void invoke() {
		Intent intent = new Intent(ma, SoundChanger.class);
		double valuee = ((SoundItem) cc.getContextStorage().get(
				ContextTypes.SOUND_CONTEXT)).getValue();
		intent.putExtra("value", valuee);

		ma.startService(intent);
		ma.stopService(intent);
	}

}
