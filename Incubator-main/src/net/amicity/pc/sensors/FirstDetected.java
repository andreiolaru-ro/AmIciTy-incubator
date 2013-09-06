/*******************************************************************************
 * Copyright (C) 2013 Andrei Olaru, Vlad Herescu, Cristian Neagoe, Cristian Grigoras
 * 
 * This file is part of AmIciTy-incubator-Android.
 * 
 * AmIciTy-incubator-Android is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator-Android is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator-Android.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.amicity.pc.sensors;

import java.io.File;

/**
 * simulates a File object containing the File properties at its first detection
 * 
 * @author vlad
 * 
 */
public class FirstDetected {

	/**
	 * the name of the File
	 */
	String fileName;
	/**
	 * the path of the File
	 */
	String pathName;
	/**
	 * time when the file was detected
	 */
	long firstTimeDetected;
	/**
	 * last time the file has been changed - "date modified"
	 */
	long lastTimeChanged;
	/**
	 * the size of the file when it was detected
	 */
	long lastsize;

	/**
	 * @param f
	 *            : the file first detected which properties are saved in a
	 *            FirstDetected instance
	 */
	FirstDetected(File f) {

		fileName = f.getName();
		pathName = f.getAbsolutePath();
		firstTimeDetected = f.lastModified();
		lastTimeChanged = f.lastModified();
		lastsize = f.length();
	}
}
