import static org.junit.Assert.assertEquals;
import org.junit.Test;

import org.chori.gsg.model.SubmissionRequestFactory.*;
import org.chori.gsg.model.SubmissionRequests.*;

public class SetupTest {
 
	SubmissionRequestFactoryInstance submissionRequestFactoryInstance = SubmissionRequestFactoryInstance.getInstance();
	SubmissionRequestFactory submissionRequestFactory = submissionRequestFactoryInstance.factory;

    @Test
    public void testSubmissionRequestFactory() {
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("ERROR", "HLA"));
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("GFE", "HLA"));
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("NAME", "HLA"));
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("FEATURE", "HLA"));
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("ERROR", "KIR"));
        System.out.println(submissionRequestFactory.assembleSubmissionRequest("FEATURE", "ABO"));
    }

    @Test
    public void canSubmissionRequestSeeHlaDataSources() {
		SubmissionRequest testHlaNameSubmissionRequest = submissionRequestFactory.assembleSubmissionRequest("NAME", "HLA");
		String dataSource = testHlaNameSubmissionRequest.getDataSource();
		System.out.println(dataSource);
	}
}