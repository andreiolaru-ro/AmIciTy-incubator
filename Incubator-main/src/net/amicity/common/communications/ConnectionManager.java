/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru , Vlad Herescu, Cristian Radu Neagoe, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.common.communications;

import java.util.ArrayList;

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
	
	/**
	 * @param me 
	 * -> the user to search all his devices
	 * @return an ArrayList of connections from same user as me
	 */
	public ArrayList<Connection> getOtherConnections(Connection me);

	/**
	 * @param id an id for all his connections
	 * @return all connections from id
	 */
	ArrayList<Connection> getAllHisConnections(String id);
}
