package net.amicity.common.core;

import java.util.concurrent.ConcurrentHashMap;
import net.amicity.common.context_types.AbstractItem;

/**
 * The ContextItem Storage picked by Intelligent Modules depending on the
 * notification
 * 
 * @author vlad
 * 
 */
public class ContextStorage extends
		ConcurrentHashMap<ContextTypes, AbstractItem>
{

	/**
	 * default serial UID - why needed?
	 */
	private static final long serialVersionUID = 1L;

}
