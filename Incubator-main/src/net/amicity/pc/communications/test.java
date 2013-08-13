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
package net.amicity.pc.communications;

import java.net.UnknownHostException;
import net.amicity.common.communications.DefaultMessageReceiver;
import net.amicity.common.communications.DefaultNetLink;

/**
 * @author cristian
 * 
 */
public class test {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String args[]) throws UnknownHostException {

		DefaultNetLink d = new DefaultNetLink();

		d.initializeReceival(4500, new DefaultMessageReceiver());

	}

}