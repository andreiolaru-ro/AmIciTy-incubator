nullpackage net.amicity.pc.sensors;

import java.util.Timer;


/**
 *  pushes the Items in Queue - the filename, the number of changes, the 
 *  difference between the sizes
 * 
 * @author vlad
 *
 */
public class SenderModule 
{
	
	long timeStart;
	
	/**
	 *  instance of ChangeDetectorModule in order to gain access to the arrays
	 *  of FilesChanged 
	 */
	public ChangeDetectorModule myDetector;
	
	/**
	 * timer to send the data of changed file in core's q
	 */
	Timer timerPush;
	
	/**
	 * @param detectorReceived : the instance of ChangeDetectorModule received
	 */
	public SenderModule(ChangeDetectorModule detectorReceived){
		timeStart = 20000;
		myDetector = detectorReceived;
	}
	
	/**
	 *  setting the timer to send all the changes occured in the Core's queue 
	 */
	public void setTimer(){
		timerPush = new Timer("Send");
		timerPush.schedule(new SenderTimer(this), timeStart, timeStart);
	}
	

}


