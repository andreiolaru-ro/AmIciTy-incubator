package netlink;

import java.net.UnknownHostException;

/**
 * @author cristian
 * 
 */
public class test {

	/**
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String args[]) throws UnknownHostException {

		DefaultNetLink d = new DefaultNetLink();

		d.initializeReceival(4500, new DefaultMessageReceiver());

	}

}
