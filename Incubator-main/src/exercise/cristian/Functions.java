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

import java.io.File;
import java.util.ArrayList;

/**
 * 
 * @author cristian
 */
public interface Functions {

	/**
	 * @param in
	 *            - read from in file
	 * @return an ArrayList of String for each line in File
	 */
	public ArrayList<String> read(File in);

	/**
	 * @param toTransform
	 *            - ArrayList of String that needs to transform
	 * @return the transformed ArrayList of String
	 */
	public ArrayList<String> transform(ArrayList<String> toTransform);

	/**
	 * @param out
	 *            - write in File out
	 * @param indicator
	 *            - 1 - append, 0 - not append;
	 * @param str
	 *            - String that neds to be write in file
	 */
	public void write(File out, int indicator, ArrayList<String> str);

}
