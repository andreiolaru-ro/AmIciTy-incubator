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
 *  module which analises the 
 * 
 * 
 * @author vlad
 *
 */
public class FileAnalizerModule extends Thread implements IntelligenceModule
{
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
	 * @param received : the core of the application
	 * @param receivedTimer : the timer which shows the window
	 */
	public FileAnalizerModule(ContextCore received,ChangeDetectorModule receivedTimer  ){
		
		myTimer = receivedTimer;
		myCore = received;
		filesOpened = new ArrayList<File>();
		shown = false;
	}

	@Override
	public void invoke()
	{
		FilesItem itemReceived  = 
		(FilesItem) myCore.getContextStorage().get(ContextTypes.FILE_CONTEXT);
		System.out.println("Itemul a fost primit de analizator");
		for(FileChangeData fcd : itemReceived.filesMonitorized){

			
			File file  = fcd.getFile();
			long nrDiff = fcd.getDifference();
			boolean changed = fcd.getNrChange();
			
			if(changed == false  ){
				System.out.println("nu am mai modificat fisierul de cand l-am deschis");
				
				if(filesOpened.contains(file) == true && shown == false){
					System.out.println("NU ESTE UN FISIER NOU DESCHIS");
					System.out.println( myTimer.cancel());
					System.out.println( myTimer.pushQueue.cancel());
					WindowMessage win= new WindowMessage(this);
					shown = true;
				}
				else{
System.out.println("ESTE UN FISIER NOU DESCHIS si nu este continut" + filesOpened.size());
					filesOpened.add(file);
				}
			}
			else{
				System.out.println("Am mai modificat fisierul de cand l-am deschis");
				
				if(nrDiff < 100 && shown == false){
					WindowMessage win= new WindowMessage(this);
					win.show();
					shown = true;
				}
			}
			

		}

	}

}


