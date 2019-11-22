package org.chori.gsg.model;

public class FeatureObject {
	public String sequence;
	public String sequenceId;
	public String locus;
	public String featureName;

	public FeatureObject(String seq, String seqId, String loc, String feat) {
		this.sequence = seq;
		this.sequenceId = seqId;
		this.locus = loc;
		this.featureName = feat;
	}

}