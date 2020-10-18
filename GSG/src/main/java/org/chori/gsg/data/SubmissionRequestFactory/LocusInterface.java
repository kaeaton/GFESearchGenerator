package org.chori.gsg.data.submissionRequestFactory;

import org.chori.gsg.data.submissionRequests.*;

public interface LocusInterface {

	public enum HeaderDataSource {
		HLA("http://neo4j.b12x.org"),
		KIR("http://neo4j-kir.b12x.org/"),
		ABO(null);

		private final String headerDataSource;

		private HeaderDataSource(String headerDataSource) {
			this.headerDataSource = headerDataSource;
		}

		public String getHeaderDataSource() {
			return this.headerDataSource;
		}
	}

		public enum HttpCallDataSource {
		HLA("http://neo4j.b12x.org/db/data/transaction/commit"),
		KIR("http://neo4j-kir.b12x.org/db/data/transaction/commit"),
		ABO(null);

		private final String httpCallDataSource;

		private HttpCallDataSource(String httpCallDataSource) {
			this.httpCallDataSource = httpCallDataSource;
		}

		public String getHttpCallDataSource() {
			return this.httpCallDataSource;
		}
	}
}