package net.amicity.common.context_types;

import java.util.ArrayList;

import net.amicity.common.core.ContextTypes;
import net.amicity.pc.sensors.*;

/**
 * Context Type to push in Core's queue. This type will be analysed by
 * @author vlad
 *
 */
public class FilesItem extends AbstractItem
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * arraylist of files changed detected
	 */
	public ArrayList<FileChangeData> filesMonitorized;
	
	/**
	 * @param filesReceived : received the list of files to be pushed in queue
	 */
	public FilesItem(ArrayList<FileChangeData> filesReceived){
		
		filesMonitorized = new ArrayList<FileChangeData>();
		filesMonitorized.addAll(filesReceived);
		this.type = ContextTypes.FILE_CONTEXT;
	}
}
