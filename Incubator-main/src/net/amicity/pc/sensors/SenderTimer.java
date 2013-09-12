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

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		
		/*File f =  mySender.myDetector.filesChanged.get(0).fileChanged;
		if(){
			
		}*/
/*		String command name = "WINDOWTITLE"
		

		ProcessBuilder builder = new ProcessBuilder("tasklist", "/FI",
				"" "");

		Process process;
		try {
			process = builder.start(); // pornesc programul
			InputStream is = process.getInputStream(); // obtin outuputul
														// shellului ca
														// input in
														// program
			// clasa este abstracta
			InputStreamReader isr = new InputStreamReader(is); // creez o
																// instanta
																// ISR
																// pe baza
																// lui
																// InputStream
			BufferedReader br = new BufferedReader(isr);
		
*/		

		if (mySender.myDetector.filesChanged.isEmpty() == false) {
			

			System.out.println("nu mai este gol" + mySender.myDetector.filesChanged.size() );

			FilesItem sendNewChanges = new FilesItem(
					mySender.myDetector.filesChanged);
			ContextCore.postContextUpdate(sendNewChanges);

		}

	}
}
