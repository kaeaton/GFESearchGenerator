package org.chori.gsg.data.submissionRequests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.chori.gsg.data.submissionRequestFactory.*;
import org.chori.gsg.data.submissionRequests.*;

public class SubmissionRequestTest {
 

	// SubmissionRequestFactoryInstance submissionRequestFactoryInstance = SubmissionRequestFactoryInstance.getInstance();
	// SubmissionRequestFactory submissionRequestFactory = SubmissionRequestFactoryInstance.factory;
	private SubmissionRequestFactory submissionRequestFactory = SubmissionRequestFactory.getSubmissionRequestFactoryInstance();
	HlaGfeSubmissionRequest hlaGfeSubReq = new HlaGfeSubmissionRequest();
	HlaNameSubmissionRequest hlaNameSubReq = new HlaNameSubmissionRequest();
	HlaFeatureSubmissionRequest hlaFeatureSubReq = new HlaFeatureSubmissionRequest();
	KirGfeSubmissionRequest kirGfeSubReq = new KirGfeSubmissionRequest();
	KirNameSubmissionRequest kirNameSubReq = new KirNameSubmissionRequest();
	KirFeatureSubmissionRequest kirFeatureSubReq = new KirFeatureSubmissionRequest();

	// @Test
	// public void testsubmissionRequestFactory() {
	// 	assertSame("The submission request objects do not match, HLA, GFE", hlaGfeSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("HLA", "GFE").getClass());
	// 	assertSame("The submission request objects do not match, HLA, NAME", hlaNameSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("HLA", "NAME").getClass());
	// 	assertSame("The submission request objects do not match, HLA, FEATURE", hlaFeatureSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("HLA", "FEATURE").getClass());
	// 	assertSame("The submission request failures do not match, HLA, ERROR", null,
	// 					submissionRequestFactory.assembleSubmissionRequest("HLA", "ERROR"));
	// 	assertSame("The submission request objects do not match, KIR, GFE", kirGfeSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("KIR", "GFE").getClass());
	// 	assertSame("The submission request objects do not match, KIR, NAME", kirNameSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("KIR", "NAME").getClass());
	// 	assertSame("The submission request objects do not match, KIR, FEATURE", kirFeatureSubReq.getClass(),
	// 					submissionRequestFactory.assembleSubmissionRequest("KIR", "FEATURE").getClass());
	// 	assertSame("The submission request errors do not match, KIR, ERROR", null,
	// 					submissionRequestFactory.assembleSubmissionRequest("KIR", "ERROR"));
	// 	assertSame("The submission request errors do not match, ABO, FEATURE", null,
	// 					submissionRequestFactory.assembleSubmissionRequest("ABO", "FEATURE"));
	// }

	// @Test
	// public void canSubmissionRequestSeeDataSources() {
	// 	assertEquals("http://neo4j.b12x.org", hlaGfeSubReq.dataSource);
	// 	assertEquals("http://neo4j.b12x.org", hlaNameSubReq.dataSource);
	// 	assertEquals("http://neo4j.b12x.org", hlaFeatureSubReq.dataSource);
		
	// }
}