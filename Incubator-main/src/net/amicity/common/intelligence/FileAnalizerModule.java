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

import java.io.File;
import java.util.ArrayList;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.ChangeDetectorModule;
import net.amicity.pc.sensors.FileChangeData;

/**
 * module which analises the
 * 
 * 
 * @author vlad
 * 
 */
public class FileAnalizerModule extends Thread implements IntelligenceModule {

	/**
	 * instance of core to gain acces to its q
	 */
	ContextCore myCore;
	/**
	 * a list with the files opened
	 */
	ArrayList<File> filesOpened;

	/**
	 * controlling the timers which show the window;
	 */
	ChangeDetectorModule myTimer;
	/**
	 * if it is already a window on screen
	 */
	boolean shown;

	/**
	 * @param received
	 *            : the core of the application
	 * @param receivedTimer
	 *            : the timer which shows the window
	 */
	public FileAnalizerModule(ContextCore received,
			ChangeDetectorModule receivedTimer) {

		myTimer = receivedTimer;
		myCore = received;
		filesOpened = new ArrayList<File>();
		shown = false;
	}

	@Override
	public void invoke() {
		FilesItem itemReceived = (FilesItem) myCore.getContextStorage().get(
				ContextTypes.FILE_CONTEXT);
		System.out.println("Itemul a fost primit de analizator");
		for (FileChangeData fcd : itemReceived.filesMonitorized) {

			File file = fcd.getFile();
			long nrDiff = fcd.getDifference();
			boolean changed = fcd.getNrChange();

			if (changed == false) {
				System.out
						.println("nu am mai modificat fisierul de cand l-am deschis");

				if (filesOpened.contains(file) == true && shown == false) {

					myTimer.ceaseTimer();
					WindowMessage win = new WindowMessage(this, file);
					win.show();
					shown = true;
				}
				else {
					System.out
							.println("ESTE UN FISIER NOU DESCHIS si nu este continut"
									+ filesOpened.size());
					filesOpened.add(file);
				}
			}
			else {
				System.out
						.println("Am mai modificat fisierul de cand l-am deschis");

				if (nrDiff < 100 && shown == false) {
					WindowMessage win = new WindowMessage(this, file);
					win.show();
					shown = true;
				}
			}

		}

	}

}
