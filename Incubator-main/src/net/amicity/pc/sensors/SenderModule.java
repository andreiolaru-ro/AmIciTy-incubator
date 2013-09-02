package net.amicity.pc.sensors;

import java.util.Timer;
import java.util.TimerTask;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.context.ContextCore;


/**
 *  pushes the Items in Queue - the filename, the number of changes, the 
 *  difference between the sizes
 * 
 * @author vlad
 *
 */
public class SenderModule  extends TimerTask
{
	/**
	 *  instance of ChangeDetectorModule in order to gain access to the arrays
	 *  of FilesChanged 
	 */
	ChangeDetectorModule myDetector;
	
	
	/**
	 * @param detectorReceived : the instance of ChangeDetectorModule received
	 */
	public SenderModule(ChangeDetectorModule detectorReceived){
		myDetector = detectorReceived;
	}
	
	/**
	 *  setting the timer to send all the changes occured in the Core's queue 
	 */
	public void setTimer(){
		Timer timerPush = new Timer("Send");
		timerPush.schedule(this, 0, 50000);
	}
	
	
	@Override
	public void run()
	{
		if(myDetector.filesChanged.isEmpty() == false){
			
			FilesItem sendNewChanges = new FilesItem(myDetector.filesChanged);
			ContextCore.postContextUpdate(sendNewChanges);
			
			// am clearuit asta!! Sei vorsichtig!!!
			myDetector.filesChanged.clear();
		}
		
	}

}
