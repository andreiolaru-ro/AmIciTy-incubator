package net.amicity.pc.sensors;

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
	int changesDetected;
	/**
	 * the difference between the first size and the new size at detecting a change
	 */
	long sizeDifference;
	
	/**
	 * @param f : file which has been changed
	 * @param change : the number of changes occured
	 * @param size : the size  
	 */
	public FileChangeData(File f, int change, long size)
	{
		fileChanged = f;
		changesDetected = change;
		sizeDifference = size;
	}
}
