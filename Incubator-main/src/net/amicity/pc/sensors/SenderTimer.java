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
package net.amicity.pc.sensors;

import java.util.TimerTask;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.context.ContextCore;

/**
 * timer of Sender instance which sends data in core's q
 * 
 * @author vlad
 * 
 */
public class SenderTimer extends TimerTask {

	/**
	 * instance used for connection betwwen the 2 classes
	 */
	SenderModule mySender;

	/**
	 * @param recv
	 *            : to gain acces to the files arrays
	 */
	SenderTimer(SenderModule recv) {
		mySender = recv;
	}

	@Override
	public void run() {

		if (mySender.myDetector.filesChanged.isEmpty() == false) {

			System.out.println("nu mai este gol");

			FilesItem sendNewChanges = new FilesItem(
					mySender.myDetector.filesChanged);
			ContextCore.postContextUpdate(sendNewChanges);

		}

	}
}
