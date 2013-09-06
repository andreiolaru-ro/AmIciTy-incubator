package net.amicity.common.context_types;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import net.amicity.common.core.ContextTypes;
import net.amicity.common.intelligence.FileContext;

/**
 * @author cristian
 *
 */
public class TransferFileItem extends AbstractItem {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * an arraylist of files to be copied.
	 */
	ArrayList<FileContext> files;

	/**
	 * the public constructor that initialize the files arraylist
	 */
	public TransferFileItem() {
		files = new ArrayList<FileContext>();
		this.type = ContextTypes.TRANSFER_FILE_CONTEXT;
	}
	
	/**
	 * @param f the folder from where i should copy files
	 */
	public void addFiles(File f) {
		
		File[] filesHere = f.listFiles();
		int i;
		String path = f.getAbsolutePath();
		
		for(i=0; i<filesHere.length; i++){	
			if(filesHere[i].isDirectory() == false){
				if(filesHere[i].getName().endsWith(".java")) {
					FileContext fc = new FileContext();
					fc.filename = filesHere[i].getName();
					Scanner s;
					try {
						s = new Scanner(filesHere[i]);
						while(s.hasNextLine()) {
							fc.content = fc.content + s.nextLine() + "\n";
						}
					}
					catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
			else{
				File contained = new File(path+"/"+filesHere[i].getName());
				addFiles(contained);
			}
			
		} 
	}
	
	/**
	 * @return all files from working_dir
	 * 
	 */
	public ArrayList<FileContext> getFiles() {
		return this.files;
	}

}
