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
public class DummyChangeDetector extends TimerTask
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
	 * 
	 */
	ArrayList<FileChangeData> filesChanged; 
	/**
	 * 
	 */
	File workSpaceCheck;
//	FileChangeData fileChanged;
	
	/**
	 * @param f : director whose files are checked for changes
	 */
	public DummyChangeDetector(File f)
	{
		filesChanged = new ArrayList<FileChangeData>();
		filesDetected = new ArrayList<FirstDetected>();
		filesArray= new ArrayList<File>();
		workSpaceCheck = f;
	}
	

	/**
	 * @param args : args received : none
	 */
	public static void main(String[] args)
	{
		Timer timer = new Timer("Change");
		File f = new File("D:/ECLIPSE/Workspace");
		DummyChangeDetector detector= new DummyChangeDetector(f);
		detector.detectFiles(f);
			
	
	     timer.schedule(detector, 0, 10000);
		 
		
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
	
	public void run(){
		              
		int contained;
		
		for(int i=0 ; i < filesArray.size() ; i++ ){
			contained = 0;
			FirstDetected fileOld= filesDetected.get(i);
			File f = filesArray.get(i);	
			
			if(f.lastModified() != fileOld.lastTimeChanged){
				fileOld.lastTimeChanged = f.lastModified();
		
				
			     for(int j = 0; j < filesChanged.size(); j++){
			     	FileChangeData fileLiteral= filesChanged.get(j);
			     	String fullPath = fileLiteral.fileChanged.getAbsolutePath() +  fileLiteral.fileChanged.getName();
			     	String fPath = f.getAbsolutePath() + f.getName();
			     	
			     	if(fullPath.compareTo(fPath) == 0 ){
			     		System.out.println("il contine" );
			     		contained = 1;
			     		fileLiteral= filesChanged.get(j);
						fileLiteral.changesDetected++;
						System.out.println(f.length() + "   " +fileOld.lastsize);
						fileLiteral.sizeDifference = f.length() - fileOld.lastsize;
						
						System.out.println(fileLiteral.fileChanged.getName() + " " + fileLiteral.changesDetected + " " + fileLiteral.sizeDifference);
						
			     		return;
			     	}
			     }
			     if(contained == 0){
			     	System.out.println("este noua " );
			     	FileChangeData fileChanged = new FileChangeData(f, 0, f.length());
			     	filesChanged.add(fileChanged);
			     }
		
			}	
		}
	}
	

}

/**
 * simulates a File object containing the File properties at its first detection
 * 
 * @author vlad
 *
 */
class FirstDetected{
	
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



/**
 * instance which remembers the number of changes executed upon a  file 
 * @author vlad
 *
 */
class FileChangeData{
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
	Long sizeDifference;
	
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




