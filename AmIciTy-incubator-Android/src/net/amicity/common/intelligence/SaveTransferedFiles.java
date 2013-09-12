package net.amicity.common.intelligence;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import net.amicity.android.MainActivity;
import net.amicity.common.context_types.TransferFileItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import android.os.Environment;

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
	 * The main activity
	 */
	MainActivity ma;

	/**
	 * @param myCore
	 *            the context core
	 * @param ma
	 */
	public SaveTransferedFiles(ContextCore myCore, MainActivity ma) {
		this.myCore = myCore;
		this.ma = ma;
	}

	@Override
	public void invoke() {
		files = ((TransferFileItem) myCore.getContextStorage().get(
				ContextTypes.TRANSFER_FILE_CONTEXT)).getFiles();
		for (FileContext f : files) {
			try {
				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/workspace/" + f.filename);
				if (!file.exists()) {
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
		ma.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				ma.getChanges().setText(
						ma.getChanges().getText() + "\n"
								+ "All working files are now in your phone");
			}
		});
	}

}
