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

	// return text from File in
	public ArrayList<String> read(File in);

	// return transformed toTransform string
	public ArrayList<String> transform(ArrayList<String> toTransform);

	// write String str in File out
	// if indicator in 0 => write from begging
	// else append to file
	public void write(File out, int indicator, ArrayList<String> str);

}
