nullnullnullnullnullpackage net.amicity.pc.sensors;

import java.io.File;



/**
 * instance which remembers the number of changes executed upon a  file 
 * @author vlad
 *
 */
public class FileChangeData{
	/**
	 * name of the file changed, from the list of files detected
	 */
	File fileChanged;
	/**
	 * the number of changes made upon the file
	 */
	boolean changesDetected;
	
	/**
	 * the number of changes made upon the file
	 */
	boolean opened;
	/**
	 * the difference between the first size and the new size at detecting a change
	 */
	long sizeDifference;
	
	/**
	 * @param f : file which has been changed
	 * @param change : the number of changes occured
	 * @param size : the size  
	 */
	public FileChangeData(File f, long size)
	{
		fileChanged = f;
		opened = true;
		changesDetected = false;
		sizeDifference = size;
	}
	
	/**
	 * @return : the File member to obtain data about the file
	 */
	public File getFile(){
		return fileChanged;
	}
	
	/**
	 * @return : number of changes made after the last send
	 */
	public boolean getNrChange(){
		boolean Changes = changesDetected;
		changesDetected = false;
		return Changes;
	}
	
	/**
	 * @return : the difference between the files sizes after the last send
	 */
	public long getDifference(){
		
		long nrDifference = sizeDifference;
		sizeDifference = 0;
		return  nrDifference;
	}

}
