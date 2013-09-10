nullnull/*******************************************************************************
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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import net.amicity.common.communications.Connection;
import net.amicity.common.intelligence.FileContext;

/**
 * @author cristian
 * 
 */
public class test_client {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String args[]) throws UnknownHostException {

		DefaultNetLink d = new DefaultNetLink();

		Connection c = new Connection(InetAddress.getByName("172.16.6.24"),
				"gica", 4500);
		
		File file = new File("E:\\mamaie.txt");
		FileContext fc = new FileContext();
		Scanner s;
		try {
			s = new Scanner(file);
			String line = "";
			while(s.hasNext()) {
				line = line + s.nextLine();
				line = line + "\n";
			}
			s.close();
			fc.filename = file.getName();
			fc.content = line;
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//send
		d.send(c, fc);
	}

}
