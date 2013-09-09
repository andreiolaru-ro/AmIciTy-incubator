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
package net.amicity.pc.intelligence;

import java.io.File;
import java.util.ArrayList;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.interfaces.WindowMessage;
import net.amicity.pc.sensors.ChangeDetectorModule;
import net.amicity.pc.sensors.FileChangeData;

/**
 * module which analises the opened files
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
	private ChangeDetectorModule myTimer;
	/**
	 * if it is already a window on screen
	 */
	private boolean shown;
	
	
	public File fileChanged;

	/**
	 * @param received
	 *            : the core of the application
	 * @param receivedTimer
	 *            : the timer which shows the window
	 */
	public FileAnalizerModule(ContextCore received,
			ChangeDetectorModule receivedTimer) {

		setMyTimer(receivedTimer);
		myCore = received;
		filesOpened = new ArrayList<File>();
		setShown(false);
		fileChanged = null;
	}

	@Override
	public void invoke() {
		FilesItem itemReceived = (FilesItem) myCore.getContextStorage().get(
				ContextTypes.FILE_CONTEXT);
		for (FileChangeData fcd : itemReceived.filesMonitorized) {

			File file = fcd.getFile();
			long nrDiff = fcd.getDifference();
			boolean changed = fcd.getNrChange();

			if (changed == false) {

				if (filesOpened.contains(file) == true && isShown() == false) {

					getMyTimer().ceaseTimer();
					fileChanged =file;
					WindowMessage win = new WindowMessage(this, file);
					win.show();
					setShown(true);
				}
				else {
					filesOpened.add(file);
				}
			}
			else {
				if (nrDiff < 100 && isShown() == false) {
					WindowMessage win = new WindowMessage(this, file);
					win.show();
					setShown(true);
				}
			}

		}

	}

	public ChangeDetectorModule getMyTimer() {
		return myTimer;
	}

	public void setMyTimer(ChangeDetectorModule myTimer) {
		this.myTimer = myTimer;
	}

	public boolean isShown() {
		return shown;
	}

	public void setShown(boolean shown) {
		this.shown = shown;
	}

}
