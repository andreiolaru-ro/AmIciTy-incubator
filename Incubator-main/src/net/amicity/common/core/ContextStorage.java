package net.amicity.common.core;

import java.util.ArrayList;
import net.amicity.common.context_types.AbstractItem;

/**
 * The ContextItem Storage picked by Intelligent Modules depending on the
 * notification
 * 
 * @author vlad
 * 
 */
public class ContextStorage extends ArrayList<AbstractItem> {

	/**
	 * default serial UID - why needed?
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param type
	 *            -> the type of item to get
	 * @return the item coresponding to the type
	 */
	public AbstractItem get(ContextTypes type) {
		for (AbstractItem i : this) {
			if (i.getType() == type)
				return i;
		}
		return null;
	}
}
