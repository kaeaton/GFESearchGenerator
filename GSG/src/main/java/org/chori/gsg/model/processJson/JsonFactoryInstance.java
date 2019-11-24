package org.chori.gsg.model.processJson;

import com.fasterxml.jackson.core.JsonFactory;

/**
 * A Singleton Jackson fasterxml JsonFactory. Only one instance per the Jackson documentation
 * 
 * @author Katrina Eaton
 * 
 */
public class JsonFactoryInstance {

	// per the jackson fasterxml directions you should 
	// only have one factory running

	// make it a Singleton
	private static JsonFactoryInstance instance = new JsonFactoryInstance();

	/**
	 * The JsonFactory instance
	 */
	public static JsonFactory factory = new JsonFactory();

	// prevent the class from being instantiated
	private JsonFactoryInstance() {	}

	/**
	 * The JsonFactory instance getter method
	 * @return the JsonFactoryInstance for processing Json
	 */
	public static JsonFactoryInstance getInstance(){
		return instance;
	}
}
