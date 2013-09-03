package net.amicity.common.intelligence;

import net.amicity.common.context_types.FilesItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.sensors.FileChangeData;


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
			System.out.println( fcd.getFile().getName());
			System.out.println( fcd.getDifference());
			System.out.println( fcd.getNrChange());
			
			System.out.println();
		}

	}

}
