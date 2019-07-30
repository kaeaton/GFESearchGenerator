package org.chori.bsg.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class ResultsPublisher extends Observable{
	
	// make it a Singleton
	private static ResultsPublisher instance = new ResultsPublisher();

	// hold the text and color info
	private List text = new ArrayList();
	
	// prevent the class from being instantiated
	private ResultsPublisher(){}
	
	//Get the only object available
	public static ResultsPublisher getInstance(){
		return instance;
	}
	
	public void setLine(String line, String whichObserver, Boolean saveToFile) {
		text.add(0, line);
		text.add(1, whichObserver);
		text.add(2, String.valueOf(saveToFile));
		
		setChanged();
		notifyObservers(text);
	}
}
