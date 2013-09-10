nullnullpackage net.amicity.pc.sensors;

import java.io.File;

/**
 * simulates a File object containing the File properties at its first detection
 * 
 * @author vlad
 *
 */
public class FirstDetected{
	
	/**
	 * the name of the File
	 */
	String fileName;
	/**
	 * the path of the File
	 */
	String pathName;
	/**
	 * time when the file was detected
	 */
	long firstTimeDetected;
	/**
	 * last time the file has been changed - "date modified"
	 */
	long lastTimeChanged;
	/**
	 * the size of the file when it was detected
	 */
	long lastsize;
	
	/**
	 * @param f : the file first detected which properties are saved in a
	 *  FirstDetected instance
	 */
	FirstDetected(File f){	
		
		fileName = f.getName();
		pathName = f.getAbsolutePath();
		firstTimeDetected = f.lastModified();
		lastTimeChanged = f.lastModified();
		lastsize = f.length();
	}
}
