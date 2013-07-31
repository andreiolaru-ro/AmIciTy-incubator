package exercise.vlad.client;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 * interfata Filtru contine antelte metodelor cu rolul de a usura adauagrea
 * unui nou filtru ce implementeaza aceasta interfata
 * 
 * @author vlad
 * 
 */
interface Filtre
{

	/**
	 * @param s
	 *             : what must be written : a String, an Array of Strings
	 * @param g
	 *             : a graphic variable for making changes regarding the canvas
	 * @param width
	 *             : the width where to write from
	 * @param height
	 *             : the height where to write from
	 */
	public void receiveData(Object s, Graphics g, int width, int height);

	/**
	 * splitting the text in words which are analised in compareWord if they
	 * fulfill the filter's condition
	 * rendering the text on the canvas
	 */
	public void setText();

	/**
	 * @param s
	 *             : word received for comparing the filter's condition
	 */
	public void compareWord(String s);

	/**
	 * @return the height occupied
	 */
	public int findHeight();

	/**
	 * @param type
	 *             : the type of the filter used
	 * @param Strings
	 *             : the arrayList to be drawn to every repaint();
	 */
	public void addStringsToHistory(ArrayList<Integer> type,
			ArrayList<String> Strings);

}