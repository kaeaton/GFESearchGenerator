package org.chori.gsg.model.SubmissionRequestFactory;

import org.chori.gsg.model.SubmissionRequests.*;

public interface HlaInterface {

	public enum DataSource {
		HLA("http://neo4j.b12x.org"),
		KIR(null),
 		ABO(null);

		private final String dataSource;

		private DataSource(String dataSource) {
			this.dataSource = dataSource;
		}
    
		public String getDataSource() {
			return this.dataSource;
		}
	}
}