package org.chori.gsg.model.submissionRequestFactory;

public class SubmissionRequestFactoryInstance {

	// make it a Singleton by instantiating the single instance
	private static SubmissionRequestFactoryInstance instance = new SubmissionRequestFactoryInstance();

	public static SubmissionRequestFactory factory = new SubmissionRequestFactory();

	// private prevents the class from being instantiated
	private SubmissionRequestFactoryInstance() { }

	public static SubmissionRequestFactoryInstance getInstance(){
		return instance;
	}
}