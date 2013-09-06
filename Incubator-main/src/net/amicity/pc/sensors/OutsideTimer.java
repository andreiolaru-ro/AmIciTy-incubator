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
import java.util.TimerTask;

/**
 * the timer which checks for changes
 * 
 * @author vlad
 * 
 */
class OutsideTimer extends TimerTask {

	/**
	 * instance of ChangeDetectorModule, to gain acces to its arrays
	 */
	ChangeDetectorModule myModule;

	/**
	 * @param moduleRecv
	 *            : the module received
	 */
	public OutsideTimer(ChangeDetectorModule moduleRecv) {
		myModule = moduleRecv;
	}

	@Override
	public void run() {

		int contained, i, j;

		for (i = 0; i < myModule.filesArray.size(); i++) {

			contained = 0;

			// comparing the differences between FirstDetected whose
			// properties are not changed, with the changed properties of
			// filesArray element

			FirstDetected fileOld = myModule.filesDetected.get(i);
			File f = myModule.filesArray.get(i);

			if (f.lastModified() != fileOld.lastTimeChanged) {
				fileOld.lastTimeChanged = f.lastModified();

				for (j = 0; j < myModule.filesChanged.size(); j++) {

					FileChangeData fileLiteral = myModule.filesChanged.get(j);
					String fullPath = fileLiteral.fileChanged.getAbsolutePath()
							+ fileLiteral.fileChanged.getName();
					String fPath = f.getAbsolutePath() + f.getName();

					// the file has already been changed in past, and we
					// just modify the fileLiteral properties
					if (fullPath.compareTo(fPath) == 0) {

						System.out.println("il contine");
						contained = 1;
						fileLiteral.changesDetected = true;
						// System.out.println(f.length() + "   "
						// +fileOld.lastsize);
						fileLiteral.sizeDifference = f.length()
								- fileOld.lastsize;

						// System.out.println(fileLiteral.fileChanged.getName()
						// + " " + fileLiteral.changesDetected + " " +
						// fileLiteral.sizeDifference);

						return;
					}
				}

				// if it's the first time when the file has been changed,
				// add a file
				if (contained == 0) {

					System.out.println("este noua ");
					FileChangeData fileChanged = new FileChangeData(f,
							f.length());
					myModule.filesChanged.add(fileChanged);

				}

			}
		}
	}
}
