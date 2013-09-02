package net.amicity.pc.sensors;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * 
 *  initialise a file change detector, which checks for "date modified" changes
 * once every 10 seconds.
 * 
 * @author Vlad herescu 
 *
 */

public class ChangeDetectorModule extends TimerTask
{
	
	/**
	 * a list containing the files detected in workspace
	 * the properties of the files are changed automatically in this array
	 */
	ArrayList<File> filesArray;
	/**
	 *  a list containg the properties of the files first detected at searching 
	 *  for files in the working space 
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
	 * initialising this Module's lists
	 */
	public ChangeDetectorModule()
	{
		filesChanged = new ArrayList<FileChangeData>();
		filesDetected = new ArrayList<FirstDetected>();
		filesArray= new ArrayList<File>();
		workSpaceCheck = new File("D:/ECLIPSE/Workspace");
			
		this.detectFiles(workSpaceCheck);
	    
	
	}
	
	/**
	 * starting ChangeDetectorModule Timer and calling the method to start
	 * the SenderModule's timer
	 */
	public void startTimer(){
		
		 Timer timerChanges = new Timer("Change");
		 timerChanges.schedule(this, 0, 10000);
		 
		 SenderModule pushQueue = new SenderModule(this);
		 pushQueue.setTimer();
	}
	
	/**
	 * @param f : firstly, receives the director whose files are checked for 
	 * changes. The method is called recursively
	 */
	public void detectFiles(File f){
		
		File[] files = f.listFiles();
		int i;
		String path = f.getAbsolutePath();
		for(i=0; i<files.length; i++){
			
			if(files[i].isDirectory() == false){
				if(files[i].getName().endsWith(".java")){
					if(filesArray.contains(files[i]) == false){
						
						FirstDetected fileFirst = new FirstDetected(files[i]);
						filesDetected.add(fileFirst);
						filesArray.add(files[i]);
					}
					
				}
			}
			else{
				File contained = new File(path+"/"+files[i].getName());
				detectFiles(contained);
			}
			
		} 
	}
	
	@Override
	public void run(){
		              
		int contained, i, j;
		
		for( i = 0 ; i < filesArray.size() ; i++ ){
			
			contained = 0;
			
			// comparing the differences between FirstDetected whose 
			// properties are not changed, with the changed properties of
			// filesArray element
			
			FirstDetected fileOld= filesDetected.get(i);
			File f = filesArray.get(i);	
			

			if(f.lastModified() != fileOld.lastTimeChanged){
				fileOld.lastTimeChanged = f.lastModified();
		
				
			     for( j = 0; j < filesChanged.size() ; j++){
			     	
			     	FileChangeData fileLiteral= filesChanged.get(j);
			     	String fullPath = fileLiteral.fileChanged.getAbsolutePath() +  fileLiteral.fileChanged.getName();
			     	String fPath = f.getAbsolutePath() + f.getName();
			     	
			     	
			     	// the file has already been changed in past, and we
			     	// just modify the fileLiteral properties
			     	if(fullPath.compareTo(fPath) == 0 ){
			     		
			     		System.out.println("il contine" );
			     		contained = 1;
						fileLiteral.changesDetected++;
						System.out.println(f.length() + "   " +fileOld.lastsize);
						fileLiteral.sizeDifference = f.length() - fileOld.lastsize;
						
						System.out.println(fileLiteral.fileChanged.getName() + " " + fileLiteral.changesDetected + " " + fileLiteral.sizeDifference);
						
			     		return;
			     	}
			     }
			     
			     // if it's the first time when the file has been changed, 
			     // add a file 
			     if(contained == 0){
			     	
			     	System.out.println("este noua " );
			     	FileChangeData fileChanged = new FileChangeData(f, 0, f.length());
			     	filesChanged.add(fileChanged);
			     	
			     }
		
			}	
		}
	}
	
}
