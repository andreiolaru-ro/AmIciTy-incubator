package net.amicity.common.intelligence;

import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.core.ContextStorage;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;

/**
 * 
 * 
 * @author cristian
 * 
 */
public class DummyAccelerometerTest implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * The ContextStorage;
	 */
	ContextStorage storage;
	/**
	 * A string that indicates if the people walks or stays
	 */
	String action;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 */
	public DummyAccelerometerTest(ContextCore cc) {
		myCore = cc;
		storage = cc.getContextStorage();
	}

	@Override
	public void invoke() {
		System.out.println("DummyAccelerometerTest invoked");

		action = ((AccelerometerItem) storage.get(ContextTypes.ACCELEROMETER)).man;
		System.out.println("The (wo)man is: " + action);

	}

}
