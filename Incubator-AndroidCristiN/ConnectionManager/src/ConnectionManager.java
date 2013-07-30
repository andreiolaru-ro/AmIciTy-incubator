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
	 * @param c The connection to be checked
	 * @return Whether the connection is contained in the list or not.
	 */
	public int contains (Connection c);
	/**
	 * @param c The connection to be removed.
	 * @return Whether the connection was removed or not.
	 */
	public boolean removeCOnnection(Connection c);
}
