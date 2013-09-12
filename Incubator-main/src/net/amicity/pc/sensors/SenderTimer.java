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
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
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
		
		for(int i = 0  ; i< mySender.myDetector.filesChanged.size(); i++){
			
			FileChangeData f = mySender.myDetector.filesChanged.get(i);
			
			if(f.changesDetected== false){
			
				boolean value = stillOpened(f.fileChanged.getName(), "eclipse.exe");
				boolean value1 = stillOpened(f.fileChanged.getName(), "Notepad");
				boolean value2 = stillOpened(f.fileChanged.getName(), "WordPad");
			
			if( value == false && value1 == false && value2 == false)
				mySender.myDetector.filesChanged.remove(f);
			}
		}

		if (mySender.myDetector.filesChanged.isEmpty() == false) {

			FilesItem sendNewChanges = new FilesItem(
					mySender.myDetector.filesChanged);
			ContextCore.postContextUpdate(sendNewChanges);

		}
	}
	
	/**
	 * @param fileName : file to be checked whether is opened
	 * @param task : the type of the task : eclipse, notepad or Wordpad
	 * @return : true if it is still opened
	 */
	@SuppressWarnings("resource")
	public static boolean stillOpened(final String fileName, final String task){
		
		String commandName ="\"" + "WINDOWTITLE eq "+ fileName + " - Notepad" + "\"";
		if(task.equals("WordPad")){
			commandName ="\"" + "WINDOWTITLE eq "+ fileName + " - Wordpad" + "\"";
		}
		ProcessBuilder builder = new ProcessBuilder("tasklist", "/FI",
				commandName);
		if(task.equals("eclipse.exe")){
			commandName ="\"" + "imagename eq eclipse.exe" + "\"";
			builder = new ProcessBuilder("tasklist", "/FI", commandName, "/v");
		}
		
				Process process;
				try {
					process = builder.start(); // pornesc programul
					InputStream is = process.getInputStream(); 
					InputStreamReader isr = new InputStreamReader(is); 
					BufferedReader br = new BufferedReader(isr);
					
					if(task.endsWith("ad")== true){
					
						
						if( (br.readLine()).startsWith("INFO") == true){
							br.close();
							return false;
						}
						return true;
					}
					 br.readLine();
					 br.readLine();
					 br.readLine();
					 StringTokenizer st = new StringTokenizer(br.readLine(), "/ ");
					 while (st.hasMoreTokens()) {
							String nameLine = st.nextToken();
							if(nameLine.equals(fileName)== true){
								br.close();
								return true;
							}
							
					 }
					 br.close();
					
				}
				catch(Exception e){
					System.out.println("ERROR  command line process");
				}
	
		return false;
	}
	
}
