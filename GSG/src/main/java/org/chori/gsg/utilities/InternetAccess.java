package org.chori.gsg.utilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.chori.gsg.gui.*;

/**
 * Tests for an active internet connection
 * 
 * @author Katrina Eaton
 * 
 */
public class InternetAccess {
	public InternetAccess() { }

	/**
     * Tests for internet access by attempting to connect to www.google.com
	 *
     * @return a boolean: true if successfully connects to google.com, false if not
     */
	public boolean tester() {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("InternetAccess.tester: Internet is connected");
			return true;
		} catch (MalformedURLException ex) { System.out.println("InternetAccess.tester: " + ex);
		} catch (IOException ex) { System.out.println("InternetAccess.tester: Internet is probably not connected: " + ex); }

		return false;

	}
}