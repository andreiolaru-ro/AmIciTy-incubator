/*******************************************************************************
 * Copyright (C) 2013 Neagoe Cristian.
 * 
 * This file is part of AmIciTy-incubator.
 * 
 * AmIciTy-incubator is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or any later version.
 * 
 * AmIciTy-incubator is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with AmIciTy-incubator.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package exercise.cristin;
/**
 * 
 * @author Cristian
 *	Interfata clasei de manipulare a formelor geometrice
 *  @init - initializeaza membrii si componentele clasei
 *  @addListeners - adauga ascultatori componentelor interactive
 */
public interface GeoManInt {
	/**
	 * metoda de initializare
	 */
	public void init();
	/**
	 * metoda de adaugare de componente
	 */
	public void addComponents();
	/**
	 * metoda de adaugare de ascultatori
	 */
	public void addListeners();

}
