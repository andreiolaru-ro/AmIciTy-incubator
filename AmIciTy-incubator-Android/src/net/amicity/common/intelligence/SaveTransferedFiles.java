package net.amicity.common.intelligence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import android.os.Environment;
import net.amicity.common.context_types.TransferFileItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;


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
		for(FileContext f : files) {
			System.out.println("filename : " + f.filename + "content : ");
			try {
				File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/workspace/" + f.filename);
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
	}
	
}
