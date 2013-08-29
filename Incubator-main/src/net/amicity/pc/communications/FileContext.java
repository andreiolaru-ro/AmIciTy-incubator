package net.amicity.pc.communications;

import java.io.Serializable;


/**
 * @author cristian
 *
 */
public class FileContext implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The name of the file
	 */
	public String filename;
	/**
	 * the content of the file
	 */
	public String content;
}
