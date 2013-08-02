package net.amicity.communications;


/**
 * 
 * cointains the methods to determine the location and to add new ones
 * in history
 * @author vlad
 *
 */
interface LocationDetector{
	/**
	 * has the puropose to detect the user's location based on the comparison
	 * between the history's wifis and the ones detected
	 * 
	 * @return : returns the location detected
	 */
	public String getLocation();
}