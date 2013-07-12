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
		ArrayList<DefaultFunctions> functions = new ArrayList();
		UpperCaseFunction upper = new UpperCaseFunction("toUpperCase");
		functions.add(upper);
		LowerCaseFunction lower = new LowerCaseFunction("toLowerCase");
		functions.add(lower);
		NewLine80Characters eighty = new NewLine80Characters("newLine80ch");
		functions.add(eighty);
		TrimFunction trim = new TrimFunction("Trim");
		functions.add(trim);
		Graphic frame = new Graphic(functions);
	}
}
