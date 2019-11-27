package org.chori.gsg.model;

public class GeneFeature {
	public String sequence;
	public String sequenceId;
	public String locus;
	public String featureName;

	public GeneFeature(String seq, String seqId, String loc, String feat) {
		this.sequence = seq;
		this.sequenceId = seqId;
		this.locus = loc;
		this.featureName = feat;
	}

	public void setFeatureName(String featureName, String rank) {}



}