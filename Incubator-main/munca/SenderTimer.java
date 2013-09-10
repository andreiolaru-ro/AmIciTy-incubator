nullnullnullnullnullpackage net.amicity.pc.sensors;

import java.util.TimerTask;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.context.ContextCore;


/**
 * timer of Sender  instance which sends data in core's q
 * 
 * @author vlad
 *
 */
public class SenderTimer extends TimerTask{
	
	/**
	 * instance used for connection betwwen the 2 classes
	 */
	SenderModule mySender;
	/**
	 * @param recv : to gain acces to the files arrays
	 */
	SenderTimer(SenderModule recv){
		mySender = recv;
	}
	
	@Override
	public void run()
	{
		
		if(mySender.myDetector.filesChanged.isEmpty() == false){
			
			System.out.println("nu mai este gol");
			
			FilesItem sendNewChanges = new FilesItem(mySender.myDetector.filesChanged);
			ContextCore.postContextUpdate(sendNewChanges);
			
			  
		}
		
	}
}
