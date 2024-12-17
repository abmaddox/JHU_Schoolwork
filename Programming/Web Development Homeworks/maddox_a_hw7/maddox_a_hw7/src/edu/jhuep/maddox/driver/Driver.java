/**
 * @author Andrew Maddox
 * @since Mar 7, 2024
 */
package edu.jhuep.maddox.driver;

import edu.jhuep.maddox.server.ServerBHC;

/**
 * Basic main function for point of entry. Port 20016 is my assigned port for the class.
 */
public class Driver {
	public static void main(String[] args) {
		ServerBHC server = new ServerBHC(20016);
		server.init();
	}

}
