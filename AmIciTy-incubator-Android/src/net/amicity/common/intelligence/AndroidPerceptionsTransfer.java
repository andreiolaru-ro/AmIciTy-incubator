package net.amicity.common.intelligence;

import java.util.ArrayList;

import net.amicity.common.communications.Connection;
import net.amicity.common.context_types.AccelerometerItem;
import net.amicity.common.context_types.SoundItem;
import net.amicity.common.core.ContextTypes;
import net.amicity.common.core.IntelligenceModule;
import net.amicity.common.core.context.ContextCore;


/**
 * @author cristian
 *
 */
public class AndroidPerceptionsTransfer implements IntelligenceModule{
	
	/**
	 * The Core of the application;
	 */
	ContextCore myCore;
	/**
	 * The accelerometer perception
	 */
	String action;
	/**
	 * The sound perception
	 */
	double value;
	/**
	 * An arraylist of all my devices
	 */
	ArrayList<Connection> myDevices;

	/**
	 * constructor of the class initialize its members
	 * 
	 * @param cc
	 *            the context core
	 */
	public AndroidPerceptionsTransfer(ContextCore cc) {
		myCore = cc;
	}
	
	@Override
	public void invoke() {
		action = ((AccelerometerItem) myCore.getContextStorage().get(
				ContextTypes.ACCELEROMETER)).man;
		value = ((SoundItem) myCore.getContextStorage().get(
				ContextTypes.SOUND_CONTEXT)).getValue();
		
		
	}
	
}
