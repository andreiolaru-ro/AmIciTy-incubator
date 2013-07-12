/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Cristian Grigoras.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package exercise.cristian;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JCheckBox;

/**
 * 
 * @author cristian
 */
public abstract class DefaultFunctions implements Functions {

	JCheckBox c;

	DefaultFunctions(String name) {
		c = new JCheckBox();
		c.setPreferredSize(new Dimension(120, 50));
		c.setText(name);
	}

	public ArrayList<String> read(File in) {

		ArrayList<String> allFile = new ArrayList();

		try {

			Scanner s = new Scanner(in);
			while (s.hasNext()) {
				allFile.add(s.nextLine());
			}
			s.close();

		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}

		return allFile;
	}

	public abstract ArrayList<String> transform(ArrayList<String> toTransform);

	public void write(File out, int indicator, ArrayList<String> str) {

		boolean b = indicator == 1 ? true : false;

		FileWriter w;
		try {

			w = new FileWriter(out, b);
			for (int i = 0; i < str.size(); i++) {
				w.write(str.get(i) + "\n");
			}
			w.close();

		}
		catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
