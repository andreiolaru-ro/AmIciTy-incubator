package net.amicity.common.intelligence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.amicity.common.context_types.TransferFileItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.interfaces.PCInterface;


/**
 * @author cristian
 *
 */
public class SaveTransferedFiles implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * an array list of files transfered
	 */
	ArrayList<FileContext> files;
	
	/**
	 * @param myCore the context core
	 */
	public SaveTransferedFiles(ContextCore myCore) {
		this.myCore = myCore;
	}

	@Override
	public void invoke() {
		files = ((TransferFileItem) myCore.getContextStorage().get(
				ContextTypes.TRANSFER_FILE_CONTEXT)).getFiles();
		boolean succes = new File("./munca").mkdir();
		if(succes) {
			for(FileContext f : files) {
				//System.out.println("filename : " + f.filename + "content : " + f.content);
				try {
					File file = new File("./munca/" + f.filename);
					if(!file.exists()) {
						file.createNewFile();
					}
					FileWriter fw = new FileWriter(file.getAbsolutePath());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(f.content);
					bw.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			PCInterface.addNotification("all working directory moved, you can start work");
			System.out.println("all working directory moved, you can start work");
		}
	}
	
}
