/*******************************************************************************
 * Copyright (c) 2013 ''Azgabast''.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     ''Azgabast'' - initial API and implementation
 ******************************************************************************/
import java.util.ArrayList;


/**
 * @author ''Azgabast''
 * The class which manages what connections the device has and their current
 * state.
 */
public class ConnMgr implements ConnectionManager
{
	/**
	 *  The connection list.
	 */
	ArrayList<Connection> connList;
	
	/**
	 *  The null constructor, which creates a new empty list.
	 */
	public ConnMgr() {
		connList = new ArrayList<Connection>();
	}
	
	/**
	 * @param connLst The initial connection list.
	 */
	public ConnMgr(ArrayList<Connection> connLst) {
		connList = new ArrayList<Connection>(connLst);
	}
	
	/**
	 * @param connLst The initial connection list.
	 */
	public ConnMgr(Connection[] connLst) {
		connList = new ArrayList<Connection>();
		for( int i = 0; i < connLst.length; i ++)
			connList.add(connLst[i]);
	}
	
	/**
	 * @param c The Connection to be added.
	 */
	@Override
	public void addConnection(Connection c) {
		connList.add(c);
	}
	
	/**
	 * @param id The desired connection's ID.
	 * @return The desired connection, or null if it isn't found.
	 */
	@Override
	public Connection getConnection(String id) {
		for ( Connection c: connList)
			if( c.id == id )
				return c;
		return null;
	}
	
	/**
	 * @param c The connection to be removed.
	 * @return Whether the connection was removed or not.
	 */
	@Override
	public boolean removeConnection(Connection c) {
		return connList.remove(c);	
	}
	
	@Override
	public String toString() {
		String s="";
		for( Connection c: connList) {
			s = s + "Id: " + c.id + " Ip: " + c.ip 
					+ "Port: " + c.port + " State: " + c.getState() + "\n";
		}
		return s;
		
	}
	
	
}
