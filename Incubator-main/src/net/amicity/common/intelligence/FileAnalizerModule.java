package net.amicity.common.intelligence;

import javax.swing.JFrame;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.FileChangeData;


/**
 *  module which analises the 
 * 
 * 
 * @author vlad
 *
 */
public class FileAnalizerModule implements IntelligenceModule
{
	ContextCore myCore;
	
	
	
	public FileAnalizerModule(ContextCore received){
		myCore = received;
	}

	@Override
	public void invoke()
	{
		FilesItem itemReceived  = 
		(FilesItem) myCore.getContextStorage().get(ContextTypes.FILE_CONTEXT);
		System.out.println("Itemul a fost primit de analizator");
		for(FileChangeData fcd : itemReceived.filesMonitorized){

			
			String fileName  = fcd.getFile().getName();
			long nrDiff = fcd.getDifference();
			int nrChange = fcd.getNrChange();
			
			if(nrDiff < 100 || nrChange < 3){
				/*System.out.println("Nu prea avet habar sa codati");
				System.out.println("pe" + fileName + " " + nrChange +" "+ nrDiff);
				System.out.println("vrei sa te ajute un prieten?");*/
				
				WindowMessage win= new WindowMessage();
				
				
			}

		}

	}

}


class WindowMessage extends JFrame{
	WindowMessage(){
		this.setSize(300, 300);
		this.show();
	}
}

