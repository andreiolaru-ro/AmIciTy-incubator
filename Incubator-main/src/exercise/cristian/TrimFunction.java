package exercise.cristian;

import java.util.ArrayList;

/**
 * 
 * @author cristian
 */
public class TrimFunction extends DefaultFunctions {

	TrimFunction(String name) {
		super(name);
	}

	public ArrayList<String> transform(ArrayList<String> toTransform) {
		ArrayList<String> output = new ArrayList();
		for (int i = 0; i < toTransform.size(); i++) {
			output.add(toTransform.get(i).trim());
		}
		return output;
	}

}
