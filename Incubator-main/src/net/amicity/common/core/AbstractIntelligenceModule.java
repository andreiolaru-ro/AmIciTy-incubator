package net.amicity.common.core;

import java.util.Set;

/**
 * @author cristian
 * 
 */
public class AbstractIntelligenceModule implements IntelligenceModule {

	@Override
	public void invoke(ContextItem item) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param t
	 *            -> a set of types from should the Module receive items
	 * @return a set of ContextItem related to the Types from parameter
	 */
	protected static Set<ContextItem> get(Set<ContextTypes> t) {
		return null;
		// TODO ask the contextStore for Items of Types: t
	}

}
