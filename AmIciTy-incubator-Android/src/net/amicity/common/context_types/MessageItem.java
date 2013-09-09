package net.amicity.common.context_types;

import java.io.Serializable;

import net.amicity.common.communications.Connection;
import net.amicity.common.core.ContextTypes;


public class MessageItem extends AbstractItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * the user whoch sends the message
	 */
	public String myUser;
	/**
	 * the name of the file
	 */
	public String myFilename;
	
	
	
	
	/**
	 * the connection with which data will be sent back
	 */
	public Connection connectionSent;
	/**
     * @param User : receving the name of the User
 	* @param Filename : receving the name of the file
	 * @param cc : used to response to the MessageItem
 	*/
	public MessageItem(String User, String Filename, Connection cc,ContextTypes recv){
	    myUser = User;
	    myFilename = Filename;
	    connectionSent = cc;
	    this.type = recv;
    }
}
