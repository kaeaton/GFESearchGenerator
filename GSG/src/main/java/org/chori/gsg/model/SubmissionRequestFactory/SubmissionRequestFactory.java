package org.chori.gsg.model.SubmissionRequestFactory;

// import org.chori.gsg.controller.*;
import org.chori.gsg.model.SubmissionRequests.*;

public class SubmissionRequestFactory {

	public static SubmissionRequest assembleSubmissionRequest(String whichTab, String whichGenes) {
		SubmissionRequest submissionRequest = null;

		switch (whichGenes) {
		case "HLA":
			switch (whichTab) {
			case "GFE":
				submissionRequest = new HlaGfeSubmissionRequest();
				break;
			case "NAME":
				submissionRequest = new HlaNameSubmissionRequest();
				break;
			case "FEATURE":
				submissionRequest = new HlaFeatureSubmissionRequest();
				break;
			default:
				System.out.println("HLA Switch, Submission Request Factory: haven't added that tab yet");
				break;
			}
			break;

		case "KIR":
			switch (whichTab) {
			case "GFE":
				// submissionRequest = new HlaGfeSubmissionRequest();
				break;
			case "NAME":
				// submissionRequest = new HlaNameSubmissionRequest();
				break;
			case "FEATURE":
				// submissionRequest = new HlaFeatureSubmissionRequest();
				break;
			default:
				System.out.println("KIR Switch, Submission Request Factory: haven't added that tab yet");
				break;
			}

		break;

		default:
			System.out.println("Gene selection switch, Submission Request Factory: haven't added those genes yet");
			break;
		}

		return submissionRequest;
	}
}