package org.chori.gsg.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.chori.gsg.view.*;


public class InternetAccess {
	public InternetAccess() { }

	public boolean tester() {
		try {
			URL url = new URL("http://www.google.com");
			URLConnection connection = url.openConnection();
			connection.connect();
			System.out.println("InternetAccess.tester: Internet is connected");
			return true;
		} catch (MalformedURLException e) { System.out.println("InternetAccess.tester: Internet is not connected");
		} catch (IOException e) { System.out.println("InternetAccess.tester: Internet is not connected"); }

		return false;

	}
}