package exercise.cristian;

import java.util.ArrayList;

/**
 * 
 * @author cristian
 */
public class NewLine80Characters extends DefaultFunctions {

	NewLine80Characters(String name) {
		super(name);
	}

	public ArrayList<String> transform(ArrayList<String> toTransform) {
		ArrayList<String> output = new ArrayList();
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
