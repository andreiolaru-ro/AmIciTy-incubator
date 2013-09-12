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
	 * controlling the timers which show the window;
	 */
	private ChangeDetectorModule myTimer;
	/**
	 * if it is already a window on screen
	 */
	private boolean shown;
	
	
	/**
	 * used by other modules such as the interfaces to find more about
	 * the file changed
	 */
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

				if ( isShown() == false){
					
					System.out.println("NU A MAI FOST SCHIMBAT");

					getMyTimer().ceaseTimer();
					fileChanged =file;
					WindowMessage win = new WindowMessage(this, file);
					win.createWindow();
					win.addWrite();
					setShown(true);
				}
			}
			else {
				System.out.println("A INTRAT in ELSE" + " "+ isShown() + " " +nrDiff);
				
				
				if (nrDiff < 100 && isShown() == false) {
					
					System.out.println("a fost SCHIMBAT dar NU de AJUNS " + file.getName());
					fileChanged =file;
					getMyTimer().ceaseTimer();
					WindowMessage win = new WindowMessage(this, file);
					win.createWindow();
					win.addWrite();
					setShown(true);
				}
			}

		}

	}

	/**
	 * @return timer
	 */
	public ChangeDetectorModule getMyTimer() {
		return myTimer;
	}

	/**
	 * @param myTimer : setting the timer
	 */
	public void setMyTimer(ChangeDetectorModule myTimer) {
		this.myTimer = myTimer;
	}

	/**
	 * @return : true if the Frame is shown
	 */
	public boolean isShown() {
		return shown;
	}

	/**
	 * @param shown : boolean to know if the frame is shown
	 */
	public void setShown(boolean shown) {
		this.shown = shown;
	}

}
