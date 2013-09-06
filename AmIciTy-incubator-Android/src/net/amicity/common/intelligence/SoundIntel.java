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
		System.out.println("Value of sound is: " + valuee);

		ma.startService(intent);
		ma.stopService(intent);
	}
}
