package net.amicity.common.intelligence;

import net.amicity.common.context_types.PerceptionItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;
import net.amicity.pc.PCInterface;

/**
 * @author cristian
 * 
 */
public class ShowPerceptionModule implements IntelligenceModule {

	/**
	 * The Core of the application;
	 */
	ContextCore myCore;

	/**
	 * The perception item from all my other devices
	 */
	PerceptionItem pi;

	/**
	 * @param cc
	 *            the context core
	 */
	public ShowPerceptionModule(ContextCore cc) {
		myCore = cc;
	}

	@Override
	public void invoke() {
		pi = ((PerceptionItem) myCore.getContextStorage().get(
				ContextTypes.PERCEPTION_CONTEXT));
		// Afisarea perceptiilor....
		System.out.println("Perceptions: " + pi.getUser() + ", sound: " + pi.getValue() + ", man: " + pi.getAction());
		PCInterface.addNotification("Last sound level was: " + pi.getValue()
				+ "\n");
		PCInterface.addNotification("Last time the user was " + pi.getAction()
				+ "\n");

	}
}
