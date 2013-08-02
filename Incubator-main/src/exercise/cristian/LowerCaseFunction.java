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

import java.util.ArrayList;

/**
 * 
 * @author cristian
 */
public class LowerCaseFunction extends DefaultFunctions {

	/**
	 * @param name
	 *            - name of the function
	 */
	LowerCaseFunction(String name) {
		super(name);
	}

	@Override
	public ArrayList<String> transform(ArrayList<String> toTransform) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < toTransform.size(); i++) {
			output.add(toTransform.get(i).toLowerCase());
		}
		return output;
	}

}
