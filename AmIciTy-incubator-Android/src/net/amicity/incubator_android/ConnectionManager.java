package net.amicity.incubator_android;

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
public interface ConnectionManager
{
	/**
	 * @param c The connection to be added.
	 */
	public void addConnection(Connection c);
	
	/**
	 * @param id The desired connection's id.
	 * @return The desired connection, or null if it isn't found.
	 */
	public Connection getConnection(String id);
	/**
	 * @param c The connection to be removed.
	 * @return Whether the connection was removed or not.
	 */
	public boolean removeConnection(Connection c);
}
