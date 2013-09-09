package net.amicity.common.communications;

import java.io.Serializable;


public class MessageItem implements Serializable
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
	
	
	public int nr;
	
	/**
     * @param User : receving the name of the User
 	* @param Filename : receving the name of the file
 	*/
	public MessageItem(String User, String Filename, int nr){
	    myUser = User;
	    myFilename = Filename;
	    this.nr = nr;
    }
}
