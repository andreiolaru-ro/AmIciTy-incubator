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

import java.util.Timer;

/**
 * pushes the Items in Queue - the filename, the number of changes, the
 * difference between the sizes
 * 
 * @author vlad
 * 
 */
public class SenderModule {

	long timeStart;

	/**
	 * instance of ChangeDetectorModule in order to gain access to the arrays of
	 * FilesChanged
	 */
	public ChangeDetectorModule myDetector;

	/**
	 * timer to send the data of changed file in core's q
	 */
	Timer timerPush;

	/**
	 * @param detectorReceived
	 *            : the instance of ChangeDetectorModule received
	 */
	public SenderModule(ChangeDetectorModule detectorReceived) {
		timeStart = 20000;
		myDetector = detectorReceived;
	}

	/**
	 * setting the timer to send all the changes occured in the Core's queue
	 */
	public void setTimer() {
		timerPush = new Timer("Send");
		timerPush.schedule(new SenderTimer(this), timeStart, timeStart);
	}

}
