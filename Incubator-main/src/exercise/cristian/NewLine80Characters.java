/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru.
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
public class NewLine80Characters extends DefaultFunctions {

	/**
	 * @param name
	 *            - name of the function
	 */
	NewLine80Characters(String name) {
		super(name);
	}

	@Override
	public ArrayList<String> transform(ArrayList<String> toTransform) {
		ArrayList<String> output = new ArrayList<String>();
		for (int i = 0; i < toTransform.size(); i++) {
			if (toTransform.get(i).length() < 80) {
				output.add(toTransform.get(i));
			}
			else {
				String[] words = toTransform.get(i).split(" ");
				String line = new String();
				int sum = 0;
				for (int j = 0; j < words.length; j++) {
					sum += words[j].length() + 1;
					if (sum > 80) {
						output.add(line.substring(0, line.length() - 1));
						line = words[j] + " ";
						sum = words[j].length() + 1;
					}
					else
						line += words[j] + " ";
				}
				output.add(line.substring(0, line.length() - 1));
			}
		}
		return output;
	}

}
