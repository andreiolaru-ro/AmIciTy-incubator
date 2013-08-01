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
public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		ArrayList<DefaultFunctions> functions = new ArrayList<DefaultFunctions>();
		UpperCaseFunction upper = new UpperCaseFunction("toUpperCase");
		functions.add(upper);
		LowerCaseFunction lower = new LowerCaseFunction("toLowerCase");
		functions.add(lower);
		NewLine80Characters eighty = new NewLine80Characters("newLine80ch");
		// !new line after word "stop"
		// !java reflection (structura unui obiect)
		// Cred ca cea mai eleganta metoda de a rezolva asta e ca in Default
		// function
		// sa fie un vector in care sunt retinute niste numere reprezentand
		// categoria. Daca doua functii au numarul 1 nu pot fi enable in
		// aceelasi timp.
		functions.add(eighty);
		TrimFunction trim = new TrimFunction("Trim");
		functions.add(trim);
		new Graphic(functions).repaint();
	}
}
