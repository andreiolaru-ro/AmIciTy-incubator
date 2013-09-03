package net.amicity.common.context_types;

import java.io.Serializable;

import net.amicity.common.core.ContextItem;
import net.amicity.common.core.ContextTypes;

/**
 * An abstract item class containing ContextType
 * 
 * @author cristian
 * 
 */
public  abstract class AbstractItem implements ContextItem, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
