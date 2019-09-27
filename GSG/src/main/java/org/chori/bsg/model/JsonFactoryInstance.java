package org.chori.bsg.model;

import com.fasterxml.jackson.core.JsonFactory;

public class JsonFactoryInstance {

	// per the jackson fasterxml directions you should 
	// only have one factory running

	// make it a Singleton
	private static JsonFactoryInstance instance = new JsonFactoryInstance();

	public static JsonFactory factory = new JsonFactory();

	// prevent the class from being instantiated
	private JsonFactoryInstance() {	}

	//Get the only object available
	public static JsonFactoryInstance getInstance(){
		return instance;
	}
}
