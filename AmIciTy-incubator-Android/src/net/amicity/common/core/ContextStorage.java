package net.amicity.common.core;

import java.util.ArrayList;
import java.util.Iterator;
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
	 * @param c The type verified to be contained.
	 * @return True if the storage contains that context type.
	 */
	public boolean contains(ContextTypes c) {
		for( AbstractItem i : this)
			if( i.getType() == c)
				return true;
		return false;
			
	}
	
	/**
	 * Removes a ContextItem of a certain type.
	 * @param c The type of item to be removed.
	 */
	public synchronized void remove(ContextTypes c) {
		Iterator<AbstractItem> it;
		if( this.contains(c))
			for(it =  this.iterator(); it.hasNext(); )
				if( it.next().getType() == c)
					it.remove();
	}
	
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
