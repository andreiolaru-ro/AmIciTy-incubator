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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import net.amicity.common.communications.MessageReceiver;

/**
 * @author cristian
 * 
 */
public class DefaultMessageReceiver implements MessageReceiver {

	@Override
	public void receive(Object obj) {
		FileContext str = (FileContext) obj;
		try {
			
			File toWrite = new File(str.filename);
			if(!toWrite.exists())
				toWrite.createNewFile();
			FileWriter fw = new FileWriter(toWrite.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str.content);
			bw.close();
			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("done");
	}

}
