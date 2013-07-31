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
public class ConnMgr
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
	public void addConnection(Connection c) {
		connList.add(c);
	}
	
	/**
	 * @param c The connection to be checked.
	 * @return A boolean value representing whether the connection is contained
	 * or not in the list.
	 */
	public int contains(Connection c) {
		for ( int i = 0; i < connList.size(); i ++)
			if( connList.get(i).id == c.id && connList.get(i).ip == c.ip 
			&& connList.get(i).port == c.port)
				return i;
		return -1;
	}
	
	/**
	 * @param c The connection to be removed.
	 * @return Whether the connection was removed or not.
	 */
	public boolean removeConnection(Connection c) {
		int where = contains(c);
		if( where == -1)
			return false;
		connList.remove(where);
		return true;
	}
	
	@Override
	public String toString() {
		String s="";
		for( Connection c: connList) {
			s = s + "Id: " + c.id + " Ip: " + c.ip + " State: " + c.getState() + "\n";
		}
		return s;
		
	}
	
	
}
