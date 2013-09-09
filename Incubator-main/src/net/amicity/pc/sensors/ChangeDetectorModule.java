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

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;

/**
 * 
 * initialise a file change detector, which checks for "date modified" changes
 * once every 10 seconds.
 * 
 * @author Vlad herescu
 * 
 */

public class ChangeDetectorModule {

	/**
	 * a list containing the files detected in workspace the properties of the
	 * files are changed automatically in this array
	 */
	ArrayList<File> filesArray;
	/**
	 * a list containg the properties of the files first detected at searching
	 * for files in the working space
	 */
	ArrayList<FirstDetected> filesDetected;
	/**
	 * contains the number changes occured on each file and the difference
	 * between sizes
	 */
	ArrayList<FileChangeData> filesChanged;
	/**
	 * 
	 */
	File workSpaceCheck;

	/**
	 * timer which pushes modified files in core q
	 */
	SenderModule pushQueue;

	/**
	 * timer for change verfication;
	 */
	Timer timerChanges;

	/**
	 * initialising this Module's lists
	 */
	public ChangeDetectorModule() {
		filesChanged = new ArrayList<FileChangeData>();
		filesDetected = new ArrayList<FirstDetected>();
		filesArray = new ArrayList<File>();
		workSpaceCheck = new File("./../");
		//workSpaceCheck = new File("D:/ECLIPSE");
		this.detectFiles(workSpaceCheck);
		pushQueue = new SenderModule(this);
	}

	/**
	 * pausing the timer to check for changes
	 */
	public void ceaseTimer() {
		timerChanges.cancel();
		pushQueue.timerPush.cancel();

	}

	/**
	 * starting ChangeDetectorModule Timer and calling the method to start the
	 * SenderModule's timer
	 */
	public void startTimer() {

		System.out.println("Verific daca intru aici");
		timerChanges = new Timer("Change");
		timerChanges.schedule(new OutsideTimer(this), 10000, 10000);
		pushQueue.setTimer();
	}

	/**
	 * @param f
	 *            : firstly, receives the director whose files are checked for
	 *            changes. The method is called recursively
	 */
	public void detectFiles(File f) {

		File[] files = f.listFiles();
		int i;
		String path = f.getAbsolutePath();
		for (i = 0; i < files.length; i++) {

			if (files[i].isDirectory() == false) {
				if (files[i].getName().endsWith(".java")) {
					if (filesArray.contains(files[i]) == false) {

						FirstDetected fileFirst = new FirstDetected(files[i]);
						filesDetected.add(fileFirst);
						filesArray.add(files[i]);
					}

				}
			}
			else {
				File contained = new File(path + "/" + files[i].getName());
				detectFiles(contained);
			}

		}
	}

}
