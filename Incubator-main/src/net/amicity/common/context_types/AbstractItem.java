package net.amicity.common.context_types;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;

/**
 * An abstract item class containing ContextType
 * 
 * @author cristian
 * 
 */
public  abstract class AbstractItem implements ContextItem {

	/**
	 * the ContextType
	 */
	ContextTypes type;

	@Override
	public void setType(ContextTypes type)
	{
		this.type = type;
	}

	@Override
	public ContextTypes getType()
	{
		return this.type;
	}

}
